package de.sharknoon.slash.People;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.sharknoon.slash.Activties.AddPeopleActivity;
import de.sharknoon.slash.Activties.CreateClientProjektActivity;
import de.sharknoon.slash.ChatMessages.ImageLoader;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.R;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private List<Person> people;
    private String purpose;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView roleTextView;
        public ImageView profilePicture;
        public ImageView personMood;
        private Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.context = context;
            profilePicture = itemView.findViewById(R.id.element_picture);
            personMood = itemView.findViewById(R.id.element_mood);
            nameTextView = itemView.findViewById(R.id.person_name);
            roleTextView = itemView.findViewById(R.id.person_role);
            itemView.setOnClickListener(this);
        }

        // Handles the row being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Person person = people.get(position);

                if(purpose.equals(PeopleSelector.CHAT)) {
                    //Send broadcast to Create Chat
                    Intent intent = new Intent(CreateClientProjektActivity.ChatPersonReceiver.ACTION);
                    intent.putExtra("Person", person);
                    context.sendBroadcast(intent);
                } else if(purpose.equals(PeopleSelector.PROJECT)) {
                    //Send broadcast to Add People
                    Intent intent = new Intent(AddPeopleActivity.ProjectPersonReceiver.ACTION);
                    intent.putExtra("Person", person);
                    context.sendBroadcast(intent);

                    //Send broadcast to People Selector
                    Intent intent2 = new Intent(PeopleSelector.PeopleSelectedReceiver.ACTION);
                    intent2.putExtra(PeopleSelector.PeopleSelectedReceiver.ACTION, person.getId());
                    context.sendBroadcast(intent2);

                    //Remove selected person from list
                    people.remove(position);
                    notifyItemRemoved(position);
                } else if(purpose.equals(PeopleSelector.SELECTED)) {
                    //Send broadcast to People Selector
                    Intent intent = new Intent(PeopleSelector.PeopleDeselectedReceiver.ACTION);
                    intent.putExtra(PeopleSelector.PeopleDeselectedReceiver.ACTION, person.getId());
                    context.sendBroadcast(intent);

                    //Remove selected person from list
                    people.remove(position);
                    notifyItemRemoved(position);
                }
            }
        }
    }

    // Pass in the contact array into the constructor
    public PeopleAdapter(List<Person> people, String purpose) {
        this.people = people;
        this.purpose = purpose;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView;
        if(purpose.equals(PeopleSelector.SELECTED))
            contactView = inflater.inflate(R.layout.layout_grid_view_element, parent, false);
        else
            contactView = inflater.inflate(R.layout.layout_recycle_view_element, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PeopleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Person person = people.get(position);

        // Set item views based on your views and data model
        TextView name = viewHolder.nameTextView;
        name.setText(person.getUsername());
        TextView role = viewHolder.roleTextView;
        if(person.getRole() != null)
            role.setText(person.getRole());
        else if(role != null)
            role.setVisibility(View.GONE);

        //Profilbild setzen
        ImageView picture = viewHolder.profilePicture;
        if(person.getImage() != null)
            new ImageLoader(person.getImage(), viewHolder.context, picture);
        else
            picture.setImageResource(R.mipmap.ic_launcher);

        ImageView mood = viewHolder.personMood;
        if(person.getSentiment() != null) {
            switch (person.getSentiment().getPolarity()) {
                case Person.POSITIVE:
                    mood.setImageResource(R.drawable.ic_sun_outline);
                    break;
                case Person.NEUTRAL:
                    mood.setImageResource(R.drawable.ic_overcast_outline);
                    break;
                case Person.NEGATIVE:
                    mood.setImageResource(R.drawable.ic_rain_outline);
                    break;
            }
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return people.size();
    }
}