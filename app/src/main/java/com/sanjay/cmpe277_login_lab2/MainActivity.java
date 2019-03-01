package com.sanjay.cmpe277_login_lab2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String USERNAME = "USERNAME";


    DatabaseHelper db;
    private EditText userName;
    private EditText password;
    private Button login;
    private Button signUp;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);

        userName.setText(getIntent().getStringExtra(USERNAME));

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString().trim();
                String pswd = password.getText().toString().trim();

                if (db.checkUserExists(user, pswd)) {
                    Toast.makeText(MainActivity.this, "Successfully logged in! ", Toast.LENGTH_SHORT).show();

                    //Session management
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(USERNAME, user);
                    editor.commit();

                    Intent moveToUserDetails = new Intent(MainActivity.this, UserDetailsActivity.class);
                    moveToUserDetails.putExtra(USERNAME, user);
                    startActivity(moveToUserDetails);

                } else {
                    Toast.makeText(MainActivity.this, "Please register to login!", Toast.LENGTH_SHORT).show();
                    Intent moveToRegister = new Intent(MainActivity.this, RegistrationActivity.class);
                    moveToRegister.putExtra(USERNAME, user);
                    startActivity(moveToRegister);
                }
            }
        });

    }
}
