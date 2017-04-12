package net.tvalk.actortemplates.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.tvalk.actortemplates.Adapter.PersonAdapter;
import net.tvalk.actortemplates.R;

import static android.R.attr.key;
import static net.tvalk.actortemplates.Activity.ShowProjects.ANONYMOUS;

/**
 * Created by Gebruiker on 11-4-2017.
 */

public class ShowTemplateDetails extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    String template_key, project_key, mUsername, mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;
    private String TAG = "ShowTemplateDetails";
    String name, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_template_details);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        Intent intent = getIntent();
        name = intent.getStringExtra("template_name");
        description = intent.getStringExtra("template_description");
        template_key = intent.getStringExtra("template_key");
        project_key = intent.getStringExtra("project_key");
        TextView tv1 = (TextView) findViewById(R.id.Template_name_edit);
        TextView tv2 = (TextView) findViewById(R.id.Template_description_edit);
        tv1.setText(name);
        tv2.setText(description);
        TextView et1 = (TextView) findViewById(R.id.Project_name_edit);
        TextView et2 = (TextView) findViewById(R.id.Project_description_edit);
        recyclerView = (RecyclerView) findViewById(R.id.Template_detail_recycler_view);
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





        mAdapter = new PersonAdapter(template_key);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                project_key = intent.getStringExtra("project_key");
                Intent i = new Intent(ShowTemplateDetails.this, InsertPersonIntemplate.class );
                i.putExtra("project_key", project_key);
                i.putExtra("template_key", template_key);
                startActivity(i);
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_add);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                template_key = intent.getStringExtra("template_key");
                project_key = intent.getStringExtra("project_key");
                Intent i = new Intent(ShowTemplateDetails.this, EditTemplate.class );
                i.putExtra("project_key", project_key);
                i.putExtra("template_key", template_key);
                i.putExtra("template_name", name);
                i.putExtra("template_description", description);
                startActivity(i);
            }
        });

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
