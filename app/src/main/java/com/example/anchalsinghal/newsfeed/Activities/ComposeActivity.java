package com.example.anchalsinghal.newsfeed.Activities;


import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.anchalsinghal.newsfeed.R;
import com.example.anchalsinghal.newsfeed.Room.db.UserDatabase;
import com.example.anchalsinghal.newsfeed.Room.entity.UserEntity;
import com.example.anchalsinghal.newsfeed.Utilities.AlertMessages;

import java.lang.ref.WeakReference;


public class ComposeActivity extends Activity {

    EditText et_title,et_description,et_imageurl;
    ImageView iv_attachment;
    Button btn_post;
    private UserDatabase userDb;
private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose_layout);

        userDb = UserDatabase.getDbInstance(this);
        progressDialog = new ProgressDialog(this);

        et_title = findViewById(R.id.et_title);
        et_description = findViewById(R.id.et_description);
        et_imageurl = findViewById(R.id.et_imageurl);
        iv_attachment = findViewById(R.id.iv_attachment);
        btn_post = findViewById(R.id.btn_post);


        // when click on post button to create a new post---
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(et_title.getText().toString().trim()))
                {
                    AlertMessages.alertMessage("Please enter title to write a post",ComposeActivity.this);
                    return;
                }
                if(TextUtils.isEmpty(et_description.getText().toString().trim()))
                {
                    AlertMessages.alertMessage("Please enter description to write a post",ComposeActivity.this);
                    return;
                }
                if(TextUtils.isEmpty(et_imageurl.getText().toString().trim()))
                {
                    AlertMessages.alertMessage("Please provide image url from google to write a post",ComposeActivity.this);
                    return;
                }

                RoomTask roomTask = new RoomTask(new WeakReference<Activity>(ComposeActivity.this),progressDialog,userDb);
                roomTask.execute(et_title.getText().toString(),et_description.getText().toString(),et_imageurl.getText().toString().trim());
            }
        });


    }



    private static class RoomTask extends AsyncTask<String, Void, Integer>
    {
        WeakReference<Activity> context;
        ProgressDialog progressDialog;
        UserDatabase userDb;

         RoomTask(WeakReference<Activity> context, ProgressDialog progressDialog, UserDatabase userDb) {

            this.context = context;
            this.progressDialog = progressDialog;
            this.userDb = userDb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

         progressDialog= ProgressDialog.show(context.get(),null, "Posting..",false);
        }

        @Override
        protected Integer doInBackground(String... params) {

             // Saving data in room database--

                        UserEntity entity = new UserEntity();
                        entity.setTitle(params[0]);
                        entity.setDescription(params[1]);
                        entity.setImageUrl(params[2]);
                        entity.setId(userDb.getUserDao().getUserDetails().size()+1);
                        userDb.getUserDao().insertUserData(entity);


            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            progressDialog.cancel();
            if(result!=null)
            {
                Intent intent = new Intent(context.get(), NewFeedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.get().startActivity(intent);
                context.get().finish();
            }
        }
    }

}
