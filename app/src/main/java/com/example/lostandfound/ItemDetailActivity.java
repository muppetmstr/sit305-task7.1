package com.example.lostandfound;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {
    //declare variables
    TextView detailText;
    Button removeBtn, backBtn;
    DBHelper db;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail); //set layout

        //initialise components
        detailText = findViewById(R.id.detailText);
        removeBtn = findViewById(R.id.btnRemove);
        backBtn = findViewById(R.id.btnBackToList);
        db = new DBHelper(this);

        //find the item id
        itemId = getIntent().getIntExtra("id", -1);
        Cursor c = db.getAllItems(); //query database to fetch all items
        //loop through cursor and find the item with the matching id
        while (c.moveToNext()) {
            if (c.getInt(0) == itemId) {
                detailText.setText(c.getString(1) + " " + c.getString(4) + "\n" + c.getString(5) + "\n" + c.getString(6)); //get item name, description, location and date
                break;
            }
        }

        //listener for when remove button is clicked
        removeBtn.setOnClickListener(v -> {
            db.deleteItem(itemId); //delete item using id to find correct item
            Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
            finish(); // Go back to item list
        });

        backBtn.setOnClickListener(v -> finish()); //return to item list without deleting
    }
}