package com.example.grant.groupk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class add extends Activity {
    private EditText title, description;
    Uri imageUrl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private static final int PICK_IMAGE = 100;
    Spinner spType;
    Button button;
    String tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        button = (Button)findViewById(R.id.addImage_Button);
        title = (EditText)findViewById(R.id.editTitle);
        description = (EditText)findViewById(R.id.editDescription);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Artifacts");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("gs://groupk-master.appspot.com/artifact/a.png");
        spType = (Spinner)findViewById(R.id.spinnerType);
        tp = "";
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openPhoto();
            }
        });

        setSp();
    }

    public void setSp(){
        spType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tp = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }


    private void openPhoto(){
        Intent photoGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoGallery, PICK_IMAGE);
    }

    public void onClickSubmit(View v){
        final String titleT, descriptionD;

        titleT = title.getText().toString().trim();
        descriptionD = description.getText().toString().trim();

        if (titleT.equals("") || titleT == null)
        {
            Toast entryError = Toast.makeText(add.this, "title cannot be empty", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else if (imageUrl.toString().isEmpty() || imageUrl.toString() == null)
        {
            Toast entryError = Toast.makeText(add.this, "add an image", Toast.LENGTH_SHORT);
            entryError.show();
        }
        else{


            DatabaseReference newPost = mDatabase.push();
            newPost.child("title").setValue(titleT);
            newPost.child("description").setValue(descriptionD);
            newPost.child("imageURI").setValue(imageUrl.toString());
            newPost.child("type").setValue(tp);

            Intent i = new Intent (add.this, CatalogMain.class);
            startActivity(i);

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUrl = data.getData();


        }
    }


}
