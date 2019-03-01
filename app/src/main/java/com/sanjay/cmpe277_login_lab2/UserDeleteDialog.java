package com.sanjay.cmpe277_login_lab2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class UserDeleteDialog extends AppCompatDialogFragment {

    public static final String OK = "OK";
    public static final String CANCEL = "Cancel";



//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setTitle("Confirmation")
//                        .setMessage("Do you really want to delete this user?")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
//                                //  db.deleteUser();
//                            }
//                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//        return builder.create();
//    }
}
