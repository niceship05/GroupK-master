package com.example.grant.groupk;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CatalogMain extends AppCompatActivity {

    private RecyclerView catalogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_main);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Artifacts");
        catalogList = (RecyclerView) findViewById(R.id.catalogRecycle);


        catalogList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        catalogList.setLayoutManager(linearLayoutManager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity(new Intent (this, Homepage.class));
                return true;
            case R.id.action_catalog:
                startActivity(new Intent (this, CatalogMain.class));
                return true;
            case R.id.action_profile:
                startActivity(new Intent (this, ProfilePage.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent (this, Setting.class));
                return true;
            case R.id.action_chat:
                startActivity(new Intent (this, ChatRoom.class));
                return true;
            case R.id.action_calender:
                startActivity(new Intent (this, CalendarActivity.class));
                return true;
            case R.id.action_map:
                startActivity(new Intent (this, MapsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onAddClick(View v) {
        if (v.getId() == R.id.addButton)
        {
            Intent i = new Intent (CatalogMain.this, add.class);
            startActivity(i);
        }
    }

    public void onDetailClick(View v){
        if (v.getId() == R.id.toDetail)
        {
            Intent i = new Intent (CatalogMain.this, Artifact_detail.class);
            startActivity(i);
        }
    }

    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Post, CatalogMain.PostViewHolder> FBRA = new FirebaseRecyclerAdapter<Post, CatalogMain.PostViewHolder>(
                Post.class,
                R.layout.catalog_tem,
                CatalogMain.PostViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(CatalogMain.PostViewHolder viewHolder, Post model, int position) {
                viewHolder.setTitle(model.getTitle());
            }


        };
        catalogList.setAdapter(FBRA);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PostViewHolder(View v){
            super(v);
            mView = v;
        }
        public void setTitle (String title){
            TextView title_content = (TextView) mView.findViewById(R.id.Title);
            title_content.setText(title);
        }



    }

}
