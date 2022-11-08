package com.example.imageproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtContentUrl;
    private Button mBtnSubmit;
    private ImageView mImageContent;
    private TextView mTvPrevious, mTvNext;
    private List<Image> mListImage;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mIndex = 0;
        mListImage = new ArrayList<>();
        SQliteHelper db = new SQliteHelper(this);
        mListImage = db.getAllString();
         Glide.with(getApplicationContext()).load(mListImage.get(mIndex).getPathUrl()).into(mImageContent);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean img = true;
                //check url in here
                if (img) {
                    db.addItem(new Image(mEdtContentUrl.getText().toString()));
                    mIndex = mListImage.size() - 1;
                    mListImage = db.getAllString();
                    Glide.with(getApplicationContext()).load(mListImage.get(mIndex).getPathUrl()).into(mImageContent);
                    mEdtContentUrl.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong content!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIndex < mListImage.size() - 1) mIndex++;
                else mIndex = 0;
                Glide.with(getApplicationContext()).load(mListImage.get(mIndex).getPathUrl()).into(mImageContent);
            }
        });

        mTvPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIndex > 0) mIndex--;
                else mIndex = mListImage.size() - 1;
                Glide.with(getApplicationContext()).load(mListImage.get(mIndex).getPathUrl()).into(mImageContent);
            }
        });
    }

    private void initView() {
        mEdtContentUrl = findViewById(R.id.edt_url_image);
        mBtnSubmit = findViewById(R.id.btn_submit);
        mImageContent = findViewById(R.id.img);
        mTvPrevious = findViewById(R.id.tv_previous);
        mTvNext = findViewById(R.id.tv_next);

    }

}