package net.tvalk.actortemplates.Adapter;

import android.content.Intent;
//import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Activity.ShowProjectDetails;
import net.tvalk.actortemplates.Activity.ShowTemplateDetails;
import net.tvalk.actortemplates.Activity.ShowTemplates;
import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.R;

import static android.R.attr.key;

/**
 * Created by Gebruiker on 7-4-2017.
 */

public class TemplateAdapter extends FirebaseRecyclerAdapter<Template, TemplateAdapter.TemplateViewHolder> {
    private String project_key;



    public TemplateAdapter(String project_ke) {
        super(Template.class, R.layout.template_row, TemplateAdapter.TemplateViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("projects").child(project_ke).child("templates"));
        setProject_key(project_ke);
    }
    public void setProject_key(String project_key) {
        this.project_key = project_key;
    }

    @Override
    protected void populateViewHolder(TemplateViewHolder viewHolder, Template model, int position) {
        viewHolder.template_name.setText(model.getName());
        viewHolder.template_description.setText(model.getDescription());
        viewHolder.t = model;
        viewHolder.template_key = getRef(position).getKey();
        viewHolder.project_key = project_key;
    }


    public static class TemplateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView template_description, template_name;
        public Template t;
        public String project_key, template_key;
        public boolean archived;

        public TemplateViewHolder(View itemView) {
            super(itemView);
            template_description = (TextView) itemView.findViewById(R.id.Template_description);
            template_name = (TextView) itemView.findViewById(R.id.Template_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ShowTemplateDetails.class);
            intent.putExtra("template_key", template_key);
            intent.putExtra("template_name", t.getName());
            intent.putExtra("template_description", t.getDescription());
            intent.putExtra("project_key", project_key);
            v.getContext().startActivity(intent);
        }
    }
}