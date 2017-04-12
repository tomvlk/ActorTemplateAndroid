package net.tvalk.actortemplates.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import static net.tvalk.actortemplates.Activity.ShowProjects.ANONYMOUS;

/**
 * Created by Gebruiker on 5-4-2017.
 */

public class ShowTemplates extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();
    private String mUsername;
    private FirebaseUser mFirebaseUser;
    private String mPhotoUrl;
    private String TAG = "ShowProjects";
    private ProjectAdapter mAdapter;
    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    ArrayList<User> templates = new ArrayList<>();

    EditText naamEditText, beschrijvingEditText;
    Button savebutton;
    RecyclerView rv;
    ProjectAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mUsername = ANONYMOUS;

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            Log.d("ShowDiaries", mUsername);
            Log.d("ShowDiaries", mFirebaseUser.getUid());
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

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

        naamEditText = (EditText) d.findViewById(R.id.Template_name);
        beschrijvingEditText = (EditText) d.findViewById(R.id.Template_description);
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
//            adapter=new ProjectAdapter(ShowTemplates.this, templates);
            rv.setAdapter(adapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem profilepic = (MenuItem) menu.getItem(R.id.profile_picture);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menuShowProject){
            startActivity(new Intent(this, ShowProjects.class));
            return true;
        }
        if(id==R.id.menuLogout){
            mFirebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            mUsername = ANONYMOUS;
            startActivity(new Intent(this, SignInActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

}