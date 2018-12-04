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
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import de.sharknoon.slash.Activties.AddPeopleActivity;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.Project.AddProjectMessage;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class CreateProject extends Fragment {
    //private static final String ARG_PARAM1 = "param1";

    //private String mParam1;
    Button add_button;
    String button_text;
    ProjectPeopleReveiver peopleReceiver = null;
    ArrayList<Person> people;

    public static CreateProject newInstance() {
        CreateProject fragment = new CreateProject();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        peopleReceiver = new ProjectPeopleReveiver();
        people = new ArrayList<>();
        IntentFilter intentFilter = new IntentFilter(ProjectPeopleReveiver.ACTION);
        getActivity().registerReceiver(peopleReceiver, intentFilter);
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);

        this.handleAddPeopleButton(view);
        this.handleContinueButton(view);

        return view;
    }

    private void handleAddPeopleButton(View view) {
        add_button = view.findViewById(R.id.AddPeopleButton);
        button_text = add_button.getText().toString();
        add_button.setText(button_text + " (0)");
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddPeopleActivity = new Intent(view.getContext(), AddPeopleActivity.class);
                startActivity(goToAddPeopleActivity);
            }
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
                AddProjectMessage projectMessage = new AddProjectMessage(ParameterManager.getSession(view.getContext()), name, desc, selectedList);
                String jsonProjectMessage = gson.toJson(projectMessage);
                Log.d("JSON", jsonProjectMessage);
                homeScreenClient.getWebSocketClient().send(jsonProjectMessage);

                getActivity().finish();
            }
        });
    }

    public class ProjectPeopleReveiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PEOPLE_PROJECT";

        @Override
        public void onReceive(Context context, Intent intent) {
            people = (ArrayList<Person>) intent.getSerializableExtra(ACTION);
            add_button.setText(button_text + " (" + people.size() + ")");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(peopleReceiver);
    }
}
