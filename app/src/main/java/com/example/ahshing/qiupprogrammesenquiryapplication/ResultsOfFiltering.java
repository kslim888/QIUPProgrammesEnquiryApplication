package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entryRules.FIBFIA;
import entryRules.FIS;

public class ResultsOfFiltering extends AppCompatActivity
{
    ExpandableListView eligibleListView, ineligibleListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_of_filtering);

        eligibleListView = findViewById(R.id.eligibleProgrammes);
        ineligibleListView = findViewById(R.id.ineligibleProgrammes);
        initData();

        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        eligibleListView.setAdapter(expandableListAdapter);
        eligibleListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });
        ineligibleListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });
        ineligibleListView.setAdapter(expandableListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }


    private void setListViewHeight(ExpandableListView listView, int group)
    {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++)
        {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();
            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group)))
            {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++)
                {
                    View listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void initData()
    {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        //get student's academic data
        //Bundle extras = getIntent().getExtras();
        //String[] subjectsStringArray = extras.getStringArray("STUDENT_SUBJECTS_LIST");
        //String[] gradesStringArray = extras.getStringArray("STUDENT_GRADES_LIST");
        // here for loop through and add
        //listDataHeader.addAll(Arrays.asList(subjectsStringArray));

        //getIntent().getStringExtra("Results_Type")
        //getIntent().getSerializableExtra("Results_Type");


        if(FIS.isJoinProgramme())
        {
            listDataHeader.add("Foundation in Sciences");
        }
        if(FIBFIA.isJoinProgramme())
        {
            listDataHeader.add("Foundation in Business");
        }

        listDataHeader.add("Foundation in Arts");
        listDataHeader.add("Bachelor of Computer Sciences");
        listDataHeader.add("Bachelor of Information Technology");
        listDataHeader.add("Bachelor of Business Administrator");
        listDataHeader.add("MBBS");
        listDataHeader.add("Bachelor of Information System");

        // here is requirement of the programme, or child of the programme group
        List<String> description1 = new ArrayList<>();
        description1.add("ABC");

        List<String> description2 = new ArrayList<>();
        description2.add("ABC");
        description2.add("DEF");
        description2.add("GHI");

        List<String> description3 = new ArrayList<>();
        description3.add("123");
        description3.add("456");
        description3.add("789");
        description3.add("10");

        listHashMap.put(listDataHeader.get(0),description1);
        listHashMap.put(listDataHeader.get(1),description2);
        listHashMap.put(listDataHeader.get(2),description3);
        listHashMap.put(listDataHeader.get(3),description1);
        listHashMap.put(listDataHeader.get(4),description3);
        listHashMap.put(listDataHeader.get(5),description2);
//        listHashMap.put(listDataHeader.get(6),description3);
//        listHashMap.put(listDataHeader.get(7),description1);

    }
}
