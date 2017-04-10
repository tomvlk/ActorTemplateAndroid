package net.tvalk.actortemplates.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import net.tvalk.actortemplates.Classes.User;
import net.tvalk.actortemplates.R;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 7-4-2017.
 */

public class EntriesAdapter2 extends RecyclerView.Adapter<MyHolder> {
Context c;
    ArrayList<User> users;

    public EntriesAdapter2(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
String name  = users.get((position)).getName();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}