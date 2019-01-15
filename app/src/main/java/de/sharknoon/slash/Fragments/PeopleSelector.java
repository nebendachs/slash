package de.sharknoon.slash.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.sharknoon.slash.HomeScreen.FindUser;
import de.sharknoon.slash.HomeScreen.PersonSearchResult;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class PeopleSelector extends Fragment {
    public static final String CHAT = "Chat";
    public static final String PROJECT = "Project";
    public static final String SELECTED = "Selected";
    public static final String PROJECT_INFO = "ProjectInfo";

    private static final String ARG_PARAM1 = "purpose";
    private static final String ARG_PARAM2 = "removees";

    private ArrayList<Person> people;
    private PeopleAdapter adapter;
    private String purpose;
    private TextView no_results;
    private ArrayList<String> selectedIDs;

    private PeopleSearchResultReceiver peopleSearchResultReceiver = null;
    private PeopleSelectedReceiver peopleSelectedReceiver = null;
    private PeopleDeselectedReceiver peopleDeselectedReceiver = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PeopleSelector.
     */
    public static PeopleSelector newInstance(String purpose, ArrayList<Person> removees) {
        PeopleSelector fragment = new PeopleSelector();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, purpose);
        args.putSerializable(ARG_PARAM2,removees);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedIDs = new ArrayList<>();
        people = new ArrayList<>();
        if (getArguments() != null) {
            purpose = getArguments().getString(ARG_PARAM1);
            ArrayList<Person> removees = ((ArrayList<Person>)getArguments().getSerializable(ARG_PARAM2));
            if(removees != null) {
                removees.forEach(person -> selectedIDs.add(person.getId()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Register People Receiver
        peopleSearchResultReceiver = new PeopleSearchResultReceiver();
        IntentFilter inf = new IntentFilter(PeopleSearchResultReceiver.ACTION);
        getActivity().registerReceiver(peopleSearchResultReceiver, inf);

        //Register Selected Receiver
        peopleSelectedReceiver = new PeopleSelectedReceiver();
        IntentFilter inf2 = new IntentFilter(PeopleSelectedReceiver.ACTION);
        getActivity().registerReceiver(peopleSelectedReceiver, inf2);

        //Register Deselected Receiver
        peopleDeselectedReceiver = new PeopleDeselectedReceiver();
        IntentFilter inf3 = new IntentFilter(PeopleDeselectedReceiver.ACTION);
        getActivity().registerReceiver(peopleDeselectedReceiver, inf3);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_selector, container, false);

        // Lookup the recyclerview in activity layout
        RecyclerView rvPeople = view.findViewById(R.id.people_view);
        // Create adapter passing in the people list
        adapter = new PeopleAdapter(people, purpose);
        // Attach the adapter to the recyclerview to populate items
        rvPeople.setAdapter(adapter);
        // Set layout manager to position the items
        rvPeople.setLayoutManager(new LinearLayoutManager(view.getContext()));

        no_results = view.findViewById(R.id.people_no_results);

        this.handleSearchButton(view);

        return view;
    }

    private void handleSearchButton(View view) {
        Button button = view.findViewById(R.id.createChatFindButton);
        TextView search = view.findViewById(R.id.createChatEditWindow);
        button.setOnClickListener(view1 -> {
            if (search != null) {
                Gson gson = new Gson();
                FindUser findUser = new FindUser(ParameterManager.getSession(view1.getContext()), search.getText().toString());
                String jsonSearchMessage = gson.toJson(findUser);
                Log.d("JSON", jsonSearchMessage);
                if(homeScreenClient.getWebSocketClient().isOpen())
                    homeScreenClient.getWebSocketClient().send(jsonSearchMessage);
                else
                    Toast.makeText(getActivity(), getString(R.string.error_socket_not_connected), Toast.LENGTH_LONG).show();
            }
        });
    }

    public class PeopleSearchResultReceiver extends BroadcastReceiver {
        PeopleSelector ps = null;
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_SEARCH_RESULT";

        @Override
        public void onReceive(Context context, Intent intent) {
            PersonSearchResult searchResult = (PersonSearchResult) intent.getSerializableExtra(ACTION);
            people.clear();
            people.addAll(searchResult.getUsers());
            ArrayList<Person> removees = new ArrayList<>();
            for(int i=0; i<people.size(); i++) {
                //Remove current user
                if(people.get(i).getId().equals(ParameterManager.getUserId(context)))
                    removees.add(people.get(i));
                //Remove already selected users
                else if(selectedIDs.contains(people.get(i).getId()))
                    removees.add(people.get(i));
            }
            people.removeAll(removees);

            //Show "no results found" if list is empty
            if(people.isEmpty())
                no_results.setVisibility(View.VISIBLE);
            else
                no_results.setVisibility(View.GONE);

            //Refresh list in UI
            adapter.notifyDataSetChanged();
        }
    }

    public class PeopleSelectedReceiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_SELECTED";

        @Override
        public void onReceive(Context context, Intent intent) {
            String id = (String) intent.getSerializableExtra(ACTION);
            selectedIDs.add(id);
        }
    }

    public class PeopleDeselectedReceiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_DESELECTED";

        @Override
        public void onReceive(Context context, Intent intent) {
            String id = (String) intent.getSerializableExtra(ACTION);
            selectedIDs.remove(id);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(peopleSearchResultReceiver);
        getActivity().unregisterReceiver(peopleSelectedReceiver);
        getActivity().unregisterReceiver(peopleDeselectedReceiver);
    }
}
