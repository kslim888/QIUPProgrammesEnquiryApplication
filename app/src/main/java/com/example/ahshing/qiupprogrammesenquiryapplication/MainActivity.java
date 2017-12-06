package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import entryRules.FIB;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enquiryProgrammes(View view)
    {
        Intent filterProgrammes = new Intent(MainActivity.this, filterProgrammes.class);
        startActivity(filterProgrammes);
    }

    public void captureData(View view)
    {
        // UEC numberMinimumGrade = 3;
        // UEC minimum grade must be B and above;
        // minimumGradeList = Arrays.asList("A+", "A", "A-", "B+", "B");
        // count++, based on results type
        // do on filter

        // create facts
        Facts facts = new Facts();
        facts.put("Number of Credit", 5);
        facts.put("Results Type","SPM");

        // create and define rules
        Rules rules = new Rules(new FIB());

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

        //Intent captureData = new Intent(MainActivity.this, captureData.class);
        //startActivity(captureData);
    }
}
