package com.qiup.programmeenquiry;

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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qiup.programmeenquiry.qiupprogrammesenquiryapplication.R;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

import com.qiup.entryrules.BAC;
import com.qiup.entryrules.BBA;
import com.qiup.entryrules.BBA_HospitalityTourismManagement;
import com.qiup.entryrules.BBS;
import com.qiup.entryrules.BCE;
import com.qiup.entryrules.BCS;
import com.qiup.entryrules.BEM;
import com.qiup.entryrules.BFI;
import com.qiup.entryrules.BIS;
import com.qiup.entryrules.BIT;
import com.qiup.entryrules.BSNE;
import com.qiup.entryrules.BS_ActuarialSciences;
import com.qiup.entryrules.Biotech;
import com.qiup.entryrules.CorporateComm;
import com.qiup.entryrules.DAC;
import com.qiup.entryrules.DBM;
import com.qiup.entryrules.DCE;
import com.qiup.entryrules.DET;
import com.qiup.entryrules.DHM;
import com.qiup.entryrules.DIS;
import com.qiup.entryrules.DIT;
import com.qiup.entryrules.DME;
import com.qiup.entryrules.ElectronicsCommunicationsEngineering;
import com.qiup.entryrules.FIBFIA;
import com.qiup.entryrules.FIS;
import com.qiup.entryrules.MBBS;
import com.qiup.entryrules.MassCommAdvertising;
import com.qiup.entryrules.MassCommJournalism;
import com.qiup.entryrules.Pharmacy;
import com.qiup.entryrules.TESL;
import fr.ganfra.materialspinner.MaterialSpinner;

public class InterestedProgrammeAndEnglishProficiency extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView addInterestedProgramme, deleteInterestedProgramme;
    MaterialSpinner englishProficiencyTestSpinner,
            proficiencyLevelSpinner, interestedProgrammeSpinner, newInterestedProgrammeSpinner;
    Button generateButton;
    EditText editTOEFL_PBT, editTOEFL_IBT, editOtherProgramme;
    LinearLayout interestedAndEnglishParentLayout;
    ArrayAdapter<String> interestedProgrammesAdapter;
    Bundle extras;
    boolean flagForNewField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_programme_and_english_proficiency);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();
        addInterestedProgramme = findViewById(R.id.addInterestedProgrammeText);
        deleteInterestedProgramme = findViewById(R.id.deleteInterestedProgrammeText);
        englishProficiencyTestSpinner = findViewById(R.id.englishProficiencyTest);
        proficiencyLevelSpinner = findViewById(R.id.proficiencyLevel);
        interestedProgrammeSpinner = findViewById(R.id.interestedProgrammeSpinner);
        generateButton = findViewById(R.id.generateResultButton);
        interestedAndEnglishParentLayout = findViewById(R.id.interestedAndEnglishParentLayout);
        editTOEFL_PBT = findViewById(R.id.editTOEFL_PBT);
        editTOEFL_IBT = findViewById(R.id.editTOEFL_IBT);
        editOtherProgramme = findViewById(R.id.editOtherProgramme);

        // setting the english proficiency array adapter
        String[] stringsEnglishProficiencyTest = getResources().getStringArray(R.array.english_proficiency_test);
        ArrayAdapter<String> englishProficiencyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, stringsEnglishProficiencyTest)
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

        // setting the list of programmes proficiency array adapter
        String[] arrayOfProgrammes = getResources().getStringArray(R.array.programme_list); // FIXME list of programme with none
        interestedProgrammesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, arrayOfProgrammes)
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

        addInterestedProgramme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_interest_programme_field, null);

                // Add the new row before the add field button.
                interestedAndEnglishParentLayout.addView(rowView, interestedAndEnglishParentLayout.getChildCount() - 3);
                newInterestedProgrammeSpinner = rowView.findViewById(R.id.newInterestedProgrammeAutoComplete);
                if(interestedAndEnglishParentLayout.getChildCount() != 8) // if added new field, make the delete field text enable
                {
                    deleteInterestedProgramme.setEnabled(true);
                    deleteInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }

                if(interestedAndEnglishParentLayout.getChildCount() == 10) // max 3 interested programmes // if got Other = 10
                {
                    addInterestedProgramme.setEnabled(false);
                    addInterestedProgramme.setTextColor(Color.GRAY);
                }

                try {
                    loadSpinnerData(newInterestedProgrammeSpinner);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteInterestedProgramme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(interestedAndEnglishParentLayout.getChildCount() != 8) // if added new field
                {
                    interestedAndEnglishParentLayout.removeViewAt(interestedAndEnglishParentLayout.getChildCount() - 4);
                    addInterestedProgramme.setEnabled(true);
                    addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(interestedAndEnglishParentLayout.getChildCount() == 8) // if deleted and back to original layout // if Other 8
                {
                    deleteInterestedProgramme.setEnabled(false);
                    deleteInterestedProgramme.setTextColor(Color.GRAY);
                    flagForNewField = false;
                }
            }
        });

        editOtherProgramme.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String englishProficiencyTestName, englishProficiencyTestLevel = "", interestProgramme;
                ArrayList<TextView> interestedProgrammeList = new ArrayList<>();
                int proficiencyNumberTOEFL;
                TextView proficiencyLevel = (TextView)proficiencyLevelSpinner.getSelectedView();

                //validation
                // first position no need as englishProficiencyTestLevel initialized as ""
                if(englishProficiencyTestSpinner.getSelectedItemPosition() == 2
                        || englishProficiencyTestSpinner.getSelectedItemPosition() == 3) // if is MUET or IELTS
                {
                    if(proficiencyLevelSpinner.getSelectedItemPosition() == 0)
                    {
                        Toast.makeText(InterestedProgrammeAndEnglishProficiency.this, "Please select proficiency level", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                        englishProficiencyTestLevel = proficiencyLevel.getText().toString();
                    }
                }
                else if(englishProficiencyTestSpinner.getSelectedItemPosition() == 4) // if is TOEFL PBT
                {
                    //convert the string number into integer number
                    proficiencyNumberTOEFL = Integer.parseInt(editTOEFL_PBT.getText().toString());

                    if(editTOEFL_PBT.getText().toString().trim().isEmpty()) // if editTOEFL_PBT is empty
                    {
                        editTOEFL_PBT.setError("Please specify your level");
                        requestFocus(editTOEFL_PBT);
                        return;
                    }
                    else if(proficiencyNumberTOEFL < 310 || proficiencyNumberTOEFL > 677)
                    {
                        editTOEFL_PBT.setError("Please input valid level");
                        requestFocus(editTOEFL_PBT);
                        return;
                    }
                }
                else if(englishProficiencyTestSpinner.getSelectedItemPosition() == 5) // if is TOEFL IBT
                {
                    //convert the string number into integer number
                    proficiencyNumberTOEFL = Integer.parseInt(editTOEFL_IBT.getText().toString());

                    if (editTOEFL_IBT.getText().toString().trim().isEmpty()) // if editTOEFL_IBT is empty
                    {
                        editTOEFL_IBT.setError("Please specify your level");
                        requestFocus(editTOEFL_IBT);
                        return;
                    }
                    else if(proficiencyNumberTOEFL > 120)
                    {
                        editTOEFL_IBT.setError("Please input valid level");
                        requestFocus(editTOEFL_IBT);
                        return;
                    }
                }

                // if interested programme does not selected
                if(interestedProgrammeSpinner.getSelectedItemPosition() == 0)
                {
                    Toast.makeText(InterestedProgrammeAndEnglishProficiency.this, "Please select interest pragramme", Toast.LENGTH_LONG).show();
                    return;
                }

                // get all the new added interest programme view value and add to a List
                for (int i = 0; i < interestedAndEnglishParentLayout.getChildCount(); i++)
                {
                    view = interestedAndEnglishParentLayout.getChildAt(i);
                    if(view instanceof LinearLayout)
                    {
                        if(view.getId() == R.id.newProgrammes)
                        {
                            newInterestedProgrammeSpinner = (MaterialSpinner) ((LinearLayout) view).getChildAt(0);
                            interestedProgrammeList.add((TextView)newInterestedProgrammeSpinner.getSelectedView());
                        }
                    }
                }

                //set the english proficiency name
                proficiencyLevel = (TextView)englishProficiencyTestSpinner.getSelectedView();
                englishProficiencyTestName = proficiencyLevel.getText().toString();

                //set the first interested programme spinner
                proficiencyLevel = (TextView)interestedProgrammeSpinner.getSelectedView();
                interestProgramme = proficiencyLevel.getText().toString();

                //initialize the array and set first index as interest programme spinner
                String[] interestedProgrammeArray = new String[interestedProgrammeList.size() + 1];
                interestedProgrammeArray[0] = interestProgramme;

                //if added new field of interest programme
                if(interestedAndEnglishParentLayout.getChildCount() == 9) // only added 1
                {
                    interestedProgrammeArray[1] = interestedProgrammeList.get(0).getText().toString();
                }
                else if (interestedAndEnglishParentLayout.getChildCount() == 10) // added 2
                {
                    interestedProgrammeArray[1] = interestedProgrammeList.get(0).getText().toString();
                    interestedProgrammeArray[2] = interestedProgrammeList.get(1).getText().toString();
                }

                // if there are duplicated interest programme, return
                for(int i = 0; i < interestedProgrammeArray.length; i++)
                {
                    for(int j = i + 1; j < interestedProgrammeArray.length; j++)
                    {
                        if(Objects.equals(interestedProgrammeArray[i], interestedProgrammeArray[j]))
                        {
                            Toast.makeText(InterestedProgrammeAndEnglishProficiency.this, "There is a duplicate interest programme", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }

                // create facts
                Facts facts = new Facts();
                facts.put("Qualification Level",  extras.getString("QUALIFICATION_LEVEL"));
                facts.put("Student's Subjects", extras.getStringArray("STUDENT_SUBJECTS_LIST"));
                facts.put("Student's Grades",extras.getStringArray("STUDENT_GRADES_LIST"));
                facts.put("Student's SPM or O-Level", extras.getString("STUDENT_SECONDARY_QUALIFICATION"));
                facts.put("Student's Bahasa Malaysia", extras.getString("STUDENT_SECONDARY_BM"));
                facts.put("Student's Mathematics", extras.getString("STUDENT_SECONDARY_MATH"));
                facts.put("Student's English", extras.getString("STUDENT_SECONDARY_ENG"));
                facts.put("Student's Additional Mathematics", extras.getString("STUDENT_SECONDARY_ADDMATH"));
                facts.put("Student's English Proficiency Test Name", englishProficiencyTestName);

                //put fact of proficiency level
                if(Objects.equals(englishProficiencyTestName, "None"))
                {
                    facts.put("Student's English Proficiency Level", englishProficiencyTestLevel);
                }
                else if(Objects.equals(englishProficiencyTestName, "MUET") || Objects.equals(englishProficiencyTestName, "IELTS"))
                {
                    facts.put("Student's English Proficiency Level", englishProficiencyTestLevel);
                }
                else if(Objects.equals(englishProficiencyTestName, "TOEFL (Paper-Based Test)"))
                {
                    facts.put("Student's English Proficiency Level", editTOEFL_PBT.getText().toString());
                }
                else if(Objects.equals(englishProficiencyTestName, "TOEFL (Internet-Based Test)"))
                {
                    facts.put("Student's English Proficiency Level", editTOEFL_IBT.getText().toString());
                }

                // create and define rules
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

                // create a rules engine and fire rules on known facts
                RulesEngine rulesEngine = new DefaultRulesEngine();
                rulesEngine.fire(rules, facts);

                // put intent of string and array of english proficiency and interest programme
                // pass to next activity
                extras.putString("STUDENT_ENGLISH_PROFICIENCY_TEST_NAME",englishProficiencyTestName);
                extras.putString("STUDENT_ENGLISH_PROFICIENCY_LEVEL", englishProficiencyTestLevel);
                extras.putString("STUDENT_OTHER_INTERESTED_PROGRAMME", editOtherProgramme.getText().toString().trim());
                extras.putStringArray("STUDENT_INTERESTED_PROGRAMME_LIST", interestedProgrammeArray);

                Intent resultsOfFiltering = new Intent(InterestedProgrammeAndEnglishProficiency.this, ResultsOfFiltering.class);
                resultsOfFiltering.putExtras(extras);
                startActivity(resultsOfFiltering);

                //here reset back to default
            }
        });

        interestedProgrammesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        englishProficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        englishProficiencyTestSpinner.setAdapter(englishProficiencyAdapter);
        interestedProgrammeSpinner.setAdapter(interestedProgrammesAdapter);

        proficiencyLevelSpinner.setEnabled(false);
        interestedProgrammeSpinner.setEnabled(false);
        addInterestedProgramme.setEnabled(false);
        deleteInterestedProgramme.setEnabled(false);
        generateButton.setEnabled(false);
        editOtherProgramme.setEnabled(false);
        flagForNewField = false;

        englishProficiencyTestSpinner.setOnItemSelectedListener(this);
        setSpinnerScrollbar();
    }

    private void loadSpinnerData(MaterialSpinner s) throws IOException
    {
        s.setAdapter(interestedProgrammesAdapter);
        flagForNewField = true; // to only set spinner scrollbar for newly added field
        setSpinnerScrollbar();
    }

    //request focus on android
    private void requestFocus(View view)
    {
        if (view.requestFocus())
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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

    //set the spinners scrollbar and height
    public void setSpinnerScrollbar() {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            if(flagForNewField) {
                android.widget.ListPopupWindow newInterestedProgrammeWindow = (android.widget.ListPopupWindow) popup.get(newInterestedProgrammeSpinner);
                newInterestedProgrammeWindow.setHeight(500);
                return;
            }

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow englishProficiencyTestWindow = (android.widget.ListPopupWindow) popup.get(englishProficiencyTestSpinner);
            android.widget.ListPopupWindow proficiencyLevelWindow = (android.widget.ListPopupWindow) popup.get(proficiencyLevelSpinner);
            android.widget.ListPopupWindow interestedProgrammeSpinnerWindow = (android.widget.ListPopupWindow) popup.get(interestedProgrammeSpinner);

            // Set popupWindow height to 500px
            englishProficiencyTestWindow.setHeight(500);
            proficiencyLevelWindow.setHeight(500);
            interestedProgrammeSpinnerWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
    {
        String [] arrayProficiencyLevel;
        ArrayAdapter<String> proficiencyAdapter;
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        switch(position)
        {
            case 1: // none chosen
            {
                //set back proficiency level selection, make it disable and reappear again
                proficiencyLevelSpinner.setSelection(0);
                proficiencyLevelSpinner.setEnabled(false);
                proficiencyLevelSpinner.setVisibility(View.VISIBLE);

                // make TOELF edit text hidden
                editTOEFL_PBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
                editTOEFL_IBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);
            }
            break;
            case 2: // muet chosen
            {
                // appear back the proficiency spinner if is hidden before
                proficiencyLevelSpinner.setVisibility(View.VISIBLE);

                // make TOELF edit text hidden
                editTOEFL_PBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
                editTOEFL_IBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);

                arrayProficiencyLevel =  getResources().getStringArray(R.array.muet_band); // get muet string array
                proficiencyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, arrayProficiencyLevel)
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

                proficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                proficiencyLevelSpinner.setAdapter(proficiencyAdapter);
            }
            break;
            case 3: // IELTS chosen
            {
                // appear back the proficiency spinner if is hidden before
                proficiencyLevelSpinner.setVisibility(View.VISIBLE);

                // make TOELF edit text hidden
                editTOEFL_PBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
                editTOEFL_IBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);

                arrayProficiencyLevel =  getResources().getStringArray(R.array.IELTS_overall_band_score); // get IELTS string array
                proficiencyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, arrayProficiencyLevel)
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

                proficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                proficiencyLevelSpinner.setAdapter(proficiencyAdapter);
            }
            break;
            case 4: // TOELF PBT chosen
            {
                editTOEFL_IBT.setVisibility(View.GONE); // hide the TOELF IBT edit text
                proficiencyLevelSpinner.setVisibility(View.GONE); // hide the proficiency spinner
                editTOEFL_PBT.setVisibility(View.VISIBLE);
                // close the keyboard
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
            }
            break;
            case 5: // TOELF IBT chosen
            {
                editTOEFL_PBT.setVisibility(View.GONE); // hide the TOELF PBT edit text
                proficiencyLevelSpinner.setVisibility(View.GONE); // hide the proficiency spinner
                editTOEFL_IBT.setVisibility(View.VISIBLE);
                // close the keyboard
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);
            }
            break;
        }

        if(position == 1)
        {
            generateButton.setEnabled(true);
            interestedProgrammeSpinner.setEnabled(true);
            editOtherProgramme.setEnabled(true);
            addInterestedProgramme.setEnabled(true);
            addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
        else if(position != 0) // if position is not 0, 2, 3, 4, 5
        {
            generateButton.setEnabled(true);
            interestedProgrammeSpinner.setEnabled(true);
            editOtherProgramme.setEnabled(true);
            addInterestedProgramme.setEnabled(true);
            proficiencyLevelSpinner.setEnabled(true);
            addInterestedProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
