package com.antoniocappiello.porterduffdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PorterDuffWithImageActivity extends AppCompatActivity {

    @Bind(R.id.spinner_porter_duff)
    Spinner spinnerPortedDuff;

    @Bind(R.id.image_source)
    ImageView mImageViewSource;

    @Bind(R.id.image_destination)
    ImageView mImageViewDestination;

    @Bind(R.id.image_result)
    ImageView mImageResult;

    private Bitmap bitmapSource;
    private Bitmap bitmapDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_with_image);
        ButterKnife.bind(this);

        initSpinnerPorterDuff();
        PorterDuff.Mode porterDuffMode = PorterDuff.Mode.valueOf(spinnerPortedDuff.getSelectedItem().toString());

        bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.android_eats_apple);
        bitmapDestination = BitmapFactory.decodeResource(getResources(), R.drawable.rain_background);

        mImageViewSource.setImageBitmap(bitmapSource);
        mImageViewDestination.setImageBitmap(bitmapDestination);

        updateImageResult(bitmapSource, bitmapDestination, porterDuffMode);

    }



    private void initSpinnerPorterDuff() {
        List<String> porterDuffModes = new ArrayList<>();
        for (PorterDuff.Mode mode : PorterDuff.Mode.values()) {
            porterDuffModes.add(mode.name());
        }

        Collections.reverse(porterDuffModes);
        ArrayAdapter<String> porterDuffModesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, porterDuffModes);
        spinnerPortedDuff.setAdapter(porterDuffModesAdapter);
        spinnerPortedDuff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PorterDuff.Mode mode = PorterDuff.Mode.valueOf((String) parent.getSelectedItem().toString());
                updateImageResult(bitmapSource, bitmapDestination, mode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateImageResult(Bitmap bitmapSource, Bitmap bitmapDestination, PorterDuff.Mode mode) {
        Bitmap newBitmap = processImages(bitmapSource, bitmapDestination, mode);
        mImageResult.setImageBitmap(newBitmap);
    }

    private Bitmap processImages(Bitmap bitmapSource, Bitmap bitmapDestination, PorterDuff.Mode mode) {

        Bitmap newBitmap;

        int w = bitmapDestination.getWidth();
        int h = bitmapDestination.getHeight();

        Bitmap.Config config = bitmapDestination.getConfig();
        if(config == null){
            config = Bitmap.Config.ARGB_8888;
        }

        newBitmap = Bitmap.createBitmap(w, h, config);
        Canvas newCanvas = new Canvas(newBitmap);
        newCanvas.drawBitmap(bitmapDestination, 0, 0, null);

        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(mode));

        newCanvas.drawBitmap(bitmapSource, 0, 0, paint);

        return newBitmap;
    }
}
