package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Adapter.PersonAdapter;
import net.tvalk.actortemplates.Adapter.PersonInTemplateAdapter;
import net.tvalk.actortemplates.R;

import static android.R.attr.key;

/**
 * Created by Gebruiker on 11-4-2017.
 */

public class InsertPersonIntemplate extends AppCompatActivity {
    String template_key, project_key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private RecyclerView recyclerView;
    private PersonInTemplateAdapter mAdapter;
    String name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_person_in_template);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        Intent intent = getIntent();
        name = intent.getStringExtra("template_name");
        description = intent.getStringExtra("template_description");
        template_key = intent.getStringExtra("template_key");
        project_key = intent.getStringExtra("project_key");
        recyclerView = (RecyclerView) findViewById(R.id.Person_recycler_view);

        mAdapter = new PersonInTemplateAdapter(project_key, template_key);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertPersonIntemplate.this, InsertPerson.class );
                i.putExtra("project_key", project_key);
                startActivity(i);
            }
        });


    }
}
