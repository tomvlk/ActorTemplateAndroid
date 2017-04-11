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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.tvalk.actortemplates.Activity.SignInActivity;
import net.tvalk.actortemplates.Adapter.ProjectAdapter;
import net.tvalk.actortemplates.R;

public class ShowProjects extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private String mUsername;
    private String mPhotoUrl;
    private String TAG = "ShowProjects";
    private RecyclerView recyclerView;
    private ProjectAdapter mAdapter;

    public static final String ANONYMOUS = "anonymous";
    private GoogleApiClient mGoogleApiClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_projects);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mUsername = ANONYMOUS;

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
//        if (mFirebaseUser == null) {
//            // Not signed in, launch the Sign In activity
//            startActivity(new Intent(this, SignInActivity.class));
//            finish();
//            return;
//        } else {
//            mUsername = mFirebaseUser.getDisplayName();
//            Log.d("ShowDiaries", mUsername);
//            Log.d("ShowDiaries", mFirebaseUser.getUid());
//            if (mFirebaseUser.getPhotoUrl() != null) {
//                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
//            }
//        }

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API)
//                .build();


        //add a OnItemClickListener
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ProjectAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }



    @Override
    public void onResume(){
        super.onResume();


    }

    @Override
    public void onPause(){
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.m.menu_show_diaries, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if(id==R.id.Project_detail){
//            startActivity(new Intent(this, ShowProjectDetails.class));
//            return true;
//        }
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////        if (id == R.id.sign_out_menu) {
////            mFirebaseAuth.signOut();
////            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
////            mUsername = ANONYMOUS;
////            startActivity(new Intent(this, SignInActivity.class));
////            return true;
////        }
//
////
////
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}