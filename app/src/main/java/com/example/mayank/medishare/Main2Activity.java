package com.example.mayank.medishare;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    RecyclerView mBlogList;
    DatabaseReference mDatabase;
    ArrayList<Blog> list = new ArrayList<Blog>();
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blogs");

        mBlogList = (RecyclerView) findViewById(R.id.recyler);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue(Blog.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Error in loading data", Toast.LENGTH_SHORT).show();
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent("com.example.mayank.medishare.Registration" );
                startActivity(i1);

            }
        });

    }


    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(final BlogViewHolder viewHolder, Blog model, int position) {

                 final String user_id = getRef(position).getKey();
                viewHolder.setOrganisation_name(model.getOrganisation_name());
                viewHolder.setDescription(model.getDescription());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       // Toast.makeText(getApplicationContext(), "" + user_id, Toast.LENGTH_SHORT).show();

                        Intent i1=new Intent("com.example.mayank.medishare.ViewPage");
                        i1.putExtra("ref",user_id);
                        startActivity(i1);

                    }
                });


            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


        }

        public void setOrganisation_name(String Organisation_name) {
            TextView post_name = (TextView) mView.findViewById(R.id.post_organisation_name);
            post_name.setText(Organisation_name);
            //   view_name=post_name.getText().toString().trim();
            Log.i("Data for ", post_name.getText().toString());
        }

        public void setDescription(String Description_value) {
            TextView post_description = (TextView) mView.findViewById(R.id.post_description);
            post_description.setText(Description_value);

            // view_description=post_description.getText().toString().trim();
        }


    }
}