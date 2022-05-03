package com.example.recycleradavance;

import static android.content.ContentValues.TAG;


import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleradavance.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageView.setImageResource(R.drawable.ic_action_name);

        binding.imageView.setImageURI(Uri.parse("file:///android_assets/app"));
        try
        {

            InputStream ims = getAssets().open("app.png");
            Drawable d = Drawable.createFromStream(ims, null);

            binding.imageView.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            return;
        }
       // binding.imageView.setImageDrawable("d");


    }
    String url = "https://via.placeholder.com/300.png";
    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            System.out.println(bm+"pramod");
          //  binding.imageView.setImageResource(R.drawable.ic_action_name);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }
}