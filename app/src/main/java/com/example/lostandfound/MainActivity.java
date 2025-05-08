package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //set view defined in activity_main.xml

        //bind variable to button
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnShow = findViewById(R.id.btnShow);

        //when create button is clicked, start CreateAdvertActivity
        btnCreate.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CreateAdvertActivity.class))
        );
        //when show button is clicked, start ItemListActivity
        btnShow.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ItemListActivity.class))
        );
    }
}