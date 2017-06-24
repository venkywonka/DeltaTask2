package com.example.android.deltatask2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int delpos;
    Drawable d;
    String caption;
    ArrayList<Album> albumList = new ArrayList<Album>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterCaption(View v){
        EditText cap = (EditText) findViewById(R.id.caption);
        caption = cap.getText().toString();
        cap.setText("");
        Toast.makeText(this,"Your caption has been recorded",Toast.LENGTH_LONG).show();


    }

    public void openCamera(View v){
        cameraIntent();
    }

    public void openGallery(View v){
        galleryIntent();
    }

    public void cameraIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    public void galleryIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2){
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                d= new BitmapDrawable(getResources(),bm);
                albumList.add(new Album(caption,d));
                setListView();

            }

            else if (requestCode == 1){
                Bundle extras = data.getExtras();
                Bitmap bm = (Bitmap) extras.get("data");
                d= new BitmapDrawable(getResources(),bm);
                albumList.add(new Album(caption,d));
                setListView();}

        }
    }

    public void setListView(){

        final AlbumAdapter adapter = new AlbumAdapter(this,albumList);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Do you want to delete item?");
                alertDialogBuilder.setPositiveButton("yes",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                    albumList.remove(position);
                                    listView.setAdapter(adapter);
                                    Toast.makeText(MainActivity.this,"Deleted album",Toast.LENGTH_SHORT).show();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;}


        });




    }







}
