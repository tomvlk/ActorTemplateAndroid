package net.tvalk.actortemplates.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Classes.Person;
import net.tvalk.actortemplates.R;

public class InsertPerson extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_person);

        Button b = (Button) findViewById(R.id.actorAdd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }

    protected void insertUser() {
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
            Toast.makeText(this, "Naam en functie moeten een naam hebben!", Toast.LENGTH_SHORT).show();
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
