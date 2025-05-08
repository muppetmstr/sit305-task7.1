package com.example.lostandfound;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
    //declare variables
    ListView listView;
    ArrayList<String> items;
    ArrayList<Integer> ids;
    ArrayAdapter<String> adapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list); //set layout based on activity_item_list.xml

        //initialise components
        listView = findViewById(R.id.listView);
        Button buttonBack = findViewById(R.id.buttonBackToMenu);
        db = new DBHelper(this);
        items = new ArrayList<>();
        ids = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        //on click listener to view item details
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ItemListActivity.this, ItemDetailActivity.class); //create intent to navigate to ItemDetailActivity
            i.putExtra("id", ids.get(position)); //pass item ID to detail activity
            startActivity(i); //start ItemDetailActivity
        });

        buttonBack.setOnClickListener(v -> finish()); //set on click listener for back button
    }

    //onResume method when returning
    @Override
    protected void onResume() {
        super.onResume();
        //load updated list
        loadItemList();
    }

    //fetches all items from database and populates ListView
    private void loadItemList() {
        items.clear();
        ids.clear();
        Cursor c = db.getAllItems(); //query database to get all items
        //looop through entire database
        while (c.moveToNext()) {
            ids.add(c.getInt(0));
            items.add(c.getString(1) + " - " + c.getString(4));
        }
        adapter.notifyDataSetChanged(); //notify adapter that data has changed
    }
}