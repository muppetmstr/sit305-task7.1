package com.example.lostandfound;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {
    //declare variables
    EditText name, phone, desc, date, location;
    RadioGroup typeGroup;
    Button save;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert); //set layout based off activity_create_advert.xml

        //initialise components
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        desc = findViewById(R.id.desc);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        typeGroup = findViewById(R.id.typeGroup);
        save = findViewById(R.id.btnSave);
        db = new DBHelper(this);

        //on click for the save button, get all the input fields
        save.setOnClickListener(v -> {
            String type = ((RadioButton)findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();
            String nameStr = name.getText().toString().trim();
            String phoneStr = phone.getText().toString().trim();
            String descStr = desc.getText().toString().trim();
            String dateStr = date.getText().toString().trim();
            String locationStr = location.getText().toString().trim();

            //validate phone number
            if (!phoneStr.matches("^04\\d{2}\\s?\\d{3}\\s?\\d{3}$")) {
                Toast.makeText(this, "Invalid phone number. Use format: 04xx xxx xxx", Toast.LENGTH_SHORT).show();
                return;
            }

            //validate date in dd/MM/yyyy
            if (!dateStr.matches("^([0-2][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
                Toast.makeText(this, "Invalid date. Use format: dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                return;
            }

            //insert into database
            if (db.insertItem(type, nameStr, phoneStr, descStr, dateStr, locationStr)) { //if insert is successful
                Toast.makeText(this, "Advert saved!", Toast.LENGTH_SHORT).show();
                finish();
            } else { //if insert is not successful
                Toast.makeText(this, "Error saving advert", Toast.LENGTH_SHORT).show();
            }
        });
    }
}