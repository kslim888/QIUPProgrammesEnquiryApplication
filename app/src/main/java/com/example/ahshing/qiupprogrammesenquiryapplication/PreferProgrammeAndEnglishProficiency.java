package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;

import fr.ganfra.materialspinner.MaterialSpinner;

public class PreferProgrammeAndEnglishProficiency extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView addPreferProgramme, deletePreferProgramme;
    MaterialSpinner englishProficiencyTestSpinner, proficiencyLevelSpinner, preferProgrammeSpinner, newPreferProgrammeSpinner;
    Button generateButton;
    EditText editTOEFL_PBT, editTOEFL_IBT;
    LinearLayout preferAndEnglishParentLayout;
    ArrayAdapter<String> preferProgrammesAdapter;
    boolean flagForNewField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefer_programme_and_english_proficiency);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addPreferProgramme = findViewById(R.id.addPreferProgrammeText);
        deletePreferProgramme = findViewById(R.id.deletePreferProgrammeText);
        englishProficiencyTestSpinner = findViewById(R.id.englishProficiencyTest);
        proficiencyLevelSpinner = findViewById(R.id.proficiencyLevel);
        preferProgrammeSpinner = findViewById(R.id.preferProgrammeSpinner);
        generateButton = findViewById(R.id.generateResultButton);
        preferAndEnglishParentLayout = findViewById(R.id.preferAndEnglishParentLayout);
        editTOEFL_PBT = findViewById(R.id.editTOEFL_PBT);
        editTOEFL_IBT = findViewById(R.id.editTOEFL_IBT);

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
        String[] arrayOfProgrammes = getResources().getStringArray(R.array.list_of_programmes);
        preferProgrammesAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, arrayOfProgrammes)
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

        addPreferProgramme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_prefer_programme_field, null);

                // Add the new row before the add field button.
                preferAndEnglishParentLayout.addView(rowView, preferAndEnglishParentLayout.getChildCount() - 2);
                newPreferProgrammeSpinner = rowView.findViewById(R.id.newPreferProgrammeSpinner);

                if(preferAndEnglishParentLayout.getChildCount() != 7) // if added new field, make the delete field text enable
                {
                    deletePreferProgramme.setEnabled(true);
                    deletePreferProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }

                if(preferAndEnglishParentLayout.getChildCount() == 9) // max 3 prefer programmes
                {
                    addPreferProgramme.setEnabled(false);
                    addPreferProgramme.setTextColor(Color.GRAY);
                }

                try {
                    loadSpinnerData(newPreferProgrammeSpinner);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        deletePreferProgramme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(preferAndEnglishParentLayout.getChildCount() != 7) // if added new field
                {
                    preferAndEnglishParentLayout.removeViewAt(preferAndEnglishParentLayout.getChildCount() - 3);
                    addPreferProgramme.setEnabled(true);
                    addPreferProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                if(preferAndEnglishParentLayout.getChildCount() == 7) // if deleted and back to original layout
                {
                    deletePreferProgramme.setEnabled(false);
                    deletePreferProgramme.setTextColor(Color.GRAY);
                }
            }
        });

        preferProgrammesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        englishProficiencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        englishProficiencyTestSpinner.setAdapter(englishProficiencyAdapter);
        preferProgrammeSpinner.setAdapter(preferProgrammesAdapter);

        proficiencyLevelSpinner.setEnabled(false);
        preferProgrammeSpinner.setEnabled(false);
        addPreferProgramme.setEnabled(false);
        deletePreferProgramme.setEnabled(false);
        generateButton.setEnabled(false);
        flagForNewField = false;

        englishProficiencyTestSpinner.setOnItemSelectedListener(this);
        //preferProgrammeSpinner.setOnItemSelectedListener(this); // position 34 other
        //newPreferProgrammeSpinner.setOnItemSelectedListener(this);
        setSpinnerScrollbar();
    }

    private void loadSpinnerData(MaterialSpinner s) throws IOException
    {
//        s.setAdapter(preferProgrammesAdapter);
        //flagForNewField = true; // to only set spinner scrollbar for newly added field
        setSpinnerScrollbar();
    }

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

    public void setSpinnerScrollbar() {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            if(flagForNewField) {
                android.widget.ListPopupWindow newPreferProgrammeWindow = (android.widget.ListPopupWindow) popup.get(newPreferProgrammeSpinner);
                newPreferProgrammeWindow.setHeight(500);
                return;
            }

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow englishProficiencyTestWindow = (android.widget.ListPopupWindow) popup.get(englishProficiencyTestSpinner);
            android.widget.ListPopupWindow proficiencyLevelWindow = (android.widget.ListPopupWindow) popup.get(proficiencyLevelSpinner);
            android.widget.ListPopupWindow preferProgrammeSpinnerWindow = (android.widget.ListPopupWindow) popup.get(preferProgrammeSpinner);

            // Set popupWindow height to 500px
            englishProficiencyTestWindow.setHeight(500);
            proficiencyLevelWindow.setHeight(500);
            preferProgrammeSpinnerWindow.setHeight(500);
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
            case 1: // muet chosen
            {
                // appear back the proficiency spinner if is hidden before
                proficiencyLevelSpinner.setVisibility(View.VISIBLE);

                // make TOELF edit text hidden
                editTOEFL_PBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
                editTOEFL_IBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);

                arrayProficiencyLevel =  getResources().getStringArray(R.array.muet_band);
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
            case 2: // IELTS chosen
            {
                // appear back the proficiency spinner if is hidden before
                proficiencyLevelSpinner.setVisibility(View.VISIBLE);

                // make TOELF edit text hidden
                editTOEFL_PBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_PBT.getWindowToken(), 0);
                editTOEFL_IBT.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(editTOEFL_IBT.getWindowToken(), 0);

                arrayProficiencyLevel =  getResources().getStringArray(R.array.IELTS_overall_band_score);
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
            case 3: // TOELF PBT chosen
            {
                editTOEFL_IBT.setVisibility(View.GONE); // hide the TOELF IBT edit text
                proficiencyLevelSpinner.setVisibility(View.GONE); // hide the proficiency spinner
                editTOEFL_PBT.setVisibility(View.VISIBLE);
            }
            break;
            case 4: // TOELF IBT chosen
            {
                editTOEFL_PBT.setVisibility(View.GONE); // hide the TOELF PBT edit text
                proficiencyLevelSpinner.setVisibility(View.GONE); // hide the proficiency spinner
                editTOEFL_IBT.setVisibility(View.VISIBLE);
            }
            break;
        }

        if(position != 0)
        {
            addPreferProgramme.setEnabled(true);
            generateButton.setEnabled(true);
            proficiencyLevelSpinner.setEnabled(true);
            preferProgrammeSpinner.setEnabled(true);
            addPreferProgramme.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
