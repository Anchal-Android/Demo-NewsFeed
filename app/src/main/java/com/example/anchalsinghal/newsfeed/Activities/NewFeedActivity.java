package com.example.anchalsinghal.newsfeed.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.anchalsinghal.newsfeed.Adapter.MyAdapter;
import com.example.anchalsinghal.newsfeed.R;
import com.example.anchalsinghal.newsfeed.Room.db.UserDatabase;
import com.example.anchalsinghal.newsfeed.Room.entity.UserEntity;
import com.example.anchalsinghal.newsfeed.listener.NewsFeedListener;

import java.util.ArrayList;
import java.util.List;

public class NewFeedActivity extends AppCompatActivity implements NewsFeedListener {

    RecyclerView recMyApplication;
    public RecyclerView.Adapter madapter;
    public RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fab_compose;

    EditText edtSearch;

    UserDatabase db;

    List<UserEntity> newsFeedList = new ArrayList<>();
    List<UserEntity> searchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_feed_layout);

        db = UserDatabase.getDbInstance(NewFeedActivity.this);
        recMyApplication = findViewById(R.id.recMyApplication);
        fab_compose = findViewById(R.id.fab_compose);
        edtSearch = findViewById(R.id.edtSearch);

        layoutManager = new LinearLayoutManager(this);
        recMyApplication.setLayoutManager(layoutManager);


         // when clicking on the search button to filter out the list based on title---

        TextWatcher textChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                if (charSequence.toString().length() < 1) {
                    searchList.clear();
                    searchList.addAll(newsFeedList);

                } else {
                    searchList.clear();
                    for (int pos = 0; pos < newsFeedList.size(); pos++) {
                        if (newsFeedList.get(pos).getTitle().toLowerCase().contains(charSequence
                                .toString().toLowerCase())) {
                            searchList.add(newsFeedList.get(pos));

                        }
                    }


                }
                madapter = new MyAdapter(searchList, NewFeedActivity.this, NewFeedActivity.this);
                recMyApplication.setAdapter(madapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        edtSearch.addTextChangedListener(textChangeListener);


        // to fetch the data from the db--
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.getData().getBoolean("ISSUCCESS")) {
                    recMyApplication.setAdapter(madapter);

                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = new Message();
                Bundle bd = new Bundle();
                if (db.getUserDao().getUserDetails() != null) {
                    newsFeedList = db.getUserDao().getUserDetails();
                    madapter = new MyAdapter(newsFeedList, NewFeedActivity.this, NewFeedActivity.this);
                    bd.putBoolean("ISSUCCESS", true);
                    msg.setData(bd);
                } else {
                    bd.putBoolean("ISSUCCESS", false);
                    msg.setData(bd);
                }
                handler.sendMessage(msg);
            }
        }).start();


// when clicking on the compose button---
        fab_compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewFeedActivity.this, ComposeActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onNewsFeedClick(String title, String description, String imageUrl) {

        Intent i = new Intent(NewFeedActivity.this, NewsFeedDetailPage.class);
        i.putExtra("TITLE", title);
        i.putExtra("DESCRIPTION", description);
        i.putExtra("IMAGEURL", imageUrl);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
