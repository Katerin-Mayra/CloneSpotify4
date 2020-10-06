package com.example.katerin.clonespotify4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    //parte1.7 permisos
    Button takePhoto;
    //parte1.4 permisos
    static final  int PERMISION_CODE=10;
    //parte1.9 permisos
    static final int code_camera=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //////////resivo y muestro los parametros
        /*Bundle bundle=this.getIntent().getExtras();
        String backupAgentName= bundle.getString("backupAgentName");
        String data=bundle.getString("data");
        Integer numer=bundle.getInt("number");

        Toast.makeText(this, " backupAgentName= "+backupAgentName +" data= "+ data + " numer= "+ numer.toString(),Toast.LENGTH_LONG).show();*/


        //parte 1.8 permisos
        loadComponets();


        //parte 1.1 de permisos
        /*
        if (checkPermission(Manifest.permission.CAMERA)){
            Toast.makeText(this,"TIENES PERMISOS ☺ " ,Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(this,"NO TIENES PERMISOS ☺ " ,Toast.LENGTH_LONG).show();
            requestPermission();
        }*/

    }
    //parte1.8.1 permisos
    private void loadComponets() {
        takePhoto=this.findViewById(R.id.photobtn);
        takePhoto.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        //parte 1.1 de permisos
        if (checkPermission(Manifest.permission.CAMERA)){
            callCamera();
          //  Toast.makeText(this,"TIENES PERMISOS ☺ " ,Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(this,"NO TIENES PERMISOS ☺ " ,Toast.LENGTH_LONG).show();
            requestPermission();
        }

    };

    //parte1.5 permisos android manifest.xml

    //PARTE1.6 PERMISOS
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISION_CODE){
            if(permissions.length>0){
                if(grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    callCamera();
                }else {
                    Toast.makeText(this,"NO PODEMOS SEGUIR CON EL REGISTRO POR QEU ES NECESARIA UNA FOTO TUYA =-( ",Toast.LENGTH_LONG).show();

                }

            }

        }
    }

    //parte1.2 de permisos
    private  void requestPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            this.requestPermissions(new String[]{Manifest.permission.CAMERA},PERMISION_CODE);
        }else {

        }
    }
    ///PERMISOS 1
        public Boolean checkPermission(String permission){
            int result = this.checkCallingOrSelfPermission(permission);
            return result== PackageManager.PERMISSION_GRANTED;




    }
    private void callCamera(){
        Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        if(camera.resolveActivity(this.getPackageManager())!=null){
            this.startActivityForResult(camera,code_camera);

        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code_camera && resultCode == RESULT_OK ){
            Bundle photo= data.getExtras();
            Bitmap imageBitmap =(Bitmap) photo.get("data");
            ImageView img=this.findViewById(R.id.imageView2);
            img.setImageBitmap(imageBitmap);
        }
    }
}