package com.example.katerin.clonespotify4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton,registerButton;
    static final int code_camera=999;
    private MainActivity root=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "OnCreate",Toast.LENGTH_LONG).show();
        loadcomponents();

    }

    private void loadcomponents() {
        loginButton=this.findViewById(R.id.login);
        registerButton=this.findViewById(R.id.register);
        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity =new Intent(root,RegisterUser.class);
                root.startActivity(registerActivity);

            }
        }));

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent =new Intent(root,DashBoard.class);

                //////////////////////////Enviar parametros a otra actividad
               /* intent.putExtra("backupAgentName",root.getApplicationInfo().backupAgentName);
                intent.putExtra("data","soy la informacion de la actividad MainActivity");
                intent.putExtra("number",26);*/

                ////////////////////////////
                //////////////UNIRSE A OTRA APLICACION
                ///Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:71834234"));

                //////////BUSQUEDA EN GOOGLE
                /*Intent intent =new Intent (Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"Intents en android");*/
       ////pagina
                //Intent intent =new Intent (Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("https://www.twitch.tv/"));
                //root.startActivity(intent);
                ////CAMARA////
                /*Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                if(camera.resolveActivity(root.getPackageManager())!=null){
                    root.startActivityForResult(camera,code_camera);

                }*/



            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code_camera && resultCode == RESULT_OK ){
            Bundle photo= data.getExtras();
            Bitmap imageBitmap =(Bitmap) photo.get("data");
            ImageView img=root.findViewById(R.id.imageView);
            img.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       // Toast.makeText(this, "OnStart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "OnStop",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "OnPause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "OnResume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // Toast.makeText(this, "OnDestroy",Toast.LENGTH_LONG).show();
    }
}