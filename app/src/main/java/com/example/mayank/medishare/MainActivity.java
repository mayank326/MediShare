package com.example.mayank.medishare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

public static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser= mAuth.getCurrentUser();

        RelativeLayout rlayout=(RelativeLayout)findViewById(R.id.mainLayout);

        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(currentUser==null)
                {
                    Intent i1=new Intent("com.example.mayank.medishare.Phone");
                    startActivity(i1);
                    finish();
                }

                else {


                    Intent i1 = new Intent("com.example.mayank.medishare.Main2Activity");
                    startActivity(i1);
                    finish();
                }
                //onStart();



            }
        });

    }




   public void onStart() {
        super.onStart();


       /* else
        {
            Intent i2=new Intent("com.example.mayank.medishare.Main2Activity");
            startActivity(i2);

        }*/
    }



}
