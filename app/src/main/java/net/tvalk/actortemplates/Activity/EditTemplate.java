package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Classes.Template;
import net.tvalk.actortemplates.R;

import java.util.HashMap;
import java.util.Map;

public class EditTemplate extends AppCompatActivity {
    TextView nametekst, descriptiontekst;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String key;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.insert_template);
        nametekst = (EditText) findViewById(R.id.editText_Actor);
        descriptiontekst = (EditText) findViewById(R.id.editText2_beschrijving);
        nametekst.setText(intent.getStringExtra("template_name"));
        descriptiontekst.setText(intent.getStringExtra("template_description"));
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
        nametekst = (TextView) findViewById(R.id.editText_Actor);
        descriptiontekst = (TextView) findViewById(R.id.editText2_beschrijving);
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
            Map<String,Object> taskMap = new HashMap<String,Object>();
            taskMap.put("name", t.getName());
            taskMap.put("description", t.getDescription());
            FirebaseDatabase.getInstance().getReference().child("projects").child(intent.getStringExtra("project_key")).child("templates").child(intent.getStringExtra("template_key")).updateChildren(taskMap);
        }
    }
    public boolean checkUnique(String naam){
        boolean b = false;
        return b;
    }
}
