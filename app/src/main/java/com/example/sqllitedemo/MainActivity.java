package com.example.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name_text , email_text , age_text;
    Button add,del,mod,view,view_all,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_text = findViewById(R.id.name_text);
        email_text = findViewById(R.id.email_text);
        age_text = findViewById(R.id.age_text);

        add = findViewById(R.id.add_btn);
        del = findViewById(R.id.delete_btn);
        mod = findViewById(R.id.modify_btn);
        view = findViewById(R.id.view_btn);
        view_all = findViewById(R.id.view_all_btn);
        show = findViewById(R.id.show_btn);

        final SQLiteDatabase db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR,email VARCHAR,age VARCHAR);");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_text.getText().toString().trim();
                String email = email_text.getText().toString().trim();
                String age =  age_text.getText().toString().trim();

                if (email.isEmpty() || name.isEmpty() || age.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Values", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.execSQL("INSERT INTO student VALUES('" + name_text.getText() + "','" + email_text.getText() +"','" + age_text.getText()+"')");
                    Toast.makeText(MainActivity.this,"Record Added",Toast.LENGTH_LONG).show();
                }
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c_1 = db.rawQuery("SELECT * FROM student WHERE name = '"+name_text.getText()+"'",null);

                if (c_1.moveToFirst())
                {
                    email_text.setText(c_1.getString(1));
                    age_text.setText(c_1.getString(2));

                    // --
//                    Bundle bundle = new Bundle();
//                    bundle.putString("name", c_1.getString(1));
//                    bundle.putString("marks", c_1.getString(2));
//
//                    startActivity(new Intent(MainActivity.this, sss).putExtras(bundle));

                    // new actiivity
//                    Intent intent = getIntent();
//                    Bundle extras = intent.getExtras();
                    // name.setText(extras.getString("name");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid name",Toast.LENGTH_LONG).show();
                }
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c_1 = db.rawQuery("SELECT * FROM student WHERE name = '" + name_text.getText() + "'",null);

                if (c_1.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE name='" + name_text.getText() + "'");
                    Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid roll",Toast.LENGTH_LONG).show();
                }
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

            }
        });


    }
}
