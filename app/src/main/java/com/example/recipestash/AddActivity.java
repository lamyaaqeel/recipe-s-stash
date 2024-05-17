package com.example.recipestash;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class AddActivity extends AppCompatActivity {

    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        EditText name=findViewById(R.id.name);
        EditText descrip=findViewById(R.id.describe);
        MaterialButton button=findViewById(R.id.save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();
                String deTxt=descrip.getText().toString();
                if(nameTxt.isEmpty() || deTxt.isEmpty()){
                    Toast.makeText(AddActivity.this, "Please add your recipe", Toast.LENGTH_SHORT).show();
                }else {

                    if(dataBaseHandler==null){
                        dataBaseHandler=new DataBaseHandler(AddActivity.this);
                    }
                    dataBaseHandler.insertRecipe(nameTxt,deTxt);
                    Toast.makeText(AddActivity.this, " recipe Added", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}