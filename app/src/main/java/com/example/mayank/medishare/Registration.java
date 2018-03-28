package com.example.mayank.medishare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static android.R.attr.data;

public class Registration extends AppCompatActivity {

    ImageView image;
    int count = 1;
    int PICK_IMAGE_REQUEST = 71;
    EditText organisation_name, phone, email, password,location;
    Button procced;
    Uri filePath;
    String newValue="my name";
    private static final int GALLERY_REQUEST = 1;
    DatabaseReference mDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;
    ProgressDialog bar;

    String mCurrentPhotoPath;

    String organisation_name_field, phone_field, email_field, password_field,location_field;
    String  downloadUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        organisation_name = (EditText) findViewById(R.id.organisation_name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        location = (EditText) findViewById(R.id.location);
        image = (ImageView) findViewById(R.id.uploadedimage);
        procced = (Button) findViewById(R.id.submit);
        bar = new ProgressDialog(getApplicationContext());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blogs");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();




        procced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

                organisation_name_field = organisation_name.getText().toString().trim();
                phone_field = phone.getText().toString().trim();
                email_field = email.getText().toString().trim();
                password_field = password.getText().toString().trim();
                location_field = location.getText().toString().trim();



                final HashMap<String, String> data = new HashMap<String, String>();
                data.put("organisation_name", organisation_name_field);
                data.put("phone", phone_field);
                data.put("email", email_field);
                data.put("password", password_field);
                data.put("location", location_field);
                data.put("image",downloadUri);
                data.put("New value ",newValue);

                mDatabase.push().setValue(data);


                Intent i1 = new Intent("com.example.mayank.medishare.Main2Activity");
                startActivity(i1);


            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }



    public void uploadImage()
    {


        if(filePath != null)
        {

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            downloadUri=taskSnapshot.getDownloadUrl().toString();
                            Toast.makeText(getApplicationContext(),"Url of" +downloadUri,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                        }
                    });
        }
    }

}