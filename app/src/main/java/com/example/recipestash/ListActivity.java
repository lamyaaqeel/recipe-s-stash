package com.example.recipestash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recipestash.R;

import java.util.List;


public class ListActivity extends AppCompatActivity {


    private DataBaseHandler handler;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
         listView=findViewById(R.id.list_view);
        Button btn=findViewById(R.id.add_item_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(ListActivity.this,AddCheckListActivity.class));
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        viewAll();
    }

    private void viewAll() {
        handler=new DataBaseHandler(this);
        List<CheckList> list=handler.viewAllCheckList();
        if(list.isEmpty()){
            Toast.makeText(this, "No Items Saved", Toast.LENGTH_SHORT).show();
        }else {
            Items adapter=new Items(list);
            listView.setAdapter(adapter);
        }
    }

    class Items extends BaseAdapter {
        List<CheckList> checkLists;

        public Items(List<CheckList> checkLists) {
            this.checkLists = checkLists;
        }

        @Override
        public int getCount() {
            return this.checkLists.size();
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
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            TextView check=view.findViewById(R.id.text_view_item);
            CheckBox checkBox=view.findViewById(R.id.checkbox_item_list);
            CheckList  checkList=this.checkLists.get(position);

            check.setText(checkList.getCheck());
            checkBox.setChecked(checkList.getIsChecked()==1);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkList.setIsChecked(isChecked?1:0);
                    handler.updateCheckList(checkList.getId(),checkList.getCheck(),isChecked?1:0);
                    notifyDataSetChanged();
                }
            });

            return view;
        }
    }


}
