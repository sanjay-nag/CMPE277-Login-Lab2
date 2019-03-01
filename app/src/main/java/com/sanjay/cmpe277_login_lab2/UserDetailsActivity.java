package com.sanjay.cmpe277_login_lab2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class UserDetailsActivity extends AppCompatActivity {

    private Button deleteUser;
    private TextView displayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        deleteUser = findViewById(R.id.deleteUserButton);
        displayUser = findViewById(R.id.userDetails);


        String user = "'" + getIntent().getStringExtra(MainActivity.USERNAME) + "' logged in!";
        displayUser.setText(user);

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserDetailsActivity.this);

                builder.setTitle("Delete Entry")
                        .setMessage("Do you really want to delete this user?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.deleteUser(getIntent().getStringExtra(MainActivity.USERNAME));

                                Toast.makeText(UserDetailsActivity.this, "User Deleted! ", Toast.LENGTH_SHORT).show();

                                Intent loginIntent = new Intent(UserDetailsActivity.this, MainActivity.class);
                                startActivity(loginIntent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();





//                builder.show();

            }
        });
    }

//    private void openDialog() {
//        UserDeleteDialog userDeleteDialog = new UserDeleteDialog();
//        userDeleteDialog.show(getSupportFragmentManager(), "delete dialog");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userdetails_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout) {

            Toast.makeText(UserDetailsActivity.this, "User Logout! ", Toast.LENGTH_SHORT).show();

            SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
