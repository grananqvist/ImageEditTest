package com.fgranqvist.imageedit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Filip on 2016-06-05.
 */
public class imagecaptureActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imagecapturePic;
    Drawable myFace;
    Bitmap bitmapImage;
    Bitmap newPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagecapture);

        Button takePhotoButton = (Button) findViewById(R.id.takePhotoButton);
        imagecapturePic = (ImageView) findViewById(R.id.imagecapturePic);
/*
        Drawable[] layers = new Drawable[2];
        layers[0] = getResources().getDrawable(R.drawable.bert);
        layers[1] = getResources().getDrawable(R.drawable.dirty);
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        imagecapturePic.setImageDrawable(layerDrawable);*/

        myFace = getResources().getDrawable(R.drawable.bert);
        bitmapImage = ((BitmapDrawable)myFace).getBitmap();
        newPhoto = invertImage(bitmapImage);
        imagecapturePic.setImageBitmap(newPhoto);

        //MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto, "coolpic", "very cool pic indeed");

        //disable button if no cam
        if (!hasCamera()) {
            takePhotoButton.setEnabled(false);
        }
    }



    //invert bitmap image
    public static Bitmap invertImage(Bitmap original) {

        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());

        int A,R,G,B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++){
                pixelColor = original.getPixel(x,y);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                //make a new pixel and set in bitmap
                finalImage.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return finalImage;
    }


    //check if user has cam
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //launch cam
    public void takePhotoButtonClicked(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //take pic and pass results
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void savePhotoClicked(View v) {
        MediaStore.Images.Media.insertImage(getContentResolver(),newPhoto, "coolpic", "very cool pic indeed");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            imagecapturePic.setImageBitmap(invertImage(photo));

            Drawable[] layers = new Drawable[2];
            layers[0] = new BitmapDrawable(getResources(), invertImage(photo));
            layers[1] = getResources().getDrawable(R.drawable.dirty);
            LayerDrawable layerDrawable = new LayerDrawable(layers);

            imagecapturePic.setImageDrawable(layerDrawable);
            //funkar ej
            /*Bitmap b = Bitmap.createBitmap(layerDrawable.getMinimumWidth(),layerDrawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
            layerDrawable.draw(new Canvas(b));
            MediaStore.Images.Media.insertImage(getContentResolver(),b, "coolpic", "very cool pic indeed");*/
        }
    }
}


