package com.example.recipestash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recipestash.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mealActivity extends AppCompatActivity {
    private Button buttonShowLink;
    private List<String> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_layout);

        buttonShowLink = findViewById(R.id.buttonShowLink);

        // Add your desired links to the list
        links = new ArrayList<>();
        links.add("https://therecipecritic.com/pink-sauce-pasta/");
        links.add("https://therecipecritic.com/sizzling-chicken-fajitas/");
        links.add("https://therecipecritic.com/easy-bbq-chicken-pizza/");
        links.add("https://therecipecritic.com/kung-pao-shrimp/");

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