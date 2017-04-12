package net.tvalk.actortemplates.Adapter;

import android.content.Intent;
//import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Activity.ShowPersons;
import net.tvalk.actortemplates.Classes.Person;
import net.tvalk.actortemplates.R;

/**
 * Created by Gebruiker on 7-4-2017.
 */

public class PersonAdapter extends FirebaseRecyclerAdapter<Person, PersonAdapter.PersonViewHolder> {
    private String project_key;
    private String template_key;

    public PersonAdapter(String projectkey, String templatekey) {
        super(Person.class, R.layout.person_row, PersonAdapter.PersonViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects").child("-Kh2cL5pGrof4LwnOsDu").child("persons"));
        this.project_key = projectkey;
        this.template_key = templatekey;
    }


    @Override
    protected void populateViewHolder(PersonViewHolder viewHolder, Person model, int position) {
        viewHolder.name.setText(model.getName());
        viewHolder.description.setText(model.getName());
        viewHolder.email.setText(model.getEmail());
        viewHolder.phone.setText(model.getPhone());
        viewHolder.function.setText(model.getFunction());
        viewHolder.p = model;
        viewHolder.project_key = project_key;
        viewHolder.key = getRef(position).getKey();
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView description, name, email, function, phone;
        public Person p;
        public String key, key2;
        String project_key;
        ImageView photo;

        public PersonViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.Person_description);
            name = (TextView) itemView.findViewById(R.id.Person_name);
            email = (TextView) itemView.findViewById(R.id.Person_email);
            function = (TextView) itemView.findViewById(R.id.Person_function);
            photo = (ImageView) itemView.findViewById(R.id.Person_photo);
            phone = (TextView) itemView.findViewById(R.id.Person_tel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ShowPersons.class);
            intent.putExtra("name", p.getName());
            intent.putExtra("description", p.getDescription());
            intent.putExtra("email", p.getDescription());
            intent.putExtra("phone", p.getDescription());
            intent.putExtra("photo", p.getDescription());
            intent.putExtra("function", p.getDescription());
            intent.putExtra("project_key", project_key);

            intent.putExtra("key", key);
            v.getContext().startActivity(intent);
        }
    }
}