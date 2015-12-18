package com.kimson.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimson.library.StickyScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StickyScrollView.OnPageChangeListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ImageView ImageItem1;
    private ImageView ImageItem2;
    private ImageView ImageItem3;
    private ImageView ImageItem4;
    private ImageView ImageItem5;
    private ImageView ImageItem6;
    private ImageView ImageItem7;
    private ImageView ImageItem8;
    private ImageView ImageItem9;
    private ImageView ImageItem10;

    private StickyScrollView stickyScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stickyScrollView = (StickyScrollView) findViewById(R.id.sticky_scroll);
        stickyScrollView.setOnPageChangeListener(this);

        ImageItem1 = (ImageView) findViewById(R.id.item_1);
        ImageItem2 = (ImageView) findViewById(R.id.item_2);
        ImageItem3 = (ImageView) findViewById(R.id.item_3);
        ImageItem4 = (ImageView) findViewById(R.id.item_4);
        ImageItem5 = (ImageView) findViewById(R.id.item_5);
        ImageItem6 = (ImageView) findViewById(R.id.item_6);
        ImageItem7 = (ImageView) findViewById(R.id.item_7);
        ImageItem8 = (ImageView) findViewById(R.id.item_8);
        ImageItem9 = (ImageView) findViewById(R.id.item_9);
        ImageItem10 = (ImageView) findViewById(R.id.item_10);

        ImageItem1.setOnClickListener(this);
        ImageItem2.setOnClickListener(this);
        ImageItem3.setOnClickListener(this);
        ImageItem4.setOnClickListener(this);
        ImageItem5.setOnClickListener(this);
        ImageItem6.setOnClickListener(this);
        ImageItem7.setOnClickListener(this);
        ImageItem8.setOnClickListener(this);
        ImageItem9.setOnClickListener(this);
        ImageItem10.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, ">>>onClick");
        switch (v.getId()) {
            case R.id.item_1:
                Log.e(TAG, ">>>item_1");
                Toast.makeText(this, "IMAGE_1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_2:
                Log.e(TAG, ">>>item_2");
                Toast.makeText(this, "IMAGE_2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_3:
                Log.e(TAG, ">>>item_3");
                Toast.makeText(this, "IMAGE_3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_4:
                Log.e(TAG, ">>>item_4");
                Toast.makeText(this, "IMAGE_4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_5:
                Log.e(TAG, ">>>item_5");
                Toast.makeText(this, "IMAGE_5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_6:
                Log.e(TAG, ">>>item_6");
                Toast.makeText(this, "IMAGE_6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_7:
                Log.e(TAG, ">>>item_7");
                Toast.makeText(this, "IMAGE_7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_8:
                Log.e(TAG, ">>>item_8");
                Toast.makeText(this, "IMAGE_8", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_9:
                Log.e(TAG, ">>>item_9");
                Toast.makeText(this, "IMAGE_9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_10:
                Log.e(TAG, ">>>item_10");
                Toast.makeText(this, "IMAGE_10", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void OnPageChange(int currentPage) {
        Log.e(TAG, ">>>OnPageChange");
        switch (currentPage) {
            case StickyScrollView.PAGE_TOP:
                Toast.makeText(this, "CHANGE_TO_TOP", Toast.LENGTH_SHORT).show();
                break;
            case StickyScrollView.PAGE_BOTTOM:
                Toast.makeText(this, "CHANGE_TO_BOTTOM", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
