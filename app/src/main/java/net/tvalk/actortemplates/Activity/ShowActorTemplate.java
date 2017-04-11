package net.tvalk.actortemplates.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Adapter.ProjectAdapter;
import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.Classes.User;
import net.tvalk.actortemplates.R;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class ShowActorTemplate extends AppCompatActivity {
    DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();

    private ProjectAdapter mAdapter;
    private FirebaseAuth mFirebaseAuth;
    ArrayList<User> templates = new ArrayList<>();

    EditText naamEditText, beschrijvingEditText;
    Button savebutton;
    RecyclerView rv;
    ProjectAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        rv = (RecyclerView) findViewById(R.id.recycler_view_templaterinos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        this.refreshData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
    }

    private void displayDialog(){
        Dialog d = new Dialog(this);
        d.setTitle("Save online");

        naamEditText = (EditText) d.findViewById(R.id.template_name);
        beschrijvingEditText = (EditText) d.findViewById(R.id.template_description);
        savebutton = (Button) d.findViewById(R.id.saveBtn);

//            savebutton.setOnClickListener(new View.OnClickListener(){
//                saveOnline(naamEditText.getText().toString, beschrijvingEditText.getText().toString);
//            });
        d.show();


    }
    private void saveOnline(String naam, String beschrijving){
        Template t = new Template();
        t.setName(naam);
        t.setDescription(beschrijving);
        mDatabase.child("templates").push().setValue(t);
    }


    public void refreshData(){
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getUpdates(DataSnapshot datasnapshot) {
        templates.clear();
        for (DataSnapshot ds : datasnapshot.getChildren()) {
            Template t = new Template();
            t.setName(ds.getValue(Template.class).getName());
            t.setDescription(ds.getValue(Template.class).getDescription());
//            templates.add(t);
        }
        if(templates.size()>0)
        {
//            adapter=new ProjectAdapter(ShowActorTemplate.this, templates);
            rv.setAdapter(adapter);
        }
    }
}