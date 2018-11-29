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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateProject.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateProject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProject extends Fragment {
    //private static final String ARG_PARAM1 = "param1";

    //private String mParam1;

    private OnFragmentInteractionListener mListener;

    public CreateProject() {
        // Required empty public constructor
    }

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String desc = editDesc.getText().toString();

                boolean tryagain = false;
                if(name.isEmpty()) {
                    editName.setError("Name is required");
                    tryagain = true;
                }
                if(false) { //Todo Check if project already exists
                    editName.setError("A project with this name already exists");
                    tryagain = true;
                }
                if(!tryagain) {
                    /*Gson gson = new Gson();
                    GetProjectMessage getProjectMessage = new GetProjectMessage(ParameterManager.getSession(view.getContext()), name);
                    String jsonGetProjectMessage = gson.toJson(getProjectMessage);
                    if(projectClient != null){
                        projectClient.getWebSocketClient().send(jsonGetProjectMessage);
                    }*/
                    Intent goToAddPeopleActivity = new Intent(view.getContext(), AddPeopleActivity.class);
                    getActivity().startActivity(goToAddPeopleActivity);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
