package com.example.recipestash;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    final static String DATA_BASE_NAME = "Recipes.db";
    final static String RECIPES_TABLE = "recipes";
    final static String CHECK_LIST_TABLE = "checkList";
    final static String ID = "ID";
    final static String NAME = "name";
    final static String DESCRIBE = "describe";

    final static String CHECK_LIST_TITLE = "checkListTitle";
    final static String IS_CHEKED = "isChecked";


    public DataBaseHandler(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String recipeTable = "CREATE TABLE " + RECIPES_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DESCRIBE + " TEXT " + ")";

        String checkListTable = "CREATE TABLE " + CHECK_LIST_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHECK_LIST_TITLE + " TEXT, " +
                IS_CHEKED + " INTEGER " + ")";
        db.execSQL(recipeTable);
        db.execSQL(checkListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecipe(String name,String describe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DESCRIBE,describe);
        contentValues.put(NAME,name);
        long result=db.insert(RECIPES_TABLE,null,contentValues);


    }

    public void insertCheckList(String check,int isChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CHECK_LIST_TITLE,check);
        contentValues.put(IS_CHEKED,isChecked);
        long result=db.insert(CHECK_LIST_TABLE,null,contentValues);

    }

    public void updateCheckList(int id,String check,int isChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CHECK_LIST_TITLE,check);
        contentValues.put(IS_CHEKED,isChecked);
        long result=db.update(CHECK_LIST_TABLE,contentValues,"ID=?",new String[]{String.valueOf(id)});

    }
    @SuppressLint("Range")
    public List<Recipe> viewAllRecipes() {
        List<Recipe> list=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+RECIPES_TABLE, null);
        try {
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex(ID));
                String name=cursor.getString(cursor.getColumnIndex(NAME));
                String describe=cursor.getString(cursor.getColumnIndex(DESCRIBE));
                Recipe recipe=new Recipe(id,name,describe);
                list.add(recipe);


            }
            cursor.close();


        }catch (Exception e){
          return  list;
        }
        return list;
    }


    @SuppressLint("Range")
    public List<CheckList> viewAllCheckList() {
        List<CheckList> list=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CHECK_LIST_TABLE, null);
        try {
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex(ID));
                int isChecked=cursor.getInt(cursor.getColumnIndex(IS_CHEKED));

                String check=cursor.getString(cursor.getColumnIndex(CHECK_LIST_TITLE));
                CheckList checkList=new CheckList(id,check,isChecked);
                list.add(checkList);


            }
            cursor.close();


        }catch (Exception e){
            return  list;
        }
        return list;
    }
}
