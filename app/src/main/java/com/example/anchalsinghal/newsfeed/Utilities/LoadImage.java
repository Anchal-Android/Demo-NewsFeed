package com.example.anchalsinghal.newsfeed.Utilities;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.anchalsinghal.newsfeed.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class LoadImage {

    public static void loadImageWithPicasso(Context context, String image_download_url, final ProgressBar prgImageLoader, final ImageView imgView) {
        //prgImageLoader.setVisibility(View.VISIBLE);
        String encode = image_download_url.replaceAll("\\s", "%20");
        Picasso.with(context).load(encode).into(imgView, new Callback() {
            @Override
            public void onSuccess() {
                //prgImageLoader.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {
                imgView.setImageResource(R.drawable.no_image);
               // prgImageLoader.setVisibility(View.INVISIBLE);
            }
        });
    }
}
