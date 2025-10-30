package com.example.midterm_shifa_karnelia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   Button Gtablebtn,hisbtn;
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
        hisbtn = findViewById(R.id.history);
        listview = findViewById(R.id.listview);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, table_view);
        listview.setAdapter(adapter);


        Gtablebtn.setOnClickListener(v->{
            String userInput = u_number.getText().toString().trim();

            //toast error message
            if(userInput.isEmpty()){
                Toast.makeText(this,"plz enter a number!!!!!",Toast.LENGTH_SHORT).show();
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




  }
}