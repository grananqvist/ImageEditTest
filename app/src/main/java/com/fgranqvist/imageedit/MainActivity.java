package com.fgranqvist.imageedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "fgranqvist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button listviewbtn = (Button) findViewById(R.id.listviewbtn);

      listviewbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,ListActivity.class ));
                    }
                }
        );
    }

    public void collectionDBbuttonClicked(View v) {
        startActivity(new Intent(MainActivity.this,CollectionActivity.class ));
    }

    public void cameraButtonClicked(View v) {
        startActivity(new Intent(MainActivity.this,imagecaptureActivity.class ));
    }

}
