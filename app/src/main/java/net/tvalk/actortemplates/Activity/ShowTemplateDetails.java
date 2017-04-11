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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Adapter.PersonAdapter;
import net.tvalk.actortemplates.Adapter.TemplateAdapter;
import net.tvalk.actortemplates.R;

/**
 * Created by Gebruiker on 11-4-2017.
 */

public class ShowTemplateDetails extends AppCompatActivity {
    String key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_template_details);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        TextView et1 = (TextView) findViewById(R.id.Project_name_edit);
        TextView et2 = (TextView) findViewById(R.id.Project_description_edit);
        recyclerView = (RecyclerView) findViewById(R.id.Template_detail_recycler_view);

        mAdapter = new PersonAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowTemplateDetails.this, InsertTemplate.class );
                i.putExtra("key", key);
                startActivity(i);
            }
        });

    }
}
