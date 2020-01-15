package com.example.examconsult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.ECField;
import java.util.ArrayList;

public class ForumArticle extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterComments adapter;
    private final ArrayList<Comments> comments = new ArrayList<>();
    Cursor cursor;
    SQLiteDatabase db;
    DBHelper dbHelper;
    EditText edit_comment;
    TextView textDescription;
    TextView textTitle;
    Button send;
    int user_id;
    int forum_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_forum_article);
            edit_comment = findViewById(R.id.edit_comment);
            recyclerView = findViewById(R.id.recycle_view);
            textDescription = findViewById(R.id.text_description);
            textTitle = findViewById(R.id.text_title);
            DBHelper dbHelper = new DBHelper(this);
            db = dbHelper.getWritableDatabase();

            Bundle arguments = getIntent().getExtras();
            user_id = arguments.getInt("id_user");
            forum_id = arguments.getInt("forum");
            //db descr
            String descr = dbHelper.getDescrById(forum_id);
            textDescription.setText(descr);
            String title = dbHelper.getTitleById(forum_id);
            textTitle.setText(title);
            db.close();
            getData();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_SHORT).show();
        }
    }
    public void getData(){
        try {
            dbHelper = new DBHelper(this);
            db = dbHelper.getWritableDatabase();
            comments.clear();
            cursor = db.rawQuery("select * from comments where forum_id = "+forum_id,null);
            if(cursor.moveToFirst()){
                do{
                    Comments comment = new Comments();
                    comment.setComment(cursor.getString(1));
                    comment.setDate(cursor.getString(2));
                    comment.setAuthor_id(dbHelper.getAuthor(cursor.getInt(3)));
                    comments.add(comment);
                }while (cursor.moveToNext());
            }else{
                Toast.makeText(this, "Таблица комментариев пустая", Toast.LENGTH_SHORT).show();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new AdapterComments(comments);
            recyclerView.setAdapter(adapter);
            cursor.close();
            db.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_SHORT).show();
        }

    }

    public void OnClick(View view) {
        try {
            if (edit_comment.length() >= 4 && edit_comment.length() <= 200){
                dbHelper.addComment(edit_comment.getText().toString(), user_id, forum_id);
                getData();
            }
            else
            {
                Toast.makeText(this, "Длинна комментария должна быть от 4 до 200", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_SHORT).show();
        }

    }
}
