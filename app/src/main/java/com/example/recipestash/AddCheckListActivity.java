package com.example.recipestash;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AddCheckListActivity extends AppCompatActivity {
    DataBaseHandler dataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_check_list);
        EditText item=findViewById(R.id.item);
        MaterialButton button=findViewById(R.id.save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemTxt=item.getText().toString();

                if(itemTxt.isEmpty()){
                    Toast.makeText(AddCheckListActivity.this, "Please ADD an item", Toast.LENGTH_SHORT).show();
                }else {
                    // save
                    if(dataBaseHandler==null){
                        dataBaseHandler=new DataBaseHandler(AddCheckListActivity.this);
                    }
                    dataBaseHandler.insertCheckList(itemTxt,0);
                    Toast.makeText(AddCheckListActivity.this, " item Added", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}