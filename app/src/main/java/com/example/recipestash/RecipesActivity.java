package com.example.recipestash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private DataBaseHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ListView listView=findViewById(R.id.list);
        handler=new DataBaseHandler(this);
        List<Recipe> recipes=handler.viewAllRecipes();
        if(recipes.isEmpty()){
            Toast.makeText(this, "No Recipes Saved", Toast.LENGTH_SHORT).show();
        }else {
            RecipesAdapter recipesAdapter=new RecipesAdapter(recipes);
            listView.setAdapter(recipesAdapter);
        }

    }

 class RecipesAdapter extends BaseAdapter{
        List<Recipe> recipes;

     public RecipesAdapter(List<Recipe> recipes) {
         this.recipes = recipes;
     }

     @Override
     public int getCount() {
         return this.recipes.size();
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item,parent,false);
         TextView name=view.findViewById(R.id.name);
         TextView describe=view.findViewById(R.id.describe);
         Recipe recipe=this.recipes.get(position);
         name.setText(recipe.getName());
         describe.setText(recipe.getDescribe());
         return view;
     }
 }
}
