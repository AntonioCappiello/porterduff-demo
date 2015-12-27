package com.antoniocappiello.porterduffdemo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PorterDuffWithColorFilterActivity extends AppCompatActivity {

    private static final int SEEK_BAR_MAX = 100;

    @Bind(R.id.spinner_porter_duff)
    Spinner spinnerPortedDuff;

    @Bind(R.id.image)
    ImageView imageView;

    @Bind(R.id.color_blue)
    View colorViewBlue;

    @Bind(R.id.color_brown)
    View colorViewBrown;

    @Bind(R.id.color_cyan)
    View colorViewCyan;

    @Bind(R.id.color_orange)
    View colorViewOrange;

    @Bind(R.id.color_purple)
    View colorViewPurple;

    @Bind(R.id.color_yellow)
    View colorViewYellow;

    @Bind(R.id.color_red)
    View colorViewRed;

    @Bind(R.id.color_green)
    View colorViewGreen;

    @Bind(R.id.seek_bar_alpha)
    SeekBar seekBarAlpha;

    private PorterDuff.Mode mode = PorterDuff.Mode.OVERLAY;
    private float alpha = 1;
    private int colorFilter = android.R.color.transparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_with_color_filter);
        ButterKnife.bind(this);
        initSeekBarAlpha();
        initSpinnerPorterDuff();
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
                mode = PorterDuff.Mode.valueOf((String) parent.getSelectedItem().toString());
                updateImageFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSeekBarAlpha() {
        seekBarAlpha.setMax(SEEK_BAR_MAX);
        seekBarAlpha.setProgress(SEEK_BAR_MAX);
        seekBarAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = (float) progress / SEEK_BAR_MAX;
                imageView.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                colorFilter = android.R.color.transparent;
                mode = PorterDuff.Mode.OVERLAY;
                updateImageFilter();
                imageView.setAlpha((float) 1);
                seekBarAlpha.setProgress(SEEK_BAR_MAX);
                spinnerPortedDuff.setSelection(0);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                super.onOptionsItemSelected(item);
                break;

        }
        return true;

    }

    @OnClick(R.id.color_red)
    void setRedFilter() {
        colorFilter = R.color.red;
        updateImageFilter();
    }

    @OnClick(R.id.color_yellow)
    void setYellowFilter() {
        colorFilter = R.color.yellow;
        updateImageFilter();    }

    @OnClick(R.id.color_green)
    void setGreenFilter() {
        colorFilter = R.color.green;
        updateImageFilter();    }

    @OnClick(R.id.color_cyan)
    void setCyanFilter() {
        colorFilter = R.color.cyan;
        updateImageFilter();    }

    @OnClick(R.id.color_blue)
    void setBlueFilter() {
        colorFilter = R.color.blue;
        updateImageFilter();
    }

    @OnClick(R.id.color_purple)
    void setPurpleFilter() {
        colorFilter = R.color.purple;
        updateImageFilter();
    }

    @OnClick(R.id.color_brown)
    void setBrownFilter() {
        colorFilter = R.color.brown;
        updateImageFilter();
    }

    @OnClick(R.id.color_orange)
    void setOrangeFilter() {
        colorFilter = R.color.orange;
        updateImageFilter();
    }

    private void updateImageFilter() {
        imageView.setColorFilter(ContextCompat.getColor(this, colorFilter), mode);
    }
}

