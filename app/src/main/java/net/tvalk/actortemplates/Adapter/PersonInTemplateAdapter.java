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
import net.tvalk.actortemplates.Activity.ShowProjectDetails;
import net.tvalk.actortemplates.Classes.Person;
import net.tvalk.actortemplates.Classes.PersonTemplate;
import net.tvalk.actortemplates.R;

import static android.R.attr.key;

/**
 * Created by Gebruiker on 7-4-2017.
 */

public class PersonInTemplateAdapter extends FirebaseRecyclerAdapter<Person, PersonInTemplateAdapter.PersonViewHolder> {
    String project_key, template_key;

    public PersonInTemplateAdapter(String project_key, String template_key) {
        super(Person.class, R.layout.person_template_row, PersonInTemplateAdapter.PersonViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects").child(project_key).child("persons"));
        this.project_key = project_key;
        this.template_key = template_key;
    }


    @Override
    protected void populateViewHolder(PersonViewHolder viewHolder, Person model, int position) {
        viewHolder.name.setText(model.getName());
        viewHolder.description.setText(model.getName());
        viewHolder.email.setText(model.getEmail());
        viewHolder.phone.setText(model.getPhone());
        viewHolder.function.setText(model.getFunction());
        viewHolder.p = model;
        viewHolder.person_key = getRef(position).getKey();
        viewHolder.project_key = project_key;
        viewHolder.template_key = template_key;
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView description, name, email, function, phone;
        public Person p;
        public String project_key, template_key, person_key;
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
            PersonTemplate p = new PersonTemplate();
            p.setMember(true);
            FirebaseDatabase.getInstance().getReference().child("projects").child(project_key).child("templates").child(template_key).child("persons").child(person_key).setValue(p);
        }
    }
}