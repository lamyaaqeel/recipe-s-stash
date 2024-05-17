package com.example.recipestash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipestash.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class snackActivity extends AppCompatActivity {
    private List<String> links;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_layout);

        Button buttonShowLink = findViewById(R.id.snakbuttonShowLink);

        links = new ArrayList<>();
        links.add("https://www.ambitiouskitchen.com/best-guacamole-recipe/");
        links.add("https://therecipecritic.com/almond-granola-bars/");
        links.add("https://therecipecritic.com/caesar-salad/");
        links.add("https://therecipecritic.com/strawberry-mango-smoothie/");


        buttonShowLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showRandomLink();
            }
        });
    }

    private void showRandomLink() {
        if (!links.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(links.size());
            String randomLink = links.get(randomIndex);

            // Open the link using an Intent
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(randomLink));
            startActivity(intent);
        }
    }
}