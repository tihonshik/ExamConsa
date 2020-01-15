package com.example.examconsult;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class add_forum extends AppCompatActivity {

    EditText title, desc;
    String date;
    int user_id;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_forum);
            title = findViewById(R.id.title_edit);
            desc = findViewById(R.id.desc_edit);
            Bundle arguments = getIntent().getExtras();
            user_id = arguments.getInt("id_user");
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "ЧТо-то пошло не так", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void click(View view) {
        try {
            DBHelper help = new DBHelper(this);
            if (title.length() >= 4 && title.length() <= 20) {
                if (desc.length() >= 4 && desc.length() <= 140) {
                    String desc = this.desc.getText().toString();
                    String title = this.title.getText().toString();
                    help.newForum(desc, 100, date, title, user_id);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("id_user", user_id);
                    this.startActivity(intent);
                } else {
                    Toast.makeText(this, "Длинна должна быть от 4 до 140", Toast.LENGTH_SHORT).show();
                }
            } else Toast.makeText(this, "Длинна должна быть от 4 до 20", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Что-то пошло не так", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
