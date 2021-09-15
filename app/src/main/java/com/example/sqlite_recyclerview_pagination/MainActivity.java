package com.example.sqlite_recyclerview_pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.os.Bundle;


import android.content.Intent;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite_recyclerview_pagination.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button submit, show;
    DatabaseHelper databaseHelper;

    EditText etname, etauthor;
    String name, author;
    ConstraintLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = (EditText) findViewById(R.id.etbook_name);
        etauthor = (EditText) findViewById(R.id.etbook_author);
        main = (ConstraintLayout) findViewById(R.id.main);

        submit = (Button) findViewById(R.id.submit);


        submit = (Button) findViewById(R.id.submit);
        show = (Button) findViewById(R.id.show);

        databaseHelper = new DatabaseHelper(this);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etname.getText().toString();
                author = etauthor.getText().toString();

                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (name.isEmpty() && author.isEmpty()) {

                    Toast.makeText(MainActivity.this, "please fill details", Toast.LENGTH_SHORT).show();
                } else {

                    databaseHelper.insertdata(name, author);
                    etname.setText("");
                    etauthor.setText("");

                    Intent intent = new Intent(MainActivity.this, ViewallActivity.class);
                    startActivity(intent);

                }
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewallActivity.class);
                startActivity(intent);
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
