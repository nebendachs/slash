package de.sharknoon.slash.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.sharknoon.slash.Login.LogoutMessage;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PeopleSelector.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PeopleSelector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeopleSelector extends Fragment {
    private ArrayList<Person> people;
    private PeopleAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public PeopleSelector() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PeopleSelector.
     */
    public static PeopleSelector newInstance() {
        PeopleSelector fragment = new PeopleSelector();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        people = new ArrayList<Person>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_selector, container, false);

        // Lookup the recyclerview in activity layout
        RecyclerView rvPeople = (RecyclerView) view.findViewById(R.id.people_view);

        // Create adapter passing in the people list
        adapter = new PeopleAdapter(people);
        // Attach the adapter to the recyclerview to populate items
        rvPeople.setAdapter(adapter);
        // Set layout manager to position the items
        rvPeople.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people.clear();
        this.people.addAll(people);
        adapter.notifyDataSetChanged();
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
