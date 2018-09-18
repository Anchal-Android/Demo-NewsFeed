package com.example.anchalsinghal.newsfeed.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anchalsinghal.newsfeed.R;
import com.example.anchalsinghal.newsfeed.Utilities.LoadImage;

public class NewsFeedDetailPage extends Activity {

    String title = "", description = "", imageUrl = "";

    ImageView iv_newsFeedImage;
    TextView tv_title_detail,tv_desc_details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_details);

        iv_newsFeedImage = findViewById(R.id.iv_newsFeedImage);
        tv_title_detail = findViewById(R.id.tv_title_detail);
        tv_desc_details = findViewById(R.id.tv_desc_details);

        // getting data through bundle from News feed Activity to sho details---
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("TITLE");
            description = extras.getString("DESCRIPTION");
            imageUrl = extras.getString("IMAGEURL");
        }

        if(imageUrl!=null && !imageUrl.equalsIgnoreCase(""))
        {
            LoadImage.loadImageWithPicasso(this,imageUrl,null,iv_newsFeedImage);
        }
        tv_title_detail.setText(title);
        tv_desc_details.setText(description);

    }
}
