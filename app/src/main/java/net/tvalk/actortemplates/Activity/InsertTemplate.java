package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.R;

import static android.R.attr.description;
import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.name;
import static com.google.android.gms.auth.api.credentials.PasswordSpecification.da;
import static net.tvalk.actortemplates.R.id.fab;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class InsertTemplate extends AppCompatActivity {
    EditText nametekst, descriptiontekst;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String key;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_template);
        nametekst = (EditText) findViewById(R.id.Project_name);
        descriptiontekst = (EditText) findViewById(R.id.Project_description);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
insertTemplate();
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.fab) {
//            String sname = nametekst.getText().toString();
//            if (sname.matches("")) {
//                Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
//            } else {
//                    Template t = new Template();
//                    t.setName(nametekst.getText().toString());
//                    t.setDescription(descriptiontekst.getText().toString());
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

    protected void insertTemplate() {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        nametekst = (EditText) findViewById(R.id.editText_Actor);
        descriptiontekst = (EditText) findViewById(R.id.editText2_beschrijving);
        String naam = nametekst.toString();
        String description = descriptiontekst.toString();
        if (naam.matches("")||description.matches("")) {
            Toast.makeText(this, "You did not enter an actor name or discription", Toast.LENGTH_SHORT).show();
        }
        else if(checkUnique(naam)== true){
            Toast.makeText(this, "Name is not unique", Toast.LENGTH_SHORT).show();
        }
        else{
            Template t = new Template();
            t.setName(nametekst.getText().toString());
            t.setDescription(descriptiontekst.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("projects").child(key).child("templates").push().setValue(t);
        }
    }
    public boolean checkUnique(String naam){
        boolean b = true;
        return b;
    }
}
