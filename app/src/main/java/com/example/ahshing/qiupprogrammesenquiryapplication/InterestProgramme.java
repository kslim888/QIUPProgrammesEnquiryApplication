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
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.io.IOException;
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

public class InterestProgramme extends AppCompatActivity
{
    TextView addInterestedProgramme, deleteInterestedProgramme, maxTextView;
    AutoCompleteTextView interestedProgrammeAutoComplete, newInterestedProgrammeAutoComplete;
    Button generateButton;
    EditText editOtherProgramme;
    CheckBox interestCheckBox;
    LinearLayout interestProgrammeParentLayout;
    ArrayAdapter<String> programmeListAdapter;
    Bundle extras;
    boolean flagForNewField;
    String[] arrayOfProgramme;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_programme);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();
        addInterestedProgramme = findViewById(R.id.addInterestedProgrammeText);
        deleteInterestedProgramme = findViewById(R.id.deleteInterestedProgrammeText);
        maxTextView = findViewById(R.id.maxTextView);
        interestedProgrammeAutoComplete = findViewById(R.id.interestedProgrammeAutoComplete);
        generateButton = findViewById(R.id.generateResultButton);
        interestProgrammeParentLayout = findViewById(R.id.interestProgrammeParentLayout);
        editOtherProgramme = findViewById(R.id.editOtherProgramme);
        interestCheckBox = findViewById(R.id.interestCheckBox);

        // setting the list of programmes array adapter
        arrayOfProgramme = getResources().getStringArray(R.array.programme_list);
        programmeListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayOfProgramme);

        interestCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked)
                {
                    addInterestedProgramme.setEnabled(false);
                    deleteInterestedProgramme.setEnabled(false);
                    editOtherProgramme.setEnabled(false);
                    interestedProgrammeAutoComplete.setEnabled(false);
                    interestedProgrammeAutoComplete.setText("");
                    interestedProgrammeAutoComplete.dismissDropDown();

                    editOtherProgramme.setText("");
                    // Reset the layout to default
                    for(int i = interestProgrammeParentLayout.getChildCount(); i != 6; i--)
                    {
                        interestProgrammeParentLayout.removeViewAt(interestProgrammeParentLayout.getChildCount() - 4);
                    }

                    maxTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    deleteInterestedProgramme.setTextColor(getResources().getColor(android.R.color.darker_gray));
                }
                else
                {
                    maxTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    addInterestedProgramme.setEnabled(true);
                    addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    deleteInterestedProgramme.setEnabled(false);
                    deleteInterestedProgramme.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    interestedProgrammeAutoComplete.setEnabled(true);
                    editOtherProgramme.setEnabled(true);
                }
            }
        });

        addInterestedProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_interest_programme_field, null);

                // Add the new row before the add field button.
                interestProgrammeParentLayout.addView(rowView, interestProgrammeParentLayout.getChildCount() - 3);
                newInterestedProgrammeAutoComplete = rowView.findViewById(R.id.newInterestedProgrammeAutoComplete);
                if(interestProgrammeParentLayout.getChildCount() != 6) // if added new field, make the delete field text enable
                {
                    deleteInterestedProgramme.setEnabled(true);
                    deleteInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(interestProgrammeParentLayout.getChildCount() == 8) // max 3 interested programmes // if got Other = 10
                {
                    addInterestedProgramme.setEnabled(false);
                    addInterestedProgramme.setTextColor(Color.GRAY);
                }

                try {
                    loadSpinnerData(newInterestedProgrammeAutoComplete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteInterestedProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(interestProgrammeParentLayout.getChildCount() != 6) // if added new field
                {
                    interestProgrammeParentLayout.removeViewAt(interestProgrammeParentLayout.getChildCount() - 4);
                    addInterestedProgramme.setEnabled(true);
                    addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(interestProgrammeParentLayout.getChildCount() == 6) // if deleted and back to original layout // if Other 8
                {
                    deleteInterestedProgramme.setEnabled(false);
                    deleteInterestedProgramme.setTextColor(Color.GRAY);
                    flagForNewField = false;
                }
            }
        });

        editOtherProgramme.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus)
                {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editOtherProgramme, InputMethodManager.SHOW_IMPLICIT);
                    editOtherProgramme.setHint("Leave blank if none");
                }
                else
                    editOtherProgramme.setHint("");
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Start validate inputted programme. If the key-in programme is not exist in programme list, return
                // If checkbox is not checked
                if(!interestCheckBox.isChecked())
                {
                    // Copy all the array and make all Upper case for case insensitive
                    String [] foundationArray = new String[3], diplomaArray = new String[8], degreeArray = new String[21];
                    for(int i = 0; i  < arrayOfProgramme.length; i++)
                    {
                        arrayOfProgramme[i] = arrayOfProgramme[i].toUpperCase();
                    }
                    for(int i = 0; i < 3; i++)
                    {
                        foundationArray[i] = arrayOfProgramme[i];
                    }
                    for(int i = 0; i < 8; i++)
                    {
                        diplomaArray[i] = arrayOfProgramme[i+3];
                    }
                    for(int i = 0; i < 21; i++)
                    {
                        degreeArray[i] = arrayOfProgramme[i+11];
                    }

                    // Check interested programme is blank or not. If blank then return
                    if(Objects.equals(interestedProgrammeAutoComplete.getText().toString().trim(), ""))
                    {
                        Toast.makeText(InterestProgramme.this, "Please key-in interest pragramme", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Based on qualification, check programmes valid or not
                    // UEC can from foundation to degree
                    // If is SPM, O-Level only foundation to diploma
                    if(Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "SPM")
                            || Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "O-Level"))
                    {
                        if(Arrays.asList(degreeArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                        {
                            Toast.makeText(InterestProgramme.this, "SPM / O-Level can only enquiry until Diploma level", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(!Arrays.asList(foundationArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim())
                                && !Arrays.asList(diplomaArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                        {
                            Toast.makeText(InterestProgramme.this, "Please key-in valid interest pragramme", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // If is STPM, A-Level, STAM only diploma to degree
                    if(!Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "SPM")
                            && !Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "O-Level")
                            && !Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "UEC"))
                    {
                        if(Arrays.asList(foundationArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                        {
                            Toast.makeText(InterestProgramme.this, "Above SPM / O-Level qualification cannot enquiry Foundation level", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(!Arrays.asList(diplomaArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim())
                                && !Arrays.asList(degreeArray).contains(interestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                        {
                            Toast.makeText(InterestProgramme.this, "Please key-in valid interest pragramme", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // For new added interest programme, do the same checking
                    for (int i = 0; i < interestProgrammeParentLayout.getChildCount(); i++)
                    {
                        view = interestProgrammeParentLayout.getChildAt(i);
                        if(view instanceof LinearLayout)
                        {
                            if(view.getId() == R.id.newProgrammes)
                            {
                                newInterestedProgrammeAutoComplete = (AutoCompleteTextView) ((LinearLayout) view).getChildAt(0);

                                if(Objects.equals(newInterestedProgrammeAutoComplete.getText().toString().trim(), ""))
                                {
                                    Toast.makeText(InterestProgramme.this, "Please key-in interest pragramme", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                if(Objects.equals(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim(), "NONE"))
                                {
                                    Toast.makeText(InterestProgramme.this, "None is not allowed in added interest programme", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                if(Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "SPM")
                                        || Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "O-Level"))
                                {
                                    if(Arrays.asList(degreeArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                                    {
                                        Toast.makeText(InterestProgramme.this, "SPM / O-Level can only enquiry until Diploma level", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    if(!Arrays.asList(foundationArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim())
                                            && !Arrays.asList(diplomaArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                                    {
                                        Toast.makeText(InterestProgramme.this, "Please key-in valid interest pragramme", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }

                                if(!Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "SPM")
                                        && !Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "O-Level")
                                        && !Objects.equals(extras.getString("QUALIFICATION_LEVEL"), "UEC"))
                                {
                                    if(Arrays.asList(foundationArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase()))
                                    {
                                        Toast.makeText(InterestProgramme.this, "Above SPM / O-Level qualification cannot enquiry Foundation level", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    if(!Arrays.asList(diplomaArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim())
                                            && !Arrays.asList(degreeArray).contains(newInterestedProgrammeAutoComplete.getText().toString().toUpperCase().trim()))
                                    {
                                        Toast.makeText(InterestProgramme.this, "Above SPM / O-Level qualification cannot enquiry Foundation level", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    // Get all the new added interest programme view value and add to a List
                    ArrayList<String> interestedProgrammeList = new ArrayList<>();
                    for (int i = 0; i < interestProgrammeParentLayout.getChildCount(); i++)
                    {
                        view = interestProgrammeParentLayout.getChildAt(i);
                        if(view instanceof LinearLayout)
                        {
                            if(view.getId() == R.id.newProgrammes)
                            {
                                newInterestedProgrammeAutoComplete = (AutoCompleteTextView) ((LinearLayout) view).getChildAt(0);
                                interestedProgrammeList.add(newInterestedProgrammeAutoComplete.getText().toString());
                            }
                        }
                    }

                    String interestProgramme = interestedProgrammeAutoComplete.getText().toString();

                    //initialize the array and set first index as interest programme spinner
                    String[] interestedProgrammeArray = new String[interestedProgrammeList.size() + 1];
                    interestedProgrammeArray[0] = interestProgramme;

                    //if added new field of interest programme
                    if(interestProgrammeParentLayout.getChildCount() == 7) // only added 1
                    {
                        interestedProgrammeArray[1] = interestedProgrammeList.get(0);
                    }
                    else if (interestProgrammeParentLayout.getChildCount() == 8) // added 2
                    {
                        interestedProgrammeArray[1] = interestedProgrammeList.get(0);
                        interestedProgrammeArray[2] = interestedProgrammeList.get(1);
                    }

                    // Check if there are duplicated interest programme. if got then return
                    for(int i = 0; i < interestedProgrammeArray.length; i++)
                    {
                        for(int j = i + 1; j < interestedProgrammeArray.length; j++)
                        {
                            if(Objects.equals(interestedProgrammeArray[i], interestedProgrammeArray[j]))
                            {
                                Toast.makeText(InterestProgramme.this, "There is a duplicate interest programme", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    // put intent of string interest programme then pass to next activity
                    extras.putStringArray("STUDENT_INTERESTED_PROGRAMME_LIST", interestedProgrammeArray);
                    extras.putBoolean("STUDENT_IS_GOT_INTERESTED_PROGRAMME", true);
                }
                else // no interest programme, pass false for key STUDENT_IS_GOT_INTERESTED_PROGRAMME
                {
                    extras.putBoolean("STUDENT_IS_GOT_INTERESTED_PROGRAMME", false);
                }

                // Create facts
                Facts facts = new Facts();
                facts.put("Qualification Level",  extras.getString("QUALIFICATION_LEVEL"));
                facts.put("Student's Subjects", extras.getStringArray("STUDENT_SUBJECTS_LIST"));
                facts.put("Student's Grades",extras.getStringArray("STUDENT_GRADES_LIST"));
                facts.put("Student's SPM or O-Level", extras.getString("STUDENT_SECONDARY_QUALIFICATION"));
                facts.put("Student's Bahasa Malaysia", extras.getString("STUDENT_SECONDARY_BM"));
                facts.put("Student's Mathematics", extras.getString("STUDENT_SECONDARY_MATH"));
                facts.put("Student's English", extras.getString("STUDENT_SECONDARY_ENG"));
                facts.put("Student's Additional Mathematics", extras.getString("STUDENT_SECONDARY_ADDMATH"));
                facts.put("Student's Science/Technical/Vocational Subject", extras.getString("STUDENT_STV_SUBJECT"));
                facts.put("Student's Science/Technical/Vocational Grade", extras.getString("STUDENT_STV_GRADE"));

                // Create and define rules
                Rules rules = new Rules(
                        new FIS(),
                        new FIBFIA(),
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
                        new BET(),
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

                // Create a rules engine and fire rules on known facts
                RulesEngine rulesEngine = new DefaultRulesEngine();
                rulesEngine.fire(rules, facts);

               Intent resultsOfFiltering = new Intent(InterestProgramme.this, ResultsOfFiltering.class);
                resultsOfFiltering.putExtras(extras);
                startActivity(resultsOfFiltering);

                //TODO reset back to default
                //TODO all data send to DB
                //editOtherProgramme.getText().toString().trim();
            }
        });

        programmeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedProgrammeAutoComplete.setAdapter(programmeListAdapter);
        interestedProgrammeAutoComplete.setEnabled(false);
        interestedProgrammeAutoComplete.setText("");
        editOtherProgramme.setEnabled(false);
        editOtherProgramme.setText("");
        addInterestedProgramme.setEnabled(false);
        deleteInterestedProgramme.setEnabled(false);
    }

    private void loadSpinnerData(AutoCompleteTextView programme) throws IOException
    {
        programme.setAdapter(programmeListAdapter);
    }

    // for back arrow button
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
