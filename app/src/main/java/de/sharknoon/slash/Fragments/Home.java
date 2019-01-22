package de.sharknoon.slash.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;

public class Home extends Fragment {

    public static Home newInstance() {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        new UserHomeScreen(getActivity());

        this.handleCreateChatButton(view);

        return view;
    }

    private void handleCreateChatButton(View view) {
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> UserHomeScreen.CreateChatOrProject(view1.getContext()));
    }
}
