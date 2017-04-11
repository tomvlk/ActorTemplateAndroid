package net.tvalk.actortemplates.Adapter;

import android.content.Intent;
//import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Activity.ShowContent;
import net.tvalk.actortemplates.Activity.ShowProjectDetails;
import net.tvalk.actortemplates.Classes.Project;
import net.tvalk.actortemplates.R;

/**
 * Created by Gebruiker on 7-4-2017.
 */

public class ProjectAdapter extends FirebaseRecyclerAdapter<Project, ProjectAdapter.ProjectViewHolder> {

    public ProjectAdapter() {
        super(Project.class, R.layout.project_row, ProjectAdapter.ProjectViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects"));
    }


    @Override
    protected void populateViewHolder(ProjectViewHolder viewHolder, Project model, int position) {
        viewHolder.name.setText(model.getName());
        viewHolder.description.setText(model.getName());
        viewHolder.p = model;
        viewHolder.key = getRef(position).getKey();
    }


    public static class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView description, name;
        public Project p;
        public String key;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.Project_description);
            name = (TextView) itemView.findViewById(R.id.Project_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ShowProjectDetails.class);
            intent.putExtra("name", p.getName());
            intent.putExtra("decription", p.getDescription());
            intent.putExtra("key", key);
            v.getContext().startActivity(intent);
        }
    }
}