package com.example.anchalsinghal.newsfeed.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertMessages {


    public AlertMessages() {


    }

    public static void alertMessage(String message, Context context) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
