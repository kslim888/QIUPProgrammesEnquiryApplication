package com.qiup.programmeenquiry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    Long queueID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enquiryProgrammes(View view)
    {
        Intent filterProgrammes = new Intent(MainActivity.this, FilterProgrammes.class);
        startActivity(filterProgrammes);
    }

    public void captureData(View view)
    {
        Intent captureData = new Intent(MainActivity.this, CaptureData.class);
        startActivity(captureData);
    }
}
