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
import de.sharknoon.slash.R;

public class SelectedPeopleAdapter extends RecyclerView.Adapter<SelectedPeopleAdapter.ViewHolder> {

    private List<Person> people;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView profilePicture;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            profilePicture = itemView.findViewById(R.id.person_picture);
            nameTextView = itemView.findViewById(R.id.person_name);
        }
    }

    public SelectedPeopleAdapter(List<Person> people) {
        this.people = people;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public SelectedPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.layout_recycle_view_element, parent, false);

        // Return a new holder instance
        SelectedPeopleAdapter.ViewHolder viewHolder = new SelectedPeopleAdapter.ViewHolder(context, contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SelectedPeopleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Person person = people.get(position);

        // Set item views based on your views and data model
        TextView name = viewHolder.nameTextView;
        name.setText(person.getUsername());
        //todo Profilbild setzen
        ImageView picture = viewHolder.profilePicture;
        picture.setImageResource(R.drawable.ic_person);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return people.size();
    }
}
