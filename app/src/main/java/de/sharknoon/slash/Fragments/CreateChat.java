package de.sharknoon.slash.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.sharknoon.slash.HomeScreen.UserCreateClientOrProjekt;
import de.sharknoon.slash.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateChat.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateChat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateChat extends Fragment {

    private static final String UserCreateClientOrProjektKey = "UserCreateClientOrProjektKey";

    private OnFragmentInteractionListener mListener;
    private UserCreateClientOrProjekt ccp;

    public CreateChat() {
        // Required empty public constructor
    }

    public static CreateChat newInstance(UserCreateClientOrProjekt ccp) {
        CreateChat fragment = new CreateChat();
        Bundle args = new Bundle();
        args.putSerializable(UserCreateClientOrProjektKey, ccp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_chat, container, false);
        ccp = (UserCreateClientOrProjekt) getArguments().getSerializable(UserCreateClientOrProjektKey);
        this.searchForUser(view);
        return view;
    }

    private void searchForUser(View view) {
        // Get text view element for registration and handover event listener
        EditText searchWindow = view.findViewById(R.id.createChatEditWindow);
        Button searchButton = view.findViewById(R.id.createChatFindButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search for User
                ccp.searchSinglePerson(v.getContext(), searchWindow.getText().toString());
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
