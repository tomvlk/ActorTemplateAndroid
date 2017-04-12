package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.tvalk.actortemplates.Classes.Person;
import net.tvalk.actortemplates.R;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class ShowPersons extends AppCompatActivity {
    String project_key, template_key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_person);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        project_key = intent.getStringExtra("project_key");
        template_key = intent.getStringExtra("template_key");
        mDatabase.child("projects").child(project_key).child("persons").getRef().addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView tv1 = (TextView) findViewById(R.id.editText21_naam);
                        TextView tv2 = (TextView) findViewById(R.id.editText12_functie);
                        TextView tv3 = (TextView) findViewById(R.id.editText17_email);
                        TextView tv4 = (TextView) findViewById(R.id.editText22_telefoon);
//                        ImageView tv5 = (ImageView) findViewById(R.id.Person_photo);
                        TextView tv6 = (TextView) findViewById(R.id.edittext_aantekeningen);
                        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                updatePerson();
                                Intent i = new Intent(ShowPersons.this, InsertTemplate.class );
                                i.putExtra("projectkey", project_key);
                                startActivity(i);
                            }
                        });
                        Person entry = dataSnapshot.getValue(Person.class);
                        tv1.setText(entry.getName());
                        tv2.setText(entry.getFunction());
                        tv3.setText(entry.getEmail());
                        tv4.setText(entry.getPhone());
////                        tv5.setText(entry.getPhoto());
                        tv6.setText(entry.getDescription());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                    public void updatePerson(){
                        EditText naamedit = (EditText) findViewById(R.id.editText21_naam);
                        EditText functieedit = (EditText) findViewById(R.id.editText12_functie);
                        EditText emailedit = (EditText) findViewById(R.id.editText17_email);
                        EditText teledit = (EditText) findViewById(R.id.editText22_telefoon);
                        EditText aantekeningenedit = (EditText) findViewById(R.id.edittext_aantekeningen);
                        String naam, functie, email, telefoon, aantekeningen;
                        naam = naamedit.getText().toString();
                        functie = functieedit.getText().toString();
                        email = emailedit.getText().toString();
                        telefoon = teledit.getText().toString();
                        aantekeningen = aantekeningenedit.getText().toString();
                        if (naam.matches("")|| functie.matches("")) {
//                            Toast.makeText(this, "Naam en functie moeten een naam hebben!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Person p = new Person();
                            p.setName(naam);
                            p.setEmail(email);
                            p.setDescription(aantekeningen);
                            p.setPhoto("testphoto.png");
                            p.setPhone(telefoon);
                            p.setFunction(functie);
//            mDatabase.child("persons").push().setValue(p);
                        }
                    }
                }
        );
    }
}