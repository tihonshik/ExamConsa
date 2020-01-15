package com.example.examconsult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Forum> forums = new ArrayList<>();
    int user_id;
    RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Bundle arguments = getIntent().getExtras();
            user_id = arguments.getInt("id_user");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setInitialData();
            recyclerView = findViewById(R.id.list);
            adapter = new RecycleAdapter(this, forums,user_id);
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_SHORT).show();
        }
    }

    private void setInitialData() {
        try {
            DBHelper helper = new DBHelper(this);
            forums = helper.getForums();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_SHORT).show();
        }
    }


    public void click(View view) {
        try {
            Intent intent = new Intent(this, add_forum.class);
            intent.putExtra("id_user", user_id);
            this.startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
