package com.example.recycleradavance;

import static android.content.ContentValues.TAG;


import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleradavance.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageAdapter imageAdapter;


    private ActivityMainBinding binding;
    Handler mainHandler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //binding.imageView.setImageResource(R.drawable.ic_action_name);
        //binding.imageView.setImageURI(Uri.fromFile(new File("src/main/assets/app.png")));
       // getImageLocalDevice();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              new Fetchimage().start();

            }
        });

    }



    //*********************image local Database Load******************
        private void  getImageLocalDevice() {
            try {

                InputStream ims = getAssets().open("app.png");
                Drawable d = Drawable.createFromStream(ims, null);

                binding.imageView.setImageDrawable(d);
                ims.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
  /********************BackGround Thread Create Next Class **********/
  class Fetchimage extends Thread{
       private Bitmap bitmap;
       private ProgressDialog progressDialog;


      @Override
      public void run() {
         mainHandler.post(new Runnable() {
             @Override
             public void run() {
                 progressDialog = new ProgressDialog(MainActivity.this);
                 progressDialog.setMessage("Getting your pic....");
                 progressDialog.setCancelable(false);
                 progressDialog.show();
             }
         });

          try{
              InputStream inputStream = new URL(Url.b).openStream();
              bitmap = BitmapFactory.decodeStream(inputStream);

          }catch (IOException e){
              e.printStackTrace();

          }


         mainHandler.post(new Runnable() {
              @Override
              public void run() {
                  if (progressDialog.isShowing())
                      progressDialog.dismiss();
                  binding.imageView.setImageBitmap(bitmap);
              }
          });

      }

  }


}