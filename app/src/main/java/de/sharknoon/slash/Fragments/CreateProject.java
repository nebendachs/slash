package de.sharknoon.slash.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.sharknoon.slash.Activties.AddPeopleActivity;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.Project.AddProjectMessage;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class CreateProject extends Fragment {
    private Button add_button;
    private String button_text;
    private ProjectPeopleReveiver peopleReceiver = null;
    private ArrayList<Person> people;
    private Spinner scrumMasterSpinner;

    public static CreateProject newInstance() {
        CreateProject fragment = new CreateProject();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        people = new ArrayList<>();
        peopleReceiver = new ProjectPeopleReveiver();
        IntentFilter intentFilter = new IntentFilter(ProjectPeopleReveiver.ACTION);
        getActivity().registerReceiver(peopleReceiver, intentFilter);
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);

        scrumMasterSpinner = view.findViewById(R.id.scrumMasterSpinner);
        this.setUpSpinner();

        this.handleAddPeopleButton(view);
        this.handleContinueButton(view);

        return view;
    }

    private void setUpSpinner() {
        ArrayList<Person> spinnerPeople = new ArrayList<>();
        spinnerPeople.add(new Person(null, "<none>"));
        spinnerPeople.add(new Person(ParameterManager.getUserId(getContext()), "me"));
        spinnerPeople.addAll(people);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, spinnerPeople);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        scrumMasterSpinner.setAdapter(adapter);
    }

    private void handleAddPeopleButton(View view) {
        add_button = view.findViewById(R.id.AddPeopleButton);
        button_text = add_button.getText().toString();
        add_button.setText(String.format("%s (0)", button_text));
        add_button.setOnClickListener(view1 -> {
            Intent goToAddPeopleActivity = new Intent(view1.getContext(), AddPeopleActivity.class);
            goToAddPeopleActivity.putExtra(AddPeopleActivity.PEOPLE, people);
            goToAddPeopleActivity.putExtra(AddPeopleActivity.IS_NEW_PROJECT, true);
            startActivity(goToAddPeopleActivity);
        });
    }

    private void handleContinueButton(View view) {
        Button button = view.findViewById(R.id.continueButton);
        EditText editName = view.findViewById(R.id.projectName);
        EditText editDesc = view.findViewById(R.id.projectDescription);
        button.setOnClickListener(view1 -> {
            String name = editName.getText().toString();
            String desc = editDesc.getText().toString();

            if(name.isEmpty()) {
                editName.setError("Name is required");
            } else {
                List<String> selectedList = new ArrayList<>();
                people.forEach(person -> selectedList.add(person.getId()));

                //Add current user
                selectedList.add(ParameterManager.getUserId(getContext()));

                Gson gson = new Gson();
                AddProjectMessage projectMessage = new AddProjectMessage(ParameterManager.getSession(view.getContext()), name, desc, selectedList, ((Person)scrumMasterSpinner.getSelectedItem()).getId());
                String jsonProjectMessage = gson.toJson(projectMessage);
                Log.d("JSON", jsonProjectMessage);
                if(homeScreenClient.getWebSocketClient().isOpen())
                    homeScreenClient.getWebSocketClient().send(jsonProjectMessage);
                else
                    Toast.makeText(getActivity(), getString(R.string.error_socket_not_connected), Toast.LENGTH_LONG).show();

                getActivity().finish();
            }
        });
    }

    public class ProjectPeopleReveiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PEOPLE_PROJECT";

        @Override
        public void onReceive(Context context, Intent intent) {
            people = (ArrayList<Person>) intent.getSerializableExtra(ACTION);
            add_button.setText(String.format(Locale.getDefault(), "%s (%d)", button_text, people.size()));
            setUpSpinner();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(peopleReceiver);
    }
}
