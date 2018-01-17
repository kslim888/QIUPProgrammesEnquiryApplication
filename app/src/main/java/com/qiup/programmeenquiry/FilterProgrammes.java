package com.qiup.programmeenquiry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qiup.programmeenquiry.qiupprogrammesenquiryapplication.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;

public class FilterProgrammes extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    MaterialSpinner qualificationSpinner, gradesSpinner, gradesSpinner1,
            spmOLevelSpinner, mathematicsSpinner, englishSpinner,
            addMathSpinner, bahasaMalaysiaSpinner, scienceTechnicalVocationalGradeSpinner;
    AutoCompleteTextView subjectsAutoCompleteTextView, newSubjectsAutoCompleteTextView,
            scienceTechnicalVocationalAutoCompleteTextView;
    ArrayAdapter<String> subjectsAdapter, gradesAdapter;
    Button filterButton;
    LinearLayout parentLinearLayout;
    TextView addNewField, deleteFieldText, subjectsText,
            gradesText, englishText, mathematicsText,
            spmOlevelText, addMathText, bahasaMalaysiaText;
    boolean flagForNewField;
    String [] subjectsList, scienceTechnicalVocationalSubjectArrays;

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
        qualificationSpinner = findViewById(R.id.qualificationSpinner);
        subjectsAutoCompleteTextView = findViewById(R.id.subjectsAutoCompleteTextView);
        newSubjectsAutoCompleteTextView = findViewById(R.id.newSubjectsAutoCompleteTextView);
        gradesSpinner = findViewById(R.id.gradesSpinner);
        spmOlevelText = findViewById(R.id.spmOlevelText);
        spmOLevelSpinner = findViewById(R.id.spmOLevelSpinner);
        mathematicsSpinner = findViewById(R.id.mathematicsSpinner);
        englishSpinner = findViewById(R.id.englishSpinner);
        addMathText = findViewById(R.id.addMathText);
        addMathSpinner = findViewById(R.id.addMathSpinner);
        bahasaMalaysiaSpinner = findViewById(R.id.bahasaMalaysiaSpinner);
        bahasaMalaysiaText = findViewById(R.id.bahasaMalaysiaText);
        scienceTechnicalVocationalAutoCompleteTextView = findViewById(R.id.scienceTechnicalVocationalAutoCompleteTextView);
        scienceTechnicalVocationalGradeSpinner = findViewById(R.id.scienceTechnicalVocationalGrade);

        // setting the results type Adapter
        String[] stringsResultsType = getResources().getStringArray(R.array.qualification_type);
        ArrayAdapter<String> resultsArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, stringsResultsType);
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
        subjectsAutoCompleteTextView.setEnabled(false);
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
        scienceTechnicalVocationalAutoCompleteTextView.setVisibility(View.GONE);
        scienceTechnicalVocationalGradeSpinner.setVisibility(View.GONE);
        spmOLevelSpinner.setAdapter(SPMOLevelArrayAdapter);
        spmOLevelSpinner.setOnItemSelectedListener(this);
        qualificationSpinner.setAdapter(resultsArrayAdapter);
        qualificationSpinner.setOnItemSelectedListener(this);
        setSpinnerScrollbar(); //set spinner scrollbar for resultsSpinner, subjectsSpinner and gradesSpinner

        scienceTechnicalVocationalAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(scienceTechnicalVocationalAutoCompleteTextView, InputMethodManager.SHOW_IMPLICIT);
                    scienceTechnicalVocationalAutoCompleteTextView.setHint("Leave blank if none");
                }
                else
                    scienceTechnicalVocationalAutoCompleteTextView.setHint("");
            }
        });

        scienceTechnicalVocationalAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(Objects.equals(scienceTechnicalVocationalAutoCompleteTextView.getText().toString().trim(), ""))
                {
                    scienceTechnicalVocationalGradeSpinner.setSelection(0);
                    scienceTechnicalVocationalGradeSpinner.setEnabled(false);
                }
                else
                {
                    scienceTechnicalVocationalGradeSpinner.setEnabled(true);
                }
            }
        });

        //add new dropdown field
        addNewField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_dropdown_field, null);
                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 9);
                if(parentLinearLayout.getChildCount() != 12) // if added new field, make the delete field text enable
                {
                    deleteFieldText.setEnabled(true);
                    deleteFieldText.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                newSubjectsAutoCompleteTextView = rowView.findViewById(R.id.newSubjectsAutoCompleteTextView);
                gradesSpinner1 = rowView.findViewById(R.id.gradesSpinner1);
                String selectedItem = qualificationSpinner.getSelectedItem().toString();
                if(Objects.equals(selectedItem, "SPM"))
                {
                    if(parentLinearLayout.getChildCount() == 23) // max 12
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "O-Level")) // max 10
                {
                    if(parentLinearLayout.getChildCount() == 21)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "A-Level")) // max 5
                {
                    if(parentLinearLayout.getChildCount() == 16)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STPM")) // max 5
                {
                    if(parentLinearLayout.getChildCount() == 16)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STAM")) // max 11
                {
                    if(parentLinearLayout.getChildCount() == 22)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "UEC"))
                {
                    if(parentLinearLayout.getChildCount() == 21)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }

                try {
                    loadSpinnerData(newSubjectsAutoCompleteTextView, gradesSpinner1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteFieldText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(parentLinearLayout.getChildCount() != 12) // if added new field
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 10);
                    addNewField.setEnabled(true);
                    addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(parentLinearLayout.getChildCount() == 12) // if deleted and back to original layout
                {
                    deleteFieldText.setEnabled(false);
                    deleteFieldText.setTextColor(Color.GRAY);
                    flagForNewField = false;
                }
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ArrayList<String> addedSubjectsList = new ArrayList<>();
                ArrayList<TextView> addedGradesList = new ArrayList<>();
                String spmOLevelString="", mathematicsGrade="", englishGrade="",
                        addMathGrade="", bahasaMalaysiaGrade="", scienceTechnicalVocationalGrade = "";

                // Validate Subjects First
                // first convert all the subjects into UpperCase for case-insensitive validation
                for(int i = 0; i  < subjectsList.length; i++)
                {
                    subjectsList[i] = subjectsList[i].toUpperCase();
                }
                // If the key-in subject is not exist in subjects list according to qualification, return
                if(!Arrays.asList(subjectsList).contains(subjectsAutoCompleteTextView.getText().toString().toUpperCase())
                        && !Objects.equals(subjectsAutoCompleteTextView.getText().toString(), ""))
                {
                    Toast.makeText(FilterProgrammes.this, "Please key-in valid subjects name", Toast.LENGTH_LONG).show();
                    return;
                }

                // Get all the new added view value and add to a List
                for (int i = 0; i < parentLinearLayout.getChildCount(); i++)
                {
                    view = parentLinearLayout.getChildAt(i);
                    if(view instanceof RelativeLayout)
                    {
                        if(view.getId() == R.id.newField)
                        {
                            newSubjectsAutoCompleteTextView = (AutoCompleteTextView) ((RelativeLayout) view).getChildAt(0);
                            // If the key-in subject is not exist in subjects list according to qualification, return
                            if(!Arrays.asList(subjectsList).contains(newSubjectsAutoCompleteTextView.getText().toString().toUpperCase())
                                    && !Objects.equals(newSubjectsAutoCompleteTextView.getText().toString(), ""))
                            {
                                Toast.makeText(FilterProgrammes.this, "Please key-in valid subjects name", Toast.LENGTH_LONG).show();
                                return;
                            }
                            gradesSpinner1 = (MaterialSpinner) ((RelativeLayout) view).getChildAt(1);
                            addedSubjectsList.add(newSubjectsAutoCompleteTextView.getText().toString());
                            addedGradesList.add((TextView)gradesSpinner1.getSelectedView());
                        }
                    }
                }

                // copy all subjects list to arrays
                String[] arrayStringSubjects = new String[addedSubjectsList.size() + 1];
                String selectedItem = subjectsAutoCompleteTextView.getText().toString();
                arrayStringSubjects[0] = selectedItem;
                for(int i = 1; i < arrayStringSubjects.length; i++)
                    arrayStringSubjects[i] = addedSubjectsList.get(i-1);

                // copy all grades list to arrays
                String[] arrayStringGrades = new String[addedGradesList.size() + 1];
                TextView selection = (TextView)gradesSpinner.getSelectedView();
                selectedItem = selection.getText().toString();
                arrayStringGrades[0] = selectedItem;
                for(int i = 1; i <  arrayStringSubjects.length; i++)
                    arrayStringGrades[i] = addedGradesList.get(i-1).getText().toString();

                // selectedItem Lastly is assigned as Qualification Level
                selectedItem = qualificationSpinner.getSelectedItem().toString();

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
                else if(Objects.equals(selectedItem, "UEC"))
                {
                    if(!Arrays.asList(arrayStringSubjects).contains("Bahasa Malaysia")
                            || !Arrays.asList(arrayStringSubjects).contains("Chinese")
                            || !Arrays.asList(arrayStringSubjects).contains("History")
                            || !Arrays.asList(arrayStringSubjects).contains("English Language"))
                    {
                        Toast.makeText(FilterProgrammes.this, "Subjects must include Bahasa Malaysia, English Language, Chinese and History", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                // Uf there are subjects is not key-in / selected
                for(int i = 0; i < arrayStringSubjects.length; i++)
                {
                    if(Objects.equals(arrayStringSubjects[i], ""))
                    {
                        Toast.makeText(FilterProgrammes.this, "There are subjects not selected", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                // If there are duplicated subjects, return
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

                //After validation, change the string value to proper subject and match with Rules
                for(int i = 0; i < arrayStringSubjects.length; i++)
                {
                    if(Objects.equals(arrayStringSubjects[i], "Bahasa Inggeris") || Objects.equals(arrayStringSubjects[i], "English Language"))
                    {
                        //English Language is for UEC and O-Level
                        arrayStringSubjects[i] = "English";
                    }
                    if(Objects.equals(arrayStringSubjects[i], "Advanced Mathematics")) // uec
                    {
                        arrayStringSubjects[i] = "Additional Mathematics";
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

                //spm or o-level
                if(spmOLevelSpinner.getSelectedView() != null)
                {
                    TextView spmOLevelText = (TextView)spmOLevelSpinner.getSelectedView();
                    spmOLevelString = spmOLevelText.getText().toString();
                }

                //bahasa malaysia
                if(bahasaMalaysiaSpinner.getSelectedView() != null)
                {
                    TextView bahasaMalaysiaText = (TextView)bahasaMalaysiaSpinner.getSelectedView();
                    bahasaMalaysiaGrade = bahasaMalaysiaText.getText().toString();
                }

                //maths
                if(mathematicsSpinner.getSelectedView() != null)
                {
                    TextView mathematicsText = (TextView)mathematicsSpinner.getSelectedView();
                    mathematicsGrade = mathematicsText.getText().toString();
                }

                //english
                if(englishSpinner.getSelectedView() != null)
                {
                    TextView englishText = (TextView)englishSpinner.getSelectedView();
                    englishGrade = englishText.getText().toString();
                }

                //add math
                if(addMathSpinner.getSelectedView() != null)
                {
                    TextView addMathText = (TextView)addMathSpinner.getSelectedView();
                    addMathGrade = addMathText.getText().toString();
                }

                //science technical vocational grade
                if(scienceTechnicalVocationalGradeSpinner.getSelectedView() != null)
                {
                    TextView scienceTechnicalVocationalGradeSelectedView = (TextView)scienceTechnicalVocationalGradeSpinner.getSelectedView();
                    scienceTechnicalVocationalGrade = scienceTechnicalVocationalGradeSelectedView.getText().toString();
                }

                //check second qualification grades error if the qualification is STPM, STAM or A-Level
                if(checkSecondQualificationError(spmOLevelString, mathematicsGrade, englishGrade,
                        addMathGrade, bahasaMalaysiaGrade, scienceTechnicalVocationalGrade, selectedItem))
                {
                    return; // if error is true, dont do anything
                }

                // send to next activity
                Bundle extras = new Bundle();
                extras.putString("QUALIFICATION_LEVEL", selectedItem);
                extras.putStringArray("STUDENT_SUBJECTS_LIST", arrayStringSubjects);
                extras.putStringArray("STUDENT_GRADES_LIST", arrayStringGrades);
                extras.putString("STUDENT_SECONDARY_QUALIFICATION", spmOLevelString);
                extras.putString("STUDENT_SECONDARY_MATH", mathematicsGrade);
                extras.putString("STUDENT_SECONDARY_ENG", englishGrade);
                extras.putString("STUDENT_SECONDARY_ADDMATH", addMathGrade);
                extras.putString("STUDENT_SECONDARY_BM", bahasaMalaysiaGrade);
                extras.putString("STUDENT_STV_SUBJECT", scienceTechnicalVocationalAutoCompleteTextView.getText().toString().trim());
                extras.putString("STUDENT_STV_GRADE", scienceTechnicalVocationalGrade);

                Intent nextActivity = new Intent(FilterProgrammes.this, InterestProgramme.class);
                nextActivity.putExtras(extras);
                startActivity(nextActivity);

                //TODO reset back to default
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

    private boolean checkSecondQualificationError(String spmOLevelQualification,
                                      String secondMath,
                                      String secondEng,
                                      String secondAddMath,
                                      String secondBahasaMalaysia,
                                      String scienceTechnicalVocationalGrade,
                                      String qualificationLevel)
    {
        if(Objects.equals(qualificationLevel, "STPM") || Objects.equals(qualificationLevel, "STAM") || Objects.equals(qualificationLevel, "A-Level"))
        {
            if(Objects.equals(spmOLevelQualification, ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose SPM or O-Level Qualification", Toast.LENGTH_LONG).show();
                return true;
            }

            if(Objects.equals(scienceTechnicalVocationalGrade, "")
                    && !Objects.equals(scienceTechnicalVocationalAutoCompleteTextView.getText().toString().trim(), ""))
            {
                Toast.makeText(FilterProgrammes.this, "Please choose " + spmOLevelQualification + " Science/Technical/Vocational grade", Toast.LENGTH_LONG).show();
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

    private void loadSpinnerData(AutoCompleteTextView subject, MaterialSpinner grade) throws IOException
    {
        subject.setAdapter(subjectsAdapter);
        grade.setAdapter(gradesAdapter);
        flagForNewField = true; // to only set spinner scrollbar for newly added field
        setSpinnerScrollbar();
    }

    //results and SPMOLevel spinner listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
    {
        String [] gradesList;

        switch(adapterView.getId())
        {
            case R.id.qualificationSpinner:
            {
                switch(position)
                {
                    case -1: // if select back to hint
                    {
                        filterButton.setEnabled(false);
                        subjectsAutoCompleteTextView.setEnabled(false);
                        gradesSpinner.setEnabled(false);
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                        subjectsText.setTextColor(Color.GRAY);
                        gradesText.setTextColor(Color.GRAY);
                    }
                    break;

                    case 0: // SPM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.spm_subjects);
                        gradesList = getResources().getStringArray(R.array.spm_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 1: // STPM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.stpm_subjects);
                        gradesList = getResources().getStringArray(R.array.stpm_grades);

                        // subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 2: // UEC selected
                    {
                        subjectsList = getResources().getStringArray(R.array.uec_subjects);
                        gradesList = getResources().getStringArray(R.array.uec_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 3: // O-Level selected
                    {
                        subjectsList = getResources().getStringArray(R.array.oLevel_subjects);
                        gradesList = getResources().getStringArray(R.array.oLevel_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 4: // A-Level selected
                    {
                        subjectsList = getResources().getStringArray(R.array.aLevel_subjects);
                        gradesList = getResources().getStringArray(R.array.aLevel_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;

                    case 5: // STAM selected
                    {
                        subjectsList = getResources().getStringArray(R.array.STAM_subjects);
                        gradesList = getResources().getStringArray(R.array.STAM_grades);

                        //subject list
                        subjectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectsList);

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
                        subjectsAutoCompleteTextView.setAdapter(subjectsAdapter);
                        gradesSpinner.setAdapter(gradesAdapter);
                    }
                    break;
                }
            }
            break;

            case R.id.spmOLevelSpinner:
            {
                ArrayAdapter<String> spmOLevelGradesAdapter, spmOLevelAddMathGradesAdapter, scienceTechnicalVocationalAdapter;
                String [] addMathGradeList;

                switch(position)
                {
                    case 1: // spm
                    {
                        gradesList = getResources().getStringArray(R.array.spm_grades);
                        addMathGradeList = getResources().getStringArray(R.array.add_math_spm_grades);
                        scienceTechnicalVocationalSubjectArrays = getResources().getStringArray(R.array.spm_science_technical_vocational_subject);

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
                        scienceTechnicalVocationalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, scienceTechnicalVocationalSubjectArrays);
                        scienceTechnicalVocationalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelAddMathGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        bahasaMalaysiaSpinner.setAdapter(spmOLevelGradesAdapter);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
                        scienceTechnicalVocationalGradeSpinner.setAdapter(spmOLevelGradesAdapter);
                        scienceTechnicalVocationalAutoCompleteTextView.setAdapter(scienceTechnicalVocationalAdapter);
                        addMathSpinner.setAdapter(spmOLevelAddMathGradesAdapter);
                    }
                    break;

                    case 2: // o-level
                    {
                        gradesList = getResources().getStringArray(R.array.oLevel_grades);
                        addMathGradeList = getResources().getStringArray(R.array.add_math_oLevel_grades);
                        scienceTechnicalVocationalSubjectArrays = getResources().getStringArray(R.array.oLevel_science_technical_vocational_subject);

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
                        scienceTechnicalVocationalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, scienceTechnicalVocationalSubjectArrays);
                        scienceTechnicalVocationalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spmOLevelAddMathGradesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        bahasaMalaysiaSpinner.setAdapter(spmOLevelGradesAdapter);
                        mathematicsSpinner.setAdapter(spmOLevelGradesAdapter);
                        englishSpinner.setAdapter(spmOLevelGradesAdapter);
                        scienceTechnicalVocationalGradeSpinner.setAdapter(spmOLevelGradesAdapter);
                        scienceTechnicalVocationalAutoCompleteTextView.setAdapter(scienceTechnicalVocationalAdapter);
                        addMathSpinner.setAdapter(spmOLevelAddMathGradesAdapter);
                    }
                    break;
                }
            }
            break;
        }

        if(adapterView.getId() == R.id.qualificationSpinner)
        {
            if(position == 1 || position == 4 || position == 5)
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
                scienceTechnicalVocationalAutoCompleteTextView.setVisibility(View.VISIBLE);
                scienceTechnicalVocationalGradeSpinner.setVisibility(View.VISIBLE);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
                addMathSpinner.setEnabled(false);
                bahasaMalaysiaSpinner.setEnabled(false);
                scienceTechnicalVocationalAutoCompleteTextView.setEnabled(false);
                scienceTechnicalVocationalGradeSpinner.setEnabled(false);
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
                scienceTechnicalVocationalAutoCompleteTextView.setVisibility(View.GONE);
                scienceTechnicalVocationalGradeSpinner.setVisibility(View.GONE);
                mathematicsSpinner.setEnabled(false);
                englishSpinner.setEnabled(false);
                addMathSpinner.setEnabled(false);
                bahasaMalaysiaSpinner.setEnabled(false);
                scienceTechnicalVocationalAutoCompleteTextView.setEnabled(false);
                scienceTechnicalVocationalGradeSpinner.setEnabled(false);
            }
        }
        else if(adapterView.getId() == R.id.spmOLevelSpinner)
        {
            if(position != 0)
            {
                scienceTechnicalVocationalAutoCompleteTextView.setEnabled(true);
                scienceTechnicalVocationalGradeSpinner.setEnabled(true);
                mathematicsSpinner.setEnabled(true);
                englishSpinner.setEnabled(true);
                addMathSpinner.setEnabled(true);
                bahasaMalaysiaSpinner.setEnabled(true);
                scienceTechnicalVocationalAutoCompleteTextView.setText("");
                scienceTechnicalVocationalAutoCompleteTextView.dismissDropDown();
            }
        }

        // after choose results type and set the adapters, make subjects and grades spinner enable
        // if both subject and grades spinner is disabled, make them both enable back
        if(adapterView.getId() == R.id.qualificationSpinner)
        {
            if(position != -1)
            {
                filterButton.setEnabled(true);
                subjectsAutoCompleteTextView.setEnabled(true);
                gradesSpinner.setEnabled(true);
                addNewField.setEnabled(true);
                addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                subjectsText.setTextColor(getResources().getColor(R.color.colorAccent));
                gradesText.setTextColor(getResources().getColor(R.color.colorAccent));
                subjectsAutoCompleteTextView.setText("");
                scienceTechnicalVocationalAutoCompleteTextView.setText("");
                subjectsAutoCompleteTextView.dismissDropDown();
                spmOLevelSpinner.setSelection(0);
                mathematicsSpinner.setSelection(0);
                englishSpinner.setSelection(0);
                addMathSpinner.setSelection(0);

                if(parentLinearLayout.getChildCount() != 11 ) // if added new view
                {
                    deleteFieldText.setEnabled(false);
                    deleteFieldText.setTextColor(Color.GRAY);
                    for(int i = parentLinearLayout.getChildCount(); i != 12; i--)  // if switch result, reset back to default
                    {
                        parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 10);
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
                android.widget.ListPopupWindow newGradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner1);
                newGradesListPopupWindow.setHeight(500);
                return;
            }

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow bahasaMalaysiaPopupWindow = (android.widget.ListPopupWindow) popup.get(bahasaMalaysiaSpinner);
            android.widget.ListPopupWindow addMathPopupWindow = (android.widget.ListPopupWindow) popup.get(addMathSpinner);
            android.widget.ListPopupWindow mathematicsPopupWindow = (android.widget.ListPopupWindow) popup.get(mathematicsSpinner);
            android.widget.ListPopupWindow englishPopupWindow = (android.widget.ListPopupWindow) popup.get(englishSpinner);
            android.widget.ListPopupWindow scienceTechnicalVocationaPopupWindow = (android.widget.ListPopupWindow) popup.get(scienceTechnicalVocationalGradeSpinner);
            android.widget.ListPopupWindow qualificationTypePopupWindow = (android.widget.ListPopupWindow) popup.get(qualificationSpinner);
            android.widget.ListPopupWindow gradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner);

            // Set popupWindow height to 500px
            bahasaMalaysiaPopupWindow.setHeight(500);
            addMathPopupWindow.setHeight(500);
            mathematicsPopupWindow.setHeight(500);
            englishPopupWindow.setHeight(500);
            gradesListPopupWindow.setHeight(500);
            qualificationTypePopupWindow.setHeight(500);
            scienceTechnicalVocationaPopupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
