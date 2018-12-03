package de.sharknoon.slash.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.sharknoon.slash.Activties.AddPeopleActivity;
import de.sharknoon.slash.R;


public class CreateProject extends Fragment {
    //private static final String ARG_PARAM1 = "param1";

    //private String mParam1;

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
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);

        this.handleAddPeopleButton(view);
        this.handleContinueButton(view);

        return view;
    }

    private void handleAddPeopleButton(View view) {
        Button button = view.findViewById(R.id.continueButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddPeopleActivity = new Intent(view.getContext(), AddPeopleActivity.class);
                getActivity().startActivity(goToAddPeopleActivity);
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
                /*List<String> selectedList = new ArrayList<>();
                selected.forEach(person -> selectedList.add(person.getId()));

                Gson gson = new Gson();
                AddProjectMessage projectMessage = new AddProjectMessage(ParameterManager.getSession(view.getContext()), projectName, projectDescription, selectedList);
                String jsonProjectMessage = gson.toJson(projectMessage);
                Log.d("JSON", jsonProjectMessage);
                homeScreenClient.getWebSocketClient().send(jsonProjectMessage);*/

            }
        });
    }

}
