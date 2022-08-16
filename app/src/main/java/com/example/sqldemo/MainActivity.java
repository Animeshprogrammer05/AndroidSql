package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button bt_add,bt_view;
    EditText et_name,et_age;
    Switch sw_active;
    ListView lv_cumstomerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_add=findViewById(R.id.bt_Add);
        bt_view=findViewById(R.id.bt_viewall);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        sw_active=findViewById(R.id.swActive);
        lv_cumstomerDetail=findViewById(R.id.lv_all);


        //button listener for add and view all
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;
               try{
                   customerModel=new CustomerModel(-1, et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_active.isChecked());

                   Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();

               }
               catch (Exception e){
                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                   customerModel=new CustomerModel( -1, "error", 0,  false);
               }

               DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
               boolean success=databaseHelper.addOne(customerModel);
                Toast.makeText(MainActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
                List<CustomerModel>everyone=databaseHelper.getEveryOne();
                ArrayAdapter customerArrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
                lv_cumstomerDetail.setAdapter(customerArrayAdapter);

            }
        });




    }
}