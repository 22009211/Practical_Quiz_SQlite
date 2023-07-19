package com.example.practicalquizsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    Button btnInsert, btnRetrieve;
    ListView lv;
    ArrayList<School> al;
    ArrayAdapter<School> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        lv = findViewById(R.id.lv);

       aa = new ArrayAdapter<School>(this, android.R.layout.simple_list_item_1, al);
       lv.setAdapter(aa);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
                String student = et1.getText().toString();
                String school = et2.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSchool(student, school);

                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
                DBHelper dbh = new DBHelper(MainActivity.this);
                //al.addAll(dbh.getAllNotes());
                    al= dbh.getSchools();

                aa.notifyDataSetChanged();
            }
        });
    }
}