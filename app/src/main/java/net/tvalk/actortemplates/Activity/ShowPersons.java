package net.tvalk.actortemplates.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.tvalk.actortemplates.Classes.Person;
import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.Classes.User;
import net.tvalk.actortemplates.R;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class ShowPersons extends AppCompatActivity {
    String key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_persons);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        mDatabase.child("projects").child("-Kh2cL5pGrof4LwnOsDu").child("persons").child("-KhHil-HGZhNTbO4R6kf").getRef().addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView tv1 = (TextView) findViewById(R.id.Person_name);
                        TextView tv2 = (TextView) findViewById(R.id.Person_function);
                        TextView tv3 = (TextView) findViewById(R.id.Person_email);
                        TextView tv4 = (TextView) findViewById(R.id.Person_tel);
                        TextView tv5 = (TextView) findViewById(R.id.Person_photo);
                        TextView tv6 = (TextView) findViewById(R.id.Person_description);

                        Person entry = dataSnapshot.getValue(Person.class);
                        tv1.setText(entry.getName());
                        tv2.setText(entry.getDescription());
                        tv3.setText(entry.getEmail());
                        tv4.setText(entry.getPhone());
                        tv5.setText(entry.getPhoto());
                        tv6.setText(entry.getFunction());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }
}