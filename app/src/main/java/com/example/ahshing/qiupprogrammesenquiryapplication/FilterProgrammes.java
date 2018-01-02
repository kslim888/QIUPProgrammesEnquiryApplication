package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import entryRules.BAC;
import entryRules.BBA;
import entryRules.BBA_HospitalityTourismManagement;
import entryRules.BBS;
import entryRules.BCE;
import entryRules.BCS;
import entryRules.BEM;
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
import entryRules.FIS_MedicineDentistryPharmacy;
import entryRules.MBBS;
import entryRules.MassCommAdvertising;
import entryRules.MassCommJournalism;
import entryRules.Pharmacy;
import entryRules.TESL;
import fr.ganfra.materialspinner.MaterialSpinner;

public class FilterProgrammes extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    MaterialSpinner resultsSpinner, subjectsSpinner, gradesSpinner,
            subjectsSpinner1, gradesSpinner1,
            spmOLevelSpinner, mathematicsSpinner, englishSpinner,
            addMathSpinner, bahasaMalaysiaSpinner;
    ArrayAdapter<String> subjectsAdapter, gradesAdapter;
    Button filterButton;
    LinearLayout parentLinearLayout;
    TextView addNewField, deleteFieldText, subjectsText,
            gradesText, englishText, mathematicsText,
            spmOlevelText, addMathText, bahasaMalaysiaText;
    boolean flagForNewField;

    //TODO set minimum subjects and grades then only can generate. if not minimum toast message

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_programme);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find view by ID...
        parentLinearLayout = findViewById(R.id.parent_layout);
        filterButton = findViewById(R.id.filterButton);
        deleteFieldText = findViewById(R.id.deleteFieldText);
        addNewField = findViewById(R.id.addNewSpinnerText);
        subjectsText = findViewById(R.id.subjectsText);
        gradesText  = findViewById(R.id.gradesText);
        englishText = findViewById(R.id.englishText);
        mathematicsText = findViewById(R.id.mathematicsText);
        resultsSpinner = findViewById(R.id.resultsSpinner);
        subjectsSpinner = findViewById(R.id.subjectsSpinner);
        gradesSpinner = findViewById(R.id.gradesSpinner);
        spmOlevelText = findViewById(R.id.spmOlevelText);
        spmOLevelSpinner = findViewById(R.id.spmOLevelSpinner);
        mathematicsSpinner = findViewById(R.id.mathematicsSpinner);
        englishSpinner = findViewById(R.id.englishSpinner);
        addMathText = findViewById(R.id.addMathText);
        addMathSpinner = findViewById(R.id.addMathSpinner);
        bahasaMalaysiaSpinner = findViewById(R.id.bahasaMalaysiaSpinner);
        bahasaMalaysiaText = findViewById(R.id.bahasaMalaysiaText);

        // setting the results type Adapter
        String[] stringsResultsType = getResources().getStringArray(R.array.qualification_type);
        ArrayAdapter<String> resultsArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, stringsResultsType)
        {
            // If is position 0(the initial dummy entry), make it hidden
            // else, pass convertView as null to prevent reuse of special case views
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v;
                if (position == 0) {
                    CheckedTextView tv = new CheckedTextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    v = super.getDropDownView(position, null, parent);
                }
                return v;
            }
        };
        resultsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // setting the results type Adapter
        final String[] stringsSPMOLevelType = getResources().getStringArray(R.array.spm_or_oLevel_type);
        ArrayAdapter<String> SPMOLevelArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, stringsSPMOLevelType)
        {
            // If is position 0(the initial dummy entry), make it hidden
            // else, pass convertView as null to prevent reuse of special case views
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v;
                if (position == 0) {
                    CheckedTextView tv = new CheckedTextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    v = super.getDropDownView(position, null, parent);
                }
                return v;
            }
        };
        SPMOLevelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addNewField.setEnabled(false);
        addNewField.setTextColor(Color.GRAY);
        deleteFieldText.setEnabled(false);
        deleteFieldText.setTextColor(Color.GRAY);
        subjectsText.setTextColor(Color.GRAY);
        gradesText.setTextColor(Color.GRAY);
        flagForNewField = false;
        subjectsSpinner.setEnabled(false);
        gradesSpinner.setEnabled(false);
        filterButton.setEnabled(false);
        englishText.setVisibility(View.GONE);
        mathematicsText.setVisibility(View.GONE);
        spmOlevelText.setVisibility(View.GONE);
        spmOLevelSpinner.setVisibility(View.GONE);
        mathematicsSpinner.setVisibility(View.GONE);
        englishSpinner.setVisibility(View.GONE);
        addMathText.setVisibility(View.GONE);
        addMathSpinner.setVisibility(View.GONE);
        bahasaMalaysiaText.setVisibility(View.GONE);
        bahasaMalaysiaSpinner.setVisibility(View.GONE);
        spmOLevelSpinner.setAdapter(SPMOLevelArrayAdapter);
        spmOLevelSpinner.setOnItemSelectedListener(this);
        resultsSpinner.setAdapter(resultsArrayAdapter);
        resultsSpinner.setOnItemSelectedListener(this);
        setSpinnerScrollbar(); //set spinner scrollbar for resultsSpinner, subjectsSpinner and gradesSpinner

        //add new dropdown field
        addNewField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_dropdown_field, null);

                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 8);
                if(parentLinearLayout.getChildCount() != 11) // if added new field, make the delete field text enable
                {
                    deleteFieldText.setEnabled(true);
                    deleteFieldText.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                subjectsSpinner1 = rowView.findViewById(R.id.subjectsSpinner1);
                gradesSpinner1 = rowView.findViewById(R.id.gradesSpinner1);

                String selectedItem = resultsSpinner.getSelectedItem().toString();
                if(Objects.equals(selectedItem, "SPM"))
                {
                    if(parentLinearLayout.getChildCount() == 22) // max 12
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "O-Level")) // max 10
                {
                    if(parentLinearLayout.getChildCount() == 20)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "A-Level")) // max 5
                {
                    if(parentLinearLayout.getChildCount() == 15)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STPM")) // max 5
                {
                    if(parentLinearLayout.getChildCount() == 15)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STAM")) // max 11
                {
                    if(parentLinearLayout.getChildCount() == 21)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                //TODO UEC set maximum

                try {
                    loadSpinnerData(subjectsSpinner1, gradesSpinner1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteFieldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(parentLinearLayout.getChildCount() != 11) // if added new field
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 9);
                    addNewField.setEnabled(true);
                    addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(parentLinearLayout.getChildCount() == 11) // if deleted and back to original layout
                {
                    deleteFieldText.setEnabled(false);
                    deleteFieldText.setTextColor(Color.GRAY);
                }
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ArrayList<TextView> addedSubjectsList = new ArrayList<>();
                ArrayList<TextView> addedGradesList = new ArrayList<>();
                String spmOLevelString="", mathematicsGrade="", englishGrade="", addMathGrade="", bahasaMalaysiaGrade="";

                // get all the new added view value and add to a List
                for (int i = 0; i < parentLinearLayout.getChildCount(); i++)
                {
                    view = parentLinearLayout.getChildAt(i);
                    if(view instanceof LinearLayout)
                    {
                        if(view.getId() == R.id.newField)
                        {
                            subjectsSpinner1 = (MaterialSpinner) ((LinearLayout) view).getChildAt(0);
                            gradesSpinner1 = (MaterialSpinner) ((LinearLayout) view).getChildAt(1);
                            addedSubjectsList.add((TextView)subjectsSpinner1.getSelectedView());
                            addedGradesList.add((TextView)gradesSpinner1.getSelectedView());
                        }
                    }
                }

                // copy all subjects list to arrays
                String[] arrayStringSubjects = new String[addedSubjectsList.size() + 1];
                TextView selection = (TextView)subjectsSpinner.getSelectedView();
                String selectedItem = selection.getText().toString();
                arrayStringSubjects[0] = selectedItem;
                for(int i = 1; i < arrayStringSubjects.length; i++)
                    arrayStringSubjects[i] = addedSubjectsList.get(i-1).getText().toString();

                // copy all grades list to arrays
                String[] arrayStringGrades = new String[addedGradesList.size() + 1];
                selection = (TextView)gradesSpinner.getSelectedView();
                selectedItem = selection.getText().toString();
                arrayStringGrades[0] = selectedItem;
                for(int i = 1; i <  arrayStringSubjects.length; i++)
                    arrayStringGrades[i] = addedGradesList.get(i-1).getText().toString();

                selectedItem = resultsSpinner.getSelectedItem().toString(); // selectedItem lastly is Qualification Level

                //check got compulsory subjects or not
                if(Objects.equals(selectedItem, "SPM"))
                {
                    if(!Arrays.asList(arrayStringSubjects).contains("Bahasa Malaysia")
                            || !Arrays.asList(arrayStringSubjects).contains("Bahasa Inggeris")
                            || !Arrays.asList(arrayStringSubjects).contains("Sejarah")
                            || !Arrays.asList(arrayStringSubjects).contains("Mathematics"))
                    {
                        Toast.makeText(FilterProgrammes.this, "Subjects must include Bahasa Malaysia, Bahasa Inggeris, Sejarah and Mathematics", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                else if(Objects.equals(selectedItem, "STPM"))
                {
                    if(!Arrays.asList(arrayStringSubjects).contains("Pengajian Am"))
                    {
                        Toast.makeText(FilterProgrammes.this, "Subjects must include Pengajian Am", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                //changing the string value to proper subject and match with Rules
                for(int i = 0; i < arrayStringSubjects.length; i++)
                {
                    if(Objects.equals(arrayStringSubjects[i], "Bahasa Inggeris") || Objects.equals(arrayStringSubjects[i], "English Language"))
                    {
                        arrayStringSubjects[i] = "English";
                    }
                    if(Objects.equals(arrayStringSubjects[i], "Mathematics D")) // o-level
                    {
                        arrayStringSubjects[i] = "Mathematics";
                    }
                    if(Objects.equals(arrayStringSubjects[i], "Advanced Mathematics")) // uec
                    {
                        arrayStringSubjects[i] = "Additional Mathematics";
                    }
                }

                // if there are subjects is not selected
                for(int i = 0; i < arrayStringSubjects.length; i++)
                {
                    if(Objects.equals(arrayStringSubjects[i], ""))
                    {
                        Toast.makeText(FilterProgrammes.this, "There are subjects not selected", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                // if there are duplicated subjects
                for(int i = 0; i < arrayStringSubjects.length; i++)
                {
                    for(int j = i + 1; j < arrayStringSubjects.length; j++)
                    {
                        if(Objects.equals(arrayStringSubjects[i], arrayStringSubjects[j]))
                        {
                            Toast.makeText(FilterProgrammes.this, "There is a duplicate subjects", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }

                // if there are Grades is not selected
                for(int i = 0; i < arrayStringGrades.length; i++)
                {
                    if(Objects.equals(arrayStringGrades[i], ""))
                    {
                        Toast.makeText(FilterProgrammes.this, "There are grades not selected", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                // create facts
                Facts facts = new Facts();
                facts.put("Qualification Level", selectedItem);
                facts.put("Student's Subjects", arrayStringSubjects);
                facts.put("Student's Grades",arrayStringGrades);
                //spm or o-level
                if(spmOLevelSpinner.getSelectedView() != null)
                {
                    TextView spmOLevelText = (TextView)spmOLevelSpinner.getSelectedView();
                    spmOLevelString = spmOLevelText.getText().toString();
                    facts.put("Student's SPM or O-Level", spmOLevelString);
                }
                else
                {
                    facts.put("Student's SPM or O-Level", " ");
                }
                //bahasa malaysia
                if(bahasaMalaysiaSpinner.getSelectedView() != null)
                {
                    TextView bahasaMalaysiaText = (TextView)bahasaMalaysiaSpinner.getSelectedView();
                    bahasaMalaysiaGrade = bahasaMalaysiaText.getText().toString();
                    facts.put("Student's Bahasa Malaysia", bahasaMalaysiaGrade);
                }
                else
                {
                    facts.put("Student's Bahasa Malaysia", " ");
                }
                //maths
                if(mathematicsSpinner.getSelectedView() != null)
                {
                    TextView mathematicsText = (TextView)mathematicsSpinner.getSelectedView();
                    mathematicsGrade = mathematicsText.getText().toString();
                    facts.put("Student's Mathematics", mathematicsGrade);
                }
                else
                {
                    facts.put("Student's Mathematics", " ");
                }
                //english
                if(englishSpinner.getSelectedView() != null)
                {
                    TextView englishText = (TextView)englishSpinner.getSelectedView();
                    englishGrade = englishText.getText().toString();
                    facts.put("Student's English", englishGrade);
                }
                else
                {
                    facts.put("Student's English", " ");
                }
                //add math
                if(addMathSpinner.getSelectedView() != null)
                {
                    TextView addMathText = (TextView)addMathSpinner.getSelectedView();
                    addMathGrade = addMathText.getText().toString();
                    facts.put("Student's Additional Mathematics", addMathGrade);
                }
                else
                {
                    facts.put("Student's Additional Mathematics", " ");
                }

                //check second qualification grades error if the qualification is STPM, STAM or A-Level
                if(checkSpinnerError(spmOLevelString, mathematicsGrade, englishGrade, addMathGrade, bahasaMalaysiaGrade, selectedItem))
                {
                    return; // if error is true, dont do anything
                }

                //TODO @Fact("Student's English Test") String studentEnglishTest
                //TODO facts.put("Student's English Test", " ");

                // create and define rules
                Rules rules = new Rules(
                        new FIS(),
                        new FIBFIA(),
                        new FIS_MedicineDentistryPharmacy(),
                        new BBA(),
                        new BBA_HospitalityTourismManagement(),
                        new BFI(),
                        new BAC(),
                        new TESL(),
                        new CorporateComm(),
                        new MassCommAdvertising(),
                        new MassCommJournalism(),
                        new BCE(),
                        new BSNE(),
                        new BIS(),
                        new BCS(),
                        new ElectronicsCommunicationsEngineering(),
                        new Biotech(),
                        new BEM(),
                        new BIT(),
                        new BS_ActuarialSciences(),
                        new BBS(),
                        new MBBS(),
                        new Pharmacy(),
                        new DHM(),
                        new DBM(),
                        new DAC(),
                        new DCE(),
                        new DIS(),
                        new DET(),
                        new DME(),
                        new DIT()
                );
                //TODO new BET()

                // create a rules engine and fire rules on known facts
                RulesEngine rulesEngine = new DefaultRulesEngine();
                rulesEngine.fire(rules, facts);

                // send to next activity
                Bundle extras = new Bundle();
                extras.putString("QUALIFICATION_LEVEL", selectedItem);
                extras.putStringArray("STUDENT_SUBJECTS_LIST", arrayStringSubjects);
                extras.putStringArray("STUDENT_GRADES_LIST", arrayStringGrades);
                extras.putString("STUDENT_SECONDARY_QUALIFICATION", spmOLevelString);
                extras.putString("STUDENT_SECONDARY_MATH", mathematicsGrade);
                extras.putString("STUDENT_SECONDARY_ENG", englishGrade);
                extras.putString("STUDENT_SECONDARY_ADDMATH", addMathGrade);

                //Intent resultsOfFiltering = new Intent(FilterProgrammes.this, ResultsOfFiltering.class);
                //resultsOfFiltering.putExtras(extras);
                //startActivity(resultsOfFiltering);

                Intent resultsOfFiltering = new Intent(FilterProgrammes.this, PreferProgrammeAndEnglishProficiency.class);
                //resultsOfFiltering.putExtras(extras);
                startActivity(resultsOfFiltering);

                //reset back to default
                /*
                resultsSpinner.setSelection(0);
                while ( parentLinearLayout.getChildCount() != 4)
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 2);
                }
                subjectsSpinner.setSelection(0);
                gradesSpinner.setSelection(0);
                subjectsSpinner.setEnabled(false);
                gradesSpinner.setEnabled(false);
                filterButton.setEnabled(false);
                deleteFieldText.setEnabled(false);
                deleteFieldText.setTextColor(Color.GRAY);
                subjectsText.setTextColor(Color.GRAY);
                gradesText.setTextColor(Color.GRAY);
                */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.specific_programme, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        else if(item.getItemId() == R.id.specificProgramme)
        {
            Intent specificProgramme = new Intent(FilterProgrammes.this, SpecificProgramme.class);
            startActivity(specificProgramme);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkSpinnerError(String spmOLevelQualification,
                                      String secondMath,
                                      String secondEng,
                                      String secondAddMath,
                                      String secondBahasaMalaysia,
                                      String qualificationLevel)
    {
        if(Objects.equals(qualificationLevel, "STPM") || Objects.equals(qualificationLevel, "STAM") || Objects.equals(qualificationLevel, "A-Level"))
        {
            if(Objects.equals(spmOLevelQualification, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose SPM or O-Level Qualification", Toast.LENGTH_LONG).show();
                ((TextView)spmOLevelSpinner.getSelectedView()).setError("Error");
                spmOLevelSpinner.setEnableErrorLabel(true);
                return true;
            }
            if(Objects.equals(secondBahasaMalaysia, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose " + spmOLevelQualification + " Bahasa Malaysia grade", Toast.LENGTH_LONG).show();
                return true;
            }
            if(Objects.equals(secondEng, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose " + spmOLevelQualification + " English grade", Toast.LENGTH_LONG).show();
                return true;
            }
            if(Objects.equals(secondMath, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose " + spmOLevelQualification + " Mathematics grade", Toast.LENGTH_LONG).show();
                return true;
            }

            if(Objects.equals(secondAddMath, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose " + spmOLevelQualification + " Additional Mathematics grade", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    private void loadSpinnerData(MaterialSpinner s, MaterialSpinner g) throws IOException
    {
        s.setAdapter(subjectsAdapter);
        g.setAdapter(gradesAdapter);
        flagForNewField = true; // to only set spinner scrollbar for newly added field
        setSpinnerScrollbar();
    }

    //results and SPMOLevel spinner listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
    {
        String [] subjectsList, gradesList;

        switch(adapterView.getId())
        {
            case R.id.resultsSpinner:
            {
                switch(position)
                {
                    case -1: // if select back to hint
                    {
                        filterButton.setEnabled(false);
                        subjectsSpinner.setEnabled(false);
                        gradesSpinner.setEnabled(false);
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                        subjectsText.setTextColor(Color.GRAY);
                        gradesText.setTextColor(Color.GRAY);
                    }
                    break;

                    case 1: // SPM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.spm_subjects);
                        gradesList = getResources().getStringArray(R.array.spm_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            // If is position 0(the initial dummy entry), make it hidden
                            // else, pass convertView as null to prevent reuse of special case views
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    CheckedTextView tv = new CheckedTextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    CheckedTextView tv = new CheckedTextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 2: // STPM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.stpm_subjects);
                        gradesList = getResources().getStringArray(R.array.stpm_grades);

                        // subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else
                                {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 3: // UEC selected
                    {
                        subjectsList = getResources().getStringArray(R.array.uec_subjects);
                        gradesList = getResources().getStringArray(R.array.uec_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0)
                                {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 4: // O-Level selected
                    {
                        subjectsList = getResources().getStringArray(R.array.oLevel_subjects);
                        gradesList = getResources().getStringArray(R.array.oLevel_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 5: // A-Level selected
                    {
                        subjectsList = getResources().getStringArray(R.array.aLevel_subjects);
                        gradesList = getResources().getStringArray(R.array.aLevel_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else
                                {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 6: // STAM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.STAM_subjects);
                        gradesList = getResources().getStringArray(R.array.STAM_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, subjectsList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };

                        //grade list
                        gradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectsSpinner.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;
                }
            }
            break;

            case R.id.spmOLevelSpinner:
            {
                ArrayAdapter<String> spmOLevelGradesAdapter, spmOLevelAddMathGradesAdapter;
                String [] addMathGradeList;
                switch(position)
                {
                    case 1: // spm
                    {
                        gradesList = getResources().getStringArray(R.array.spm_grades);
                        addMathGradeList = getResources().getStringArray(R.array.add_math_spm_grades);
                        //grade list
                        spmOLevelGradesAdapter = new ArrayAdapter<String>(FilterProgrammes.this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        spmOLevelAddMathGradesAdapter = new ArrayAdapter<String>(FilterProgrammes.this, R.layout.spinner_text, addMathGradeList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelAddMathGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        bahasaMalaysiaSpinner.setAdapter(spmOLevelGradesAdapter);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
                        addMathSpinner.setAdapter(spmOLevelAddMathGradesAdapter);
                    }
                    break;

                    case 2: // o-level
                    {
                        gradesList = getResources().getStringArray(R.array.oLevel_grades);
                        addMathGradeList = getResources().getStringArray(R.array.add_math_oLevel_grades);
                        //grade list
                        spmOLevelGradesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, gradesList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        spmOLevelAddMathGradesAdapter = new ArrayAdapter<String>(FilterProgrammes.this, R.layout.spinner_text, addMathGradeList)
                        {
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent)
                            {
                                View v;
                                if (position == 0) {
                                    TextView tv = new TextView(getContext());
                                    tv.setHeight(0);
                                    tv.setVisibility(View.GONE);
                                    v = tv;
                                }
                                else {
                                    v = super.getDropDownView(position, null, parent);
                                }
                                return v;
                            }
                        };
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelAddMathGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        bahasaMalaysiaSpinner.setAdapter(spmOLevelGradesAdapter);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
                        addMathSpinner.setAdapter(spmOLevelAddMathGradesAdapter);
                    }
                    break;
                }
            }
            break;
        }

        if(adapterView.getId() == R.id.resultsSpinner)
        {
            if(position == 2 || position == 5 || position == 6)
            {
                spmOlevelText.setVisibility(View.VISIBLE);
                spmOLevelSpinner.setVisibility(View.VISIBLE);
                englishText.setVisibility(View.VISIBLE);
                englishSpinner.setVisibility(View.VISIBLE);
                mathematicsText.setVisibility(View.VISIBLE);
                mathematicsSpinner.setVisibility(View.VISIBLE);
                addMathText.setVisibility(View.VISIBLE);
                addMathSpinner.setVisibility(View.VISIBLE);
                bahasaMalaysiaText.setVisibility(View.VISIBLE);
                bahasaMalaysiaSpinner.setVisibility(View.VISIBLE);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
                addMathSpinner.setEnabled(false);
                bahasaMalaysiaSpinner.setEnabled(false);
            }
            else
            {
                addMathText.setVisibility(View.GONE);
                addMathSpinner.setVisibility(View.GONE);
                englishText.setVisibility(View.GONE);
                englishSpinner.setVisibility(View.GONE);
                mathematicsText.setVisibility(View.GONE);
                mathematicsSpinner.setVisibility(View.GONE);
                spmOlevelText.setVisibility(View.GONE);
                spmOLevelSpinner.setVisibility(View.GONE);
                bahasaMalaysiaText.setVisibility(View.GONE);
                bahasaMalaysiaSpinner.setVisibility(View.GONE);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
                addMathSpinner.setEnabled(false);
                bahasaMalaysiaSpinner.setEnabled(false);
            }
        }
        else if(adapterView.getId() == R.id.spmOLevelSpinner)
        {
            if(position != 0)
            {
                mathematicsSpinner.setEnabled(true);
                englishSpinner.setEnabled(true);
                addMathSpinner.setEnabled(true);
                bahasaMalaysiaSpinner.setEnabled(true);
            }
        }

        // after choose results type and set the adapters, make subjects and grades spinner enable
        // if both subject and grades spinner is disabled, make them both enable back
        if(adapterView.getId() == R.id.resultsSpinner)
        {
            if(position != -1)
            {
                filterButton.setEnabled(true);
                subjectsSpinner.setEnabled(true);
                gradesSpinner.setEnabled(true);
                addNewField.setEnabled(true);
                addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                subjectsText.setTextColor(getResources().getColor(R.color.colorAccent));
                gradesText.setTextColor(getResources().getColor(R.color.colorAccent));

                spmOLevelSpinner.setSelection(0);
                mathematicsSpinner.setSelection(0);
                englishSpinner.setSelection(0);
                addMathSpinner.setSelection(0);

                if(parentLinearLayout.getChildCount() != 11 ) // if added new view
                {
                    deleteFieldText.setEnabled(false);
                    deleteFieldText.setTextColor(Color.GRAY);
                    for(int i = parentLinearLayout.getChildCount(); i != 11; i--)  // if switch result, reset back to default
                    {
                        parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 9);
                    }
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    public void setSpinnerScrollbar() {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            if(flagForNewField) {
                android.widget.ListPopupWindow newSubjectsListPopupWindow = (android.widget.ListPopupWindow) popup.get(subjectsSpinner1);
                android.widget.ListPopupWindow newGradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner1);
                newSubjectsListPopupWindow.setHeight(500);
                newGradesListPopupWindow.setHeight(500);
                return;
            }

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow bahasaMalaysiaPopupWindow = (android.widget.ListPopupWindow) popup.get(bahasaMalaysiaSpinner);
            android.widget.ListPopupWindow addMathPopupWindow = (android.widget.ListPopupWindow) popup.get(addMathSpinner);
            android.widget.ListPopupWindow mathematicsPopupWindow = (android.widget.ListPopupWindow) popup.get(mathematicsSpinner);
            android.widget.ListPopupWindow englishPopupWindow = (android.widget.ListPopupWindow) popup.get(englishSpinner);
            android.widget.ListPopupWindow qualificationTypePopupWindow = (android.widget.ListPopupWindow) popup.get(resultsSpinner);
            android.widget.ListPopupWindow subjectsListPopupWindow = (android.widget.ListPopupWindow) popup.get(subjectsSpinner);
            android.widget.ListPopupWindow gradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner);

            // Set popupWindow height to 500px
            bahasaMalaysiaPopupWindow.setHeight(500);
            addMathPopupWindow.setHeight(500);
            mathematicsPopupWindow.setHeight(500);
            englishPopupWindow.setHeight(500);
            subjectsListPopupWindow.setHeight(500);
            gradesListPopupWindow.setHeight(500);
            qualificationTypePopupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
