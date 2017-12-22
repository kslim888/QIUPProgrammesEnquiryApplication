package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import entryRules.BAC;
import entryRules.BBA;
import entryRules.BBA_HospitalityTourismManagement;
import entryRules.BCE;
import entryRules.BFI;
import entryRules.CorporateComm;
import entryRules.FIBFIA;
import entryRules.FIS;
import entryRules.FIS_MedicineDentistryPharmacy;
import entryRules.MassCommAdvertising;
import entryRules.MassCommJournalism;
import entryRules.TESL;

public class ResultsOfFiltering extends AppCompatActivity
{
    ExpandableListView eligibleListView, ineligibleListView;
    ExpandableListAdapter eligibleListAdapter, ineligibleListAdapter;
    List<String> eligibleDataHeader, ineligibleDataHeader;
    HashMap<String, List<String>> eligibleHashMap, ineligibleHashMap;
    String resultType;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_of_filtering);

        eligibleListView = findViewById(R.id.eligibleProgrammes);
        ineligibleListView = findViewById(R.id.ineligibleProgrammes);
        extras = getIntent().getExtras();
        resultType = extras.getString("QUALIFICATION_LEVEL");
        initData();

        eligibleListAdapter = new ExpandableListAdapter(this, eligibleDataHeader, eligibleHashMap);
        eligibleListView.setAdapter(eligibleListAdapter);
        eligibleListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });

        ineligibleListAdapter = new ExpandableListAdapter(this, ineligibleDataHeader, ineligibleHashMap);
        ineligibleListView.setAdapter(ineligibleListAdapter);
        ineligibleListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                setListViewHeight(expandableListView, i);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        MaterialDialog materialDialog = new MaterialDialog.Builder(ResultsOfFiltering.this)
                .title("Student's Academic Qualification Info")
                .customView(R.layout.dialog_list_view, false)
                .positiveText("OK")
                .build();
        View view = materialDialog.getCustomView();

        //get student's academic data
        String[] subjectsStringArray = extras.getStringArray("STUDENT_SUBJECTS_LIST");
        String[] gradesStringArray = extras.getStringArray("STUDENT_GRADES_LIST");

        //reference to list view in dialog
        ListView listView = view.findViewById(R.id.dialogListView);
        CustomAdapter customAdapter = new CustomAdapter(this, subjectsStringArray, gradesStringArray);
        listView.setAdapter(customAdapter);

        //reference to the qualification level and set text
        TextView resultTypeText = view.findViewById(R.id.resultTypeDialog);
        resultTypeText.setText(resultType);

        //reference to the vertical bar
        View topbar = view.findViewById(R.id.topBar);
        View bottomBar = view.findViewById(R.id.bottomBar);

        //reference to the second qualification level and set text
        TextView secondQualificationLevelText = view.findViewById(R.id.secondQualificationLevelText);
        TextView secondQualificationLevel = view.findViewById(R.id.secondQualificationLevel);
        TextView secondMathText = view.findViewById(R.id.secondMathText);
        TextView secondMathGrade = view.findViewById(R.id.mathGrade);
        TextView secondEngText = view.findViewById(R.id.secondEngText);
        TextView secondEngGrade = view.findViewById(R.id.englishGrade);
        TextView secondAddMathText = view.findViewById(R.id.secondAddMathText);
        TextView secondAddMathGrade = view.findViewById(R.id.addMathGrade);
        if(Objects.equals(resultType, "SPM") || Objects.equals(resultType, "O-Level"))
        {
            //textView
            secondQualificationLevelText.setVisibility(View.GONE);
            secondMathText.setVisibility(View.GONE);
            secondEngText.setVisibility(View.GONE);
            secondAddMathText.setVisibility(View.GONE);
            //grade
            secondQualificationLevel.setVisibility(View.GONE);
            secondMathGrade.setVisibility(View.GONE);
            secondEngGrade.setVisibility(View.GONE);
            secondAddMathGrade.setVisibility(View.GONE);
            topbar.setVisibility(View.GONE);
            bottomBar.setVisibility(View.GONE);
            //set the list view height
            ViewGroup.LayoutParams param = listView.getLayoutParams();
            param.height = 800;
            listView.setLayoutParams(param);
            listView.requestLayout();
        }
        else
        {
            // get the secondary qualification data
            String secondaryQualificationLevel  = extras.getString("STUDENT_SECONDARY_QUALIFICATION");
            String secondaryMathGrade = extras.getString("STUDENT_SECONDARY_MATH");
            String secondaryEngGrade  = extras.getString("STUDENT_SECONDARY_ENG");
            String secondaryAddMathGrade = extras.getString("STUDENT_SECONDARY_ADDMATH");
            secondQualificationLevel.setText(secondaryQualificationLevel);
            secondMathGrade.setText(secondaryMathGrade);
            secondEngGrade.setText(secondaryEngGrade);
            secondAddMathGrade.setText(secondaryAddMathGrade);
        }

        materialDialog.show();
        materialDialog.getTitleView().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        materialDialog.getWindow().setLayout(1000, 1100);
        return super.onOptionsItemSelected(item);
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
        eligibleDataHeader = new ArrayList<>();
        eligibleHashMap = new HashMap<>();

        ineligibleDataHeader = new ArrayList<>();
        ineligibleHashMap = new HashMap<>();

        //Foundation
        if(Objects.equals(resultType, "SPM") || Objects.equals(resultType, "O-Level"))
        {
            if(FIS.isJoinProgramme())
            {
                eligibleDataHeader.add("Foundation in Sciences");
            }
            else
            {
                ineligibleDataHeader.add("Foundation in Sciences");
            }
            if(FIBFIA.isJoinProgramme())
            {
                eligibleDataHeader.add("Foundation in Business");
                eligibleDataHeader.add("Foundation in Arts");
            }
            else
            {
                ineligibleDataHeader.add("Foundation in Business");
                ineligibleDataHeader.add("Foundation in Arts");
            }
            if(FIS_MedicineDentistryPharmacy.isJoinProgramme())
            {
                eligibleDataHeader.add("Foundation in Sciences\n(to pursue degree programme in Medicine, Dentistry or Pharmacy)");
            }
            else
            {
                ineligibleDataHeader.add("Foundation in Sciences\n(to pursue degree programme in Medicine, Dentistry or Pharmacy)");
            }
        }
        else
        {
            //Faculty of Business, management & Social Sciences
            if(BBA.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Business Administration (Hons)");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Business Administration (Hons)");
            }

            if(BBA_HospitalityTourismManagement.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Business Administration (Hons) in Hospitality & Tourism Management");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Business Administration (Hons) in Hospitality & Tourism Management");
            }

            if(BAC.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Accountancy (Hons)");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Accountancy (Hons)");
            }

            if(BFI.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Finance (Hons)");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Finance (Hons)");
            }

            if(TESL.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Arts (Hons) TESL");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Arts (Hons) TESL");
            }

            if(CorporateComm.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Corporate Communication (Hons)");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Corporate Communication (Hons)");
            }

            if(MassCommJournalism.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Mass Communication (Hons) Journalism");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Mass Communication (Hons) Journalism");
            }

            if(MassCommAdvertising.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Mass Communication (Hons) Advertising");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Mass Communication (Hons) Advertising");
            }

            if(BCE.isJoinProgramme())
            {
                eligibleDataHeader.add("Bachelor of Early Childhood Education (Hons)");
            }
            else
            {
                ineligibleDataHeader.add("Bachelor of Early Childhood Education (Hons)");
            }

           // eligibleDataHeader.add("Bachelor of Special Needs Education (Hons)");
        }


    /*
        eligibleDataHeader.add("Master of Business Administration (MBA)");
        eligibleDataHeader.add("Diploma in Business Management");
        eligibleDataHeader.add("Diploma in Hotel Management");
        eligibleDataHeader.add("Diploma of Accountancy");
        eligibleDataHeader.add("Diploma in Early Childhood Education");

        //Faculty of Integrative Sciences & Technology
        eligibleDataHeader.add("Bachelor of Computer Science (Hons)");
        eligibleDataHeader.add("Bachelor of Information Technology (Hons)");
        eligibleDataHeader.add("Bachelor of Business Information System (Hons)");
        eligibleDataHeader.add("Diploma in Information Technology");
        eligibleDataHeader.add("Diploma in Business Information System");

        eligibleDataHeader.add("Bachelor of Engineering (Hons) Electronics & Communications Engineering");
        eligibleDataHeader.add("Bachelor of Engineering (Hons) in Mechatronics");
        eligibleDataHeader.add("Diploma in Mechatronics Engineering");
        eligibleDataHeader.add("Bachelor of Science (Hons) in Biotechnology");
        eligibleDataHeader.add("Bachelor of Science (Hons) in Chemistry");
        eligibleDataHeader.add("Bachelor of Environmental Technology (Hons)");
        eligibleDataHeader.add("Bachelor of Science (Hons) Actuarial Sciences");
        eligibleDataHeader.add("Diploma in Environmental Technology");

        //Faculty of Pharmacy
        eligibleDataHeader.add("Bachelor of Pharmacy (Hons)");

        //Faculty of Medicine
        eligibleDataHeader.add("Bachelor of Medicine & Bachelor of Surgery (MBBS)");
        eligibleDataHeader.add("Bachelor of Biomedical Sciences (Hons)");

        //Centre for Graduate Studies and Research
        eligibleDataHeader.add("Master of Business Administration");
        eligibleDataHeader.add("Master of Science");
        eligibleDataHeader.add("PhD in Business Administration");
        eligibleDataHeader.add("PhD in Science");
        eligibleDataHeader.add("Doctor in Business Administration");
*/
        // here is requirement of the programme, or child of the programme group
        List<String> fibfiaRequirements = new ArrayList<>();
        fibfiaRequirements.add("ABC");

        List<String> fisRequirements = new ArrayList<>();
        fisRequirements.add("ABC");
        fisRequirements.add("DEF");
        fisRequirements.add("GHI");

        List<String> fisOtherRequirements = new ArrayList<>();
        fisOtherRequirements.add("123");
        fisOtherRequirements.add("456");
        fisOtherRequirements.add("789");
        fisOtherRequirements.add("10");

        // added description only can scroll
       eligibleHashMap.put(eligibleDataHeader.get(0),fibfiaRequirements);
        eligibleHashMap.put(eligibleDataHeader.get(1),fisRequirements);
//        eligibleHashMap.put(eligibleDataHeader.get(2),fisOtherRequirements);
//        eligibleHashMap.put(eligibleDataHeader.get(3),fisOtherRequirements);
//        eligibleHashMap.put(eligibleDataHeader.get(4),fisOtherRequirements);
//        eligibleHashMap.put(eligibleDataHeader.get(5),fisOtherRequirements);
        //listHashMap.put(listDataHeader.get(6),description3);
//        listHashMap.put(listDataHeader.get(7),description1);
    }
}
