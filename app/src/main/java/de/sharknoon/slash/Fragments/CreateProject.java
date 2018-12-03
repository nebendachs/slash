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

        this.handleContinueButton(view);

        return view;
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
                /*Gson gson = new Gson();
                GetProjectMessage getProjectMessage = new GetProjectMessage(ParameterManager.getSession(view.getContext()), name);
                String jsonGetProjectMessage = gson.toJson(getProjectMessage);
                if(projectClient != null){
                    projectClient.getWebSocketClient().send(jsonGetProjectMessage);
                }*/
                Intent goToAddPeopleActivity = new Intent(view1.getContext(), AddPeopleActivity.class);
                getActivity().startActivity(goToAddPeopleActivity);
            }
        });
    }

}
