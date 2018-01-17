package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import entryRules.BBS;
import entryRules.BCE;
import entryRules.BCS;
import entryRules.BEM;
import entryRules.BET;
import entryRules.BFI;
import entryRules.BIS;
import entryRules.BIT;
import entryRules.BSNE;
import entryRules.BS_ActuarialSciences;
import entryRules.Biotech;
import entryRules.CorporateComm;
import entryRules.DAC;
import entryRules.DBM;
import entryRules.DCE;
import entryRules.DET;
import entryRules.DHM;
import entryRules.DIS;
import entryRules.DIT;
import entryRules.DME;
import entryRules.ElectronicsCommunicationsEngineering;
import entryRules.FIBFIA;
import entryRules.FIS;
import entryRules.MBBS;
import entryRules.MassCommAdvertising;
import entryRules.MassCommJournalism;
import entryRules.Pharmacy;
import entryRules.TESL;

public class ResultsOfFiltering extends AppCompatActivity
{
    //TODO interest programme set marker for none, eligible and not eligible
    //TODO add all the requirements description
    //TODO Diploma requirements description
    //TODO Degree requirements description
    //TODO put and set the header and requirements description accordingly

    ExpandableListView eligibleListView, notEligibleListView;
    ExpandableListAdapter eligibleListAdapter, notEligibleListAdapter;
    List<String> eligibleDataHeader, notEligibleDataHeader,
            //Requirements Description
            fibfiaRequirements, fisRequirements,
            dbmRequirements, dhmRequirements, dacRequirements, dceRequirements,
            ditRequirements, disRequirements, dmeRequirements, detRequirements,
            bbaRequirements, bbaHospitalityRequirements, bacRequirements, bfiRequirements, teslRequirements,
            corporateCommRequirements, bmcAdvertisingRequirements, bmcJournalismRequirements,
            bceRequirements, bsneRequirements, bcsRequirements, bitRequirements, bisRequirements,
            eceRequirements, bemRequirements, biotechRequirements, betRequirements,
            actuarialScienceRequirementsm, pharmacyRequirements, bbsRequirements, mbbsRequirements;
    HashMap<String, List<String>> eligibleHashMap, notEligibleHashMap;
    String qualificationLevel;
    Bundle extras;
    TextView eligibleTextView, notEligibleTextView;
    String[] interestedProgramme;
    String otherInterestedProgramme;
    boolean isGotInterestedProgramme;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_of_filtering);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eligibleTextView = findViewById(R.id.eligibleTextView);
        notEligibleTextView = findViewById(R.id.notEligibleTextView);
        eligibleListView = findViewById(R.id.eligibleProgrammes);
        notEligibleListView = findViewById(R.id.notEligibleProgrammes);

        extras = getIntent().getExtras();
        qualificationLevel = extras.getString("QUALIFICATION_LEVEL");
        isGotInterestedProgramme = extras.getBoolean("STUDENT_IS_GOT_INTERESTED_PROGRAMME");
        interestedProgramme = extras.getStringArray("STUDENT_INTERESTED_PROGRAMME_LIST");
        otherInterestedProgramme = extras.getString("STUDENT_OTHER_INTERESTED_PROGRAMME");

        // Initialize variable for expandable list view.
        // Header
        eligibleDataHeader = new ArrayList<>();
        notEligibleDataHeader = new ArrayList<>();
        //HashMap to put into Adapter
        notEligibleHashMap = new HashMap<>();
        eligibleHashMap = new HashMap<>();
        //Foundation
        fibfiaRequirements = new ArrayList<>();
        fisRequirements = new ArrayList<>();
        //Diploma
        dbmRequirements = new ArrayList<>();
        dhmRequirements = new ArrayList<>();
        dacRequirements = new ArrayList<>();
        dceRequirements = new ArrayList<>();
        ditRequirements = new ArrayList<>();
        disRequirements = new ArrayList<>();
        dmeRequirements = new ArrayList<>();
        detRequirements = new ArrayList<>();
        //Degree
        bbaRequirements = new ArrayList<>();
        bbaHospitalityRequirements = new ArrayList<>();
        bacRequirements = new ArrayList<>();
        bfiRequirements = new ArrayList<>();
        teslRequirements = new ArrayList<>();
        corporateCommRequirements = new ArrayList<>();
        bmcAdvertisingRequirements = new ArrayList<>();
        bmcJournalismRequirements = new ArrayList<>();
        bceRequirements = new ArrayList<>();
        bsneRequirements = new ArrayList<>();
        bcsRequirements = new ArrayList<>();
        bitRequirements = new ArrayList<>();
        bisRequirements = new ArrayList<>();
        eceRequirements = new ArrayList<>();
        bemRequirements = new ArrayList<>();
        biotechRequirements = new ArrayList<>();
        betRequirements = new ArrayList<>();
        actuarialScienceRequirementsm = new ArrayList<>();
        pharmacyRequirements = new ArrayList<>();
        bbsRequirements = new ArrayList<>();
        mbbsRequirements = new ArrayList<>();

        initDescription();
        initData(); // After initialize, expandableListView will set adapter

        //TODO GIVE CORRECT/ELIGIBLE OR WRONG/NOTELIGIBLE boolean, based on qualification
        //give qualification, -> call isjoinprogramme -> false/true = hide/appear

        eligibleListAdapter = new ExpandableListAdapter(this, eligibleDataHeader, eligibleHashMap);
        eligibleListView.setAdapter(eligibleListAdapter);
        notEligibleListAdapter = new ExpandableListAdapter(this, notEligibleDataHeader, notEligibleHashMap);
        notEligibleListView.setAdapter(notEligibleListAdapter);
    }

    private void initDescription()
    {
        //Foundation requirements description
        fibfiaRequirements.add("SPM / O-Level \n 5 credits in any subjects");
        fibfiaRequirements.add("United Examination Certificate (UEC) \n - Minimum Grade B in 3 subjects");

        fisRequirements.add("SPM, O-Level or equivalent \n " +
                "- 5 credits with Mathematics, two Sciences subjects and any other two subjects \n " +
                "- Pass in Bahasa Malaysia and English Language ");



        //Diploma requirements description




        //Degree requirements description


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
            {
                this.finish();
            }
            break;
            case R.id.studentsAcademicQualification:
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
                resultTypeText.setText(qualificationLevel);

                //reference to english proficiency FIXME DOES NOT NEED NOW. May delete
                TextView englishProficiencyTestName = view.findViewById(R.id.dialogEnglishProficiencyTestName);
                TextView englishProficiencyTestLevel  = view.findViewById(R.id.dialogProficiencyLevel);
                TextView proficiencyLevelText = view.findViewById(R.id.proficiencyLevelText);
                TextView englishProficiencyTestText = view.findViewById(R.id.englishProficiencyTestText);

                englishProficiencyTestName.setText(extras.getString("STUDENT_ENGLISH_PROFICIENCY_TEST_NAME"));
                if(!Objects.equals(englishProficiencyTestName.getText().toString(), "None"))
                {
                    englishProficiencyTestLevel.setText(extras.getString("STUDENT_ENGLISH_PROFICIENCY_LEVEL"));
                }
                else
                {
                    proficiencyLevelText.setVisibility(View.GONE);
                    englishProficiencyTestLevel.setVisibility(View.GONE);
                }

                //FIXME For now, set hidden for all the proficiency related things
                englishProficiencyTestText.setVisibility(View.GONE);
                englishProficiencyTestName.setVisibility(View.GONE);
                englishProficiencyTestLevel.setVisibility(View.GONE);
                proficiencyLevelText.setVisibility(View.GONE);

                //reference to the vertical bar
                View topbar = view.findViewById(R.id.topBar);
                View bottomBar = view.findViewById(R.id.bottomBar);

                //reference to the second qualification level and set text
                TextView secondQualificationLevelText = view.findViewById(R.id.secondQualificationLevelText);
                TextView secondQualificationLevel = view.findViewById(R.id.secondQualificationLevel);
                TextView bahasaMalaysiaText = view.findViewById(R.id.bahasaMalaysiaText);
                TextView bahasaMalaysiaGrade = view.findViewById(R.id.bahasaMalaysiaGrade);
                TextView secondEngText = view.findViewById(R.id.secondEngText);
                TextView secondEngGrade = view.findViewById(R.id.englishGrade);
                TextView secondMathText = view.findViewById(R.id.secondMathText);
                TextView secondMathGrade = view.findViewById(R.id.mathGrade);
                TextView secondAddMathText = view.findViewById(R.id.secondAddMathText);
                TextView secondAddMathGrade = view.findViewById(R.id.addMathGrade);

                if(Objects.equals(qualificationLevel, "STPM")
                        || Objects.equals(qualificationLevel, "A-Level")
                        || Objects.equals(qualificationLevel, "STAM"))
                {
                    // set the secondary qualification data
                    bahasaMalaysiaGrade.setText(extras.getString("STUDENT_SECONDARY_BM"));
                    secondQualificationLevel.setText(extras.getString("STUDENT_SECONDARY_QUALIFICATION"));
                    secondEngGrade.setText(extras.getString("STUDENT_SECONDARY_ENG"));
                    secondMathGrade.setText(extras.getString("STUDENT_SECONDARY_ENG"));
                    secondAddMathGrade.setText(extras.getString("STUDENT_SECONDARY_ADDMATH"));

                    materialDialog.getWindow().setLayout(1000, 1100);
                }
                else
                {
                    //textView
                    secondQualificationLevelText.setVisibility(View.GONE);
                    secondMathText.setVisibility(View.GONE);
                    secondEngText.setVisibility(View.GONE);
                    secondAddMathText.setVisibility(View.GONE);
                    bahasaMalaysiaText.setVisibility(View.GONE);
                    //grade
                    secondQualificationLevel.setVisibility(View.GONE);
                    secondMathGrade.setVisibility(View.GONE);
                    secondEngGrade.setVisibility(View.GONE);
                    secondAddMathGrade.setVisibility(View.GONE);
                    bahasaMalaysiaGrade.setVisibility(View.GONE);
                    topbar.setVisibility(View.GONE);
                    bottomBar.setVisibility(View.GONE);

                    materialDialog.getWindow().setLayout(1000, 800);
                }
                materialDialog.show();
                materialDialog.getTitleView().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            }
            break;
            case  R.id.switchEligibility:
            {
                // switching between not eligible and eligible programme
                // using Eligible TextView as indicator
                if(eligibleTextView.getVisibility() == View.VISIBLE) // switch to not eligible
                {
                    eligibleTextView.setVisibility(View.GONE);
                    eligibleListView.setVisibility(View.GONE);
                    notEligibleTextView.setVisibility(View.VISIBLE);
                    notEligibleListView.setVisibility(View.VISIBLE);
                    item.getIcon().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY); // set the icon color to gray
                }
                else if(eligibleTextView.getVisibility() == View.GONE) // switch to eligible
                {
                    eligibleTextView.setVisibility(View.VISIBLE);
                    eligibleListView.setVisibility(View.VISIBLE);
                    notEligibleTextView.setVisibility(View.GONE);
                    notEligibleListView.setVisibility(View.GONE);
                    item.getIcon().clearColorFilter(); // set back the icon to normal color
                }
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData()
    {
        // if no interest programme, do checking for all programmes
        if(!isGotInterestedProgramme)
        {
            // for SPM and O-Level add programme
            if(Objects.equals(qualificationLevel, "SPM") || Objects.equals(qualificationLevel, "O-Level"))
            {
                addFoundationJoinProgramme();
                addDiplomaJoinProgramme();
            }

            // for STPM, A-Level, STAM add programme
            if(!Objects.equals(qualificationLevel, "SPM")
                    && !Objects.equals(qualificationLevel, "O-Level")
                    && !Objects.equals(qualificationLevel, "UEC"))
            {
                addDiplomaJoinProgramme();
                addDegreeJoinProgramme();
            }

            // for UEC add programme
            if(Objects.equals(qualificationLevel, "UEC"))
            {
                addFoundationJoinProgramme();
                addDiplomaJoinProgramme();
                addDegreeJoinProgramme();
            }
        }
        else //got interested programme
        {
            enquiryInterestedProgramme();
        }
    }

    private void enquiryInterestedProgramme()
    {
        for(int i = 0; i < interestedProgramme.length; i++)
        {
            switch(interestedProgramme[i])
            {
                // access to specific URL according to programme name
                // Foundation
                case "Foundation in Arts":
                {
                    if(FIBFIA.isJoinProgramme())
                    {
                        eligibleDataHeader.add("Foundation in Arts");
                    }
                    else
                    {
                        notEligibleDataHeader.add("Foundation in Arts");
                    }
                }
                break;
                case "Foundation in Business":
                {
                    if(FIBFIA.isJoinProgramme())
                    {
                        eligibleDataHeader.add("Foundation in Business");
                    }
                    else
                    {
                        notEligibleDataHeader.add("Foundation in Business");
                    }
                }
                break;
                case "Foundation in Sciences":
                {
                    if(FIS.isJoinProgramme())
                    {
                        eligibleDataHeader.add("Foundation in Sciences");
                    }
                    else
                    {
                        notEligibleDataHeader.add("Foundation in Sciences");
                    }
                }
                break;

                // Diploma
                case "Diploma in Business Management":
                {

                }
                break;
                case "Diploma in Hotel Management":
                {

                }
                break;
                case "Diploma of Accountancy":
                {

                }
                break;
                case "Diploma in Early Childhood Education":
                {

                }
                break;
                case "Diploma in Information Technology":
                {

                }
                break;
                case "Diploma in Business Information System":
                {

                }
                break;
                case "Diploma in Mechatronics Engineering":
                {

                }
                break;
                case "Diploma in Environmental Technology":
                {

                }
                break;

                //Degree
                case "Bachelor of Business Administration (Hons)":
                {

                }
                break;
                case "BBA (Hons) in Hospitality & Tourism Management":
                {

                }
                break;
                case "Bachelor of Accountancy (Hons)":
                {

                }
                break;
                case "Bachelor of Finance (Hons)":
                {

                }
                break;
                case "Bachelor of Arts (Hons) TESL":
                {

                }
                break;
                case "Bachelor of Corporate Communication (Hons)":
                {

                }
                break;
                case "Bachelor of Mass Communication (Hons) Journalism":
                {

                }
                break;
                case "Bachelor of Mass Communication (Hons) Advertising":
                {

                }
                break;
                case "Bachelor of Early Childhood Education (Hons)":
                {

                }
                break;
                case "Bachelor of Special Needs Education (Hons)":
                {

                }
                case "Bachelor of Computer Science (Hons)":
                {

                }
                break;
                case "Bachelor of Information Technology (Hons)":
                {

                }
                break;
                case "Bachelor of Business Information System (Hons)":
                {

                }
                break;
                case "Bachelor of Engineering (Hons) Electronics & Communications Engineering":
                {

                }
                break;
                case "Bachelor of Engineering (Hons) in Mechatronics":
                {

                }
                break;
                case "Bachelor of Science (Hons) in Biotechnology":
                {

                }
                break;
                case "Bachelor of Environmental Technology (Hons)":
                {

                }
                break;
                case "Bachelor of Science (Hons) Actuarial Sciences":
                {

                }
                break;
                case "Bachelor of Pharmacy (Hons)":
                {

                }
                break;
                case "Bachelor of Biomedical Sciences (Hons)":
                {

                }
                break;
                case "Bachelor of Medicine & Bachelor of Surgery":
                {

                }
                break;
            }
        }

        //TODO add hashmap HERE
        // first is header string, second is description
        notEligibleHashMap.put(notEligibleDataHeader.get(0),fibfiaRequirements);
    }

    private void addFoundationJoinProgramme()
    {
        if(FIBFIA.isJoinProgramme())
        {
            eligibleDataHeader.add("Foundation in Business");
            eligibleDataHeader.add("Foundation in Arts");
        }
        else
        {
            notEligibleDataHeader.add("Foundation in Business");
            notEligibleDataHeader.add("Foundation in Arts");
        }
        if(FIS.isJoinProgramme())
        {
            eligibleDataHeader.add("Foundation in Sciences");
        }
        else
        {
            notEligibleDataHeader.add("Foundation in Sciences");
        }
    }

    private void addDiplomaJoinProgramme()
    {
        if(DBM.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Business Management");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Business Management");
        }

        if(DHM.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Hotel Management");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Hotel Management");
        }

        if(DAC.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma of Accountancy");
        }
        else
        {
            notEligibleDataHeader.add("Diploma of Accountancy");
        }

        if(DCE.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Early Childhood Education");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Early Childhood Education");
        }

        if(DIT.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Information Technology");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Information Technology");
        }

        if(DIS.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Business Information System");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Business Information System");
        }

        if(DME.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Mechatronics Engineering");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Mechatronics Engineering");
        }

        if(DET.isJoinProgramme())
        {
            eligibleDataHeader.add("Diploma in Environmental Technology");
        }
        else
        {
            notEligibleDataHeader.add("Diploma in Environmental Technology");
        }
    }

    private void addDegreeJoinProgramme()
    {
        // Faculty of Business, management & Social Sciences
        if(BBA.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Business Administration (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Business Administration (Hons)");
        }

        if(BBA_HospitalityTourismManagement.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Business Administration (Hons) in Hospitality & Tourism Management");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Business Administration (Hons) in Hospitality & Tourism Management");
        }

        if(BAC.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Accountancy (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Accountancy (Hons)");
        }

        if(BFI.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Finance (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Finance (Hons)");
        }

        if(TESL.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Arts (Hons) TESL");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Arts (Hons) TESL");
        }

        if(CorporateComm.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Corporate Communication (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Corporate Communication (Hons)");
        }

        if(MassCommJournalism.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Mass Communication (Hons) Journalism");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Mass Communication (Hons) Journalism");
        }

        if(MassCommAdvertising.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Mass Communication (Hons) Advertising");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Mass Communication (Hons) Advertising");
        }

        if(BCE.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Early Childhood Education (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Early Childhood Education (Hons)");
        }
        if(BSNE.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Special Needs Education (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Special Needs Education (Hons)");
        }

        //Faculty of Integrative Sciences & Technology
        if(BCS.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Computer Science (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Computer Science (Hons)");
        }

        if(BIT.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Information Technology (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Information Technology (Hons)");
        }

        if(BIS.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Business Information System (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Business Information System (Hons)");
        }

        if(ElectronicsCommunicationsEngineering.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Engineering (Hons) Electronics & Communications Engineering");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Engineering (Hons) Electronics & Communications Engineering");
        }

        if(BEM.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Engineering (Hons) in Mechatronics");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Engineering (Hons) in Mechatronics");
        }

        if(Biotech.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Science (Hons) in Biotechnology");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Science (Hons) in Biotechnology");
        }

        if(BS_ActuarialSciences.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Science (Hons) Actuarial Sciences");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Science (Hons) Actuarial Sciences");
        }

        //Faculty of Medicine

        if(MBBS.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Medicine & Bachelor of Surgery (MBBS)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Medicine & Bachelor of Surgery (MBBS)");
        }

        if(BBS.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Biomedical Sciences (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Biomedical Sciences (Hons)");
        }

        //Faculty of Pharmacy
        if(Pharmacy.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Pharmacy (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Pharmacy (Hons)");
        }

        if(BET.isJoinProgramme())
        {
            eligibleDataHeader.add("Bachelor of Environmental Technology (Hons)");
        }
        else
        {
            notEligibleDataHeader.add("Bachelor of Environmental Technology (Hons)");
        }
    }
}
