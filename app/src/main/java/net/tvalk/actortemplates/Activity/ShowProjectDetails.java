package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.R;

/**
 * Created by Gebruiker on 11-4-2017.
 */

public class ShowProjectDetails extends AppCompatActivity {
    String key;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_project_details);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
    }
}
