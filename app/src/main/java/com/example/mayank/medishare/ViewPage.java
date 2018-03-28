package com.example.mayank.medishare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ViewPage extends AppCompatActivity {

   TextView t1, t2,t3,t4;
    String ref;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Button i1;
    String phoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        Intent profile = this.getIntent();
        ref = profile.getStringExtra("ref");

        t1 = (TextView) findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView5);
        t3 = (TextView) findViewById(R.id.textView10);
        t4=(TextView)findViewById(R.id.textView11);
        i1=(Button)findViewById(R.id.contact);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Blogs").child(ref);
        databaseReference.keepSynced(true);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                phoneno=dataSnapshot.child("phone").getValue().toString();
               t1.setText(dataSnapshot.child("organisation_name").getValue().toString());
              t2.setText(phoneno);
                t3.setText(dataSnapshot.child("location").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(Intent.ACTION_DIAL);
                i1.setData(Uri.parse(String.valueOf(Uri.parse("tel:" +phoneno))));
                startActivity(i1);
            }
        });
    }
}