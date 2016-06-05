package com.fgranqvist.imageedit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] filters = {"blur", "sharpen", "contrast"};
        ListAdapter myAdapter = new CustomAdapter(this,filters);
        ListView filtersList = (ListView) findViewById(R.id.filtersList);
        filtersList.setAdapter(myAdapter);

        filtersList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Toast.makeText(ListActivity.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
