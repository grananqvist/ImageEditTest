package com.fgranqvist.imageedit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Filip on 2016-06-05.
 */
public class CollectionActivity extends AppCompatActivity {

    EditText collectionInput;
    TextView collectionOutput;
    MyDBHandler dbHandler;

    NotificationCompat.Builder notification;
    private static final int uniqueID = 123456;

    private static final String TAG = "fgranqvist";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        collectionInput = (EditText) findViewById(R.id.collectionInput);
        collectionOutput = (TextView) findViewById(R.id.collectionOutput);
        dbHandler = new MyDBHandler(this,null,null,1);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        //printDatabase();
    }

    //add product to db
    public void addButtonClicked(View v) {
        Products product = new Products(collectionInput.getText().toString());
        dbHandler.addProduct(product);
        sendNotification(product.get_productname());
        printDatabase();

    }

    //detele items
    public void deleteButtonClicked(View v) {
        String t = collectionInput.getText().toString();
        dbHandler.deleteProduct(t);
        printDatabase();
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        collectionOutput.setText(dbString);
        collectionInput.setText("");

    }

    //send a notification message to phone
    public void sendNotification(String item) {
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setTicker(item + " has been added to database!");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Database changed");
        notification.setContentText(item + " has been added to database!");

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //builds notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }
}
