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

import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.Classes.User;
import net.tvalk.actortemplates.R;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();

    private EntriesAdapter2 mAdapter;
    private FirebaseAuth mFirebaseAuth;
    ArrayList<User> templates = new ArrayList<>();

    EditText usernaamEditText;
    Button savebutton;
    RecyclerView rv;
    EntriesAdapter2 adapter;
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

        usernaamEditText = (EditText) d.findViewById(R.id.user_name);
        savebutton = (Button) d.findViewById(R.id.saveBtn);

//            savebutton.setOnClickListener(new View.OnClickListener(){
//                saveOnline(naamEditText.getText().toString, beschrijvingEditText.getText().toString);
//            });
        d.show();


    }
    private void saveOnline(String naam, String beschrijving){
        User u = new User();
        u.setName(naam);
        mDatabase.child("users").push().setValue(u);
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
            User t = new User();
            t.setName(ds.getValue(Template.class).getName());
            templates.add(t);
        }
        if(templates.size()>0)
        {
            adapter=new EntriesAdapter2(MainActivity.this, templates);
            rv.setAdapter(adapter);
        }
    }
}