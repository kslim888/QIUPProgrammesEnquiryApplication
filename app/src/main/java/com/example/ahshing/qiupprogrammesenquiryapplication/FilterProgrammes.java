package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

import entryRules.BAC;
import entryRules.BBA;
import entryRules.BBA_HospitalityTourismManagement;
import entryRules.BFI;
import entryRules.FIBFIA;
import entryRules.FIS;
import entryRules.FIS_MedicineDentistryPharmacy;
import fr.ganfra.materialspinner.MaterialSpinner;

public class FilterProgrammes extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    MaterialSpinner resultsSpinner, subjectsSpinner, gradesSpinner, subjectsSpinner1,
            gradesSpinner1, spmOLevelSpinner, mathematicsSpinner, englishSpinner;
    ArrayAdapter<String> subjectsAdapter, gradesAdapter;
    Button filterButton;
    LinearLayout parentLinearLayout;
    TextView addNewField, deleteFieldText, subjectsText,
            gradesText, englishText, mathematicsText, spmOlevelText;
    boolean flagForNewField;

    //TODO set minimum subjects and grades then only can generate. if not minimum toast message

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_programme);

        //find view by ID...
        parentLinearLayout = findViewById(R.id.parent_layout);
        filterButton = findViewById(R.id.filterButton);
        deleteFieldText = findViewById(R.id.deleteFieldText);
        addNewField = findViewById(R.id.addNewSpinnerField);
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
        String[] stringsSPMOLevelType = getResources().getStringArray(R.array.spm_or_oLevel_type);
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
        mathematicsSpinner.setEnabled(false);
        englishSpinner.setEnabled(false);
        subjectsSpinner.setEnabled(false);
        gradesSpinner.setEnabled(false);
        filterButton.setEnabled(false);
        englishText.setVisibility(View.GONE);
        mathematicsText.setVisibility(View.GONE);
        spmOlevelText.setVisibility(View.GONE);
        spmOLevelSpinner.setVisibility(View.GONE);
        mathematicsSpinner.setVisibility(View.GONE);
        englishSpinner.setVisibility(View.GONE);
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
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 6);
                if(parentLinearLayout.getChildCount() != 9) // if added new field, make the delete field text enable
                {
                    deleteFieldText.setEnabled(true);
                    deleteFieldText.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                subjectsSpinner1 = rowView.findViewById(R.id.subjectsSpinner1);
                gradesSpinner1 = rowView.findViewById(R.id.gradesSpinner1);

                String selectedItem = resultsSpinner.getSelectedItem().toString();
                if(Objects.equals(selectedItem, "SPM"))
                {
                    if(parentLinearLayout.getChildCount() == 20) // max 12
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "O-Level")) // max 10
                {
                    if(parentLinearLayout.getChildCount() == 18)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "A-Level")) // max 4
                {
                    if(parentLinearLayout.getChildCount() == 12)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STPM")) // max 5
                {
                    if(parentLinearLayout.getChildCount() == 13)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STAM")) // max 11
                {
                    if(parentLinearLayout.getChildCount() == 19)
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
                if(parentLinearLayout.getChildCount() != 9) // if added new field
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 7);
                    addNewField.setEnabled(true);
                    addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(parentLinearLayout.getChildCount() == 9) // if deleted and back to original layout
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

                // copy all subjects list to arrays and pass to next activity
                String[] arrayStringSubjects = new String[addedSubjectsList.size() + 1];
                TextView selection = (TextView)subjectsSpinner.getSelectedView();
                String selectedItem = selection.getText().toString();
                arrayStringSubjects[0] = selectedItem;
                for(int i = 1; i < arrayStringSubjects.length; i++)
                    arrayStringSubjects[i] = addedSubjectsList.get(i-1).getText().toString();

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

                String[] arrayStringGrades = new String[addedGradesList.size() + 1];
                selection = (TextView)gradesSpinner.getSelectedView();
                selectedItem = selection.getText().toString();
                arrayStringGrades[0] = selectedItem;
                for(int i = 1; i <  arrayStringSubjects.length; i++)
                    arrayStringGrades[i] = addedGradesList.get(i-1).getText().toString();

                selectedItem = resultsSpinner.getSelectedItem().toString(); // lastly is qualification level

                // create facts
                Facts facts = new Facts();
                facts.put("Qualification Level", selectedItem);
                facts.put("Student's Subjects", arrayStringSubjects);
                facts.put("Student's Grades",arrayStringGrades);

                //spm or o-level
                if(spmOLevelSpinner.getSelectedView() != null)
                {
                    TextView spmOLevelText = (TextView)spmOLevelSpinner.getSelectedView();
                    String spmOLevelString = spmOLevelText.getText().toString();
                    facts.put("Student's SPM or O-Level", spmOLevelString);
                    Log.d("SPMOLevel", "onClick: " + spmOLevelString);
                }
                else
                {
                    facts.put("Student's SPM or O-Level", " ");
                    Log.d("SPMOLevel", "onClick: ");
                }

                //maths
                if(mathematicsSpinner.getSelectedView() != null)
                {
                    TextView mathematicsText = (TextView)mathematicsSpinner.getSelectedView();
                    String mathematicsString = mathematicsText.getText().toString();
                    facts.put("Student's Mathematics", mathematicsString);
                    Log.d("Student's Mathematics", "onClick: " + mathematicsString);
                }
                else
                {
                    facts.put("Student's Mathematics", " ");
                    Log.d("Student's Mathematics", "onClick: ");
                }

                //english
                if(englishSpinner.getSelectedView() != null)
                {
                    TextView englishText = (TextView)englishSpinner.getSelectedView();
                    String englishString = englishText.getText().toString();
                    facts.put("Student's English", englishString);
                    Log.d("Student's English", "onClick: " + englishString);
                }
                else
                {
                    facts.put("Student's English", " ");
                    Log.d("Student's English", "onClick: ");
                }

                // create and define rules
                Rules rules = new Rules(
                        new FIS(),
                        new FIBFIA(),
                        new FIS_MedicineDentistryPharmacy(),
                        new BBA(),
                        new BBA_HospitalityTourismManagement(),
                        new BFI(),
                        new BAC()
                );

                // create a rules engine and fire rules on known facts
                RulesEngine rulesEngine = new DefaultRulesEngine();
                rulesEngine.fire(rules, facts);

                // send to next activity
                Bundle extras = new Bundle();
                extras.putString("RESULT_TYPE", selectedItem);
                extras.putStringArray("STUDENT_SUBJECTS_LIST", arrayStringSubjects);
                extras.putStringArray("STUDENT_GRADES_LIST", arrayStringGrades);

                Intent resultsOfFiltering = new Intent(FilterProgrammes.this, ResultsOfFiltering.class);
                resultsOfFiltering.putExtras(extras);
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
                ArrayAdapter<String> spmOLevelGradesAdapter;
                switch(position)
                {
                    case 1: // spm
                    {
                        gradesList = getResources().getStringArray(R.array.spm_grades);
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
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
                    }
                    break;

                    case 2: // o-level
                    {
                        gradesList = getResources().getStringArray(R.array.oLevel_grades);
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
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
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
                englishText.setVisibility(View.VISIBLE);
                mathematicsText.setVisibility(View.VISIBLE);
                spmOlevelText.setVisibility(View.VISIBLE);
                spmOLevelSpinner.setVisibility(View.VISIBLE);
                mathematicsSpinner.setVisibility(View.VISIBLE);
                englishSpinner.setVisibility(View.VISIBLE);
                spmOLevelSpinner.setSelection(0);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
            }
            else
            {
                englishText.setVisibility(View.GONE);
                mathematicsText.setVisibility(View.GONE);
                spmOlevelText.setVisibility(View.GONE);
                spmOLevelSpinner.setVisibility(View.GONE);
                mathematicsSpinner.setVisibility(View.GONE);
                englishSpinner.setVisibility(View.GONE);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
            }
        }
        else if(adapterView.getId() == R.id.spmOLevelSpinner)
        {
            if(position != 0)
            {
                mathematicsSpinner.setEnabled(true);
                englishSpinner.setEnabled(true);
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

                if(parentLinearLayout.getChildCount() != 9 ) // if added new view
                {
                    deleteFieldText.setEnabled(false);
                    deleteFieldText.setTextColor(Color.GRAY);
                    for(int i = parentLinearLayout.getChildCount(); i != 9; i--)  // if switch result, reset back to default
                    {
                        parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 7);
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
            android.widget.ListPopupWindow mathematicsPopupWindow = (android.widget.ListPopupWindow) popup.get(mathematicsSpinner);
            android.widget.ListPopupWindow englishPopupWindow = (android.widget.ListPopupWindow) popup.get(englishSpinner);
            android.widget.ListPopupWindow qualificationTypePopupWindow = (android.widget.ListPopupWindow) popup.get(resultsSpinner);
            android.widget.ListPopupWindow subjectsListPopupWindow = (android.widget.ListPopupWindow) popup.get(subjectsSpinner);
            android.widget.ListPopupWindow gradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner);

            // Set popupWindow height to 500px
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
