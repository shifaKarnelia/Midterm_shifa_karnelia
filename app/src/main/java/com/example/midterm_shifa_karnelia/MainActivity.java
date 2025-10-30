package com.example.midterm_shifa_karnelia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   Button Gtablebtn;
   EditText u_number;
   ListView listview;


    ArrayList<String> table_view = new ArrayList<>();
    ArrayAdapter<String> adapter;
    public static ArrayList<Integer> historyList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Gtablebtn = findViewById(R.id.table);
        u_number = findViewById(R.id.number);
        listview = findViewById(R.id.listview);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, table_view);
        listview.setAdapter(adapter);



        //calculate table when user click on button
        Gtablebtn.setOnClickListener(v->{
            String userInput = u_number.getText().toString().trim();
            //toast error message if user input is empty
            if(userInput.isEmpty()){
                Toast.makeText(this,"plz enter a number!!!!!",Toast.LENGTH_SHORT).show();
                return;
            }
            int number= Integer.parseInt(userInput);
            table_view.clear();
            for(int i =1; i <=10; i++){
                table_view.add(number + " x " + i +" = " +(number*i));
            }
            adapter.notifyDataSetChanged();
            if(!historyList.contains(number)){
                historyList.add(number);
            }
       });

        //when user click on item ask user if they want to delete item
        //yes - delete item and toast message
        listview.setOnItemClickListener((parent, view, position, id) -> {
            String selected = table_view.get(position);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Row")
                    .setMessage("Do you want to delete this: " + selected + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        table_view.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Deleted: " + selected, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

  }

  //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // when user click on item start second activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.vHistory) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.clear) {
            // Show confirmation dialog
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Clear All?")
                    .setMessage("Do you want to delete all rows?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        table_view.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "All rows cleared!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}