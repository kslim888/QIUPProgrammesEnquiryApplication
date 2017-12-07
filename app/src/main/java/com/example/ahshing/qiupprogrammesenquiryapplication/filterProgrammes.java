package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;

public class filterProgrammes extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    MaterialSpinner resultsSpinner, subjectsSpinner, gradesSpinner, subjectsSpinner1, gradesSpinner1;
    ArrayAdapter<String> subjectsAdapter, gradesAdapter;
    String [] subjectsList, gradesList;
    Button filterButton;
    LinearLayout parentLinearLayout;
    TextView addNewField, deleteFieldText;
    boolean flagForNewField;

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
        resultsSpinner = findViewById(R.id.resultsSpinner);
        subjectsSpinner = findViewById(R.id.subjectsSpinner);
        gradesSpinner = findViewById(R.id.gradesSpinner);

        // setting the results type Adapter
        ArrayAdapter<CharSequence> resultsArrayAdapter = ArrayAdapter.createFromResource(this, R.array.result_type, R.layout.spinner_text);
        resultsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addNewField.setEnabled(false);
        addNewField.setTextColor(Color.GRAY);
        deleteFieldText.setEnabled(false);
        deleteFieldText.setTextColor(Color.GRAY);
        flagForNewField = false;
        subjectsSpinner.setEnabled(false);
        gradesSpinner.setEnabled(false);
        filterButton.setEnabled(false);
        resultsSpinner.setAdapter(resultsArrayAdapter);
        resultsSpinner.setOnItemSelectedListener(this);
        setSpinnerScrollbar(); //set spinner scrollbar for resultsSpinner, subjectsSpinner and gradesSpinner

        //add new dropdown field
        addNewField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView selection = (TextView)resultsSpinner.getSelectedView();
                String selectedItem = selection.getText().toString();
                if(Objects.equals(selectedItem, "SPM"))
                {
                    if(parentLinearLayout.getChildCount() > 13) // max 12
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "O-Level")) // max 10
                {
                    if(parentLinearLayout.getChildCount() > 11)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "A-Level")) // max 4
                {
                    if(parentLinearLayout.getChildCount() > 5)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STPM")) // max 5
                {
                    if(parentLinearLayout.getChildCount() > 6)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }
                else if(Objects.equals(selectedItem, "STAM")) // max 11
                {
                    if(parentLinearLayout.getChildCount() > 12)
                    {
                        addNewField.setEnabled(false);
                        addNewField.setTextColor(Color.GRAY);
                    }
                }

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.new_dropdown_field, null);
                // Add the new row before the add field button.
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
                if(parentLinearLayout.getChildCount() != 4) // if added new field, make the delete field text enable
                {
                    deleteFieldText.setEnabled(true);
                    deleteFieldText.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                }
                subjectsSpinner1 = rowView.findViewById(R.id.subjectsSpinner1);
                gradesSpinner1 = rowView.findViewById(R.id.gradesSpinner1);
                try {
                    loadSpinnerData(subjectsSpinner1, gradesSpinner1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteFieldText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(parentLinearLayout.getChildCount() != 4) // if added new field
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 2);
                }
                if(parentLinearLayout.getChildCount() == 4) // if deleted and back to original layout
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
                Intent resultsOfFiltering = new Intent(filterProgrammes.this, ResultsOfFiltering.class);
                startActivity(resultsOfFiltering);
            }
        });
    }

    private void loadSpinnerData(Spinner s, Spinner g) throws IOException
    {
        s.setAdapter(subjectsAdapter);
        g.setAdapter(gradesAdapter);
        flagForNewField = true; // to only set spinner scrollbar for newly added field
        setSpinnerScrollbar();
    }

    //results spinner listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
    {
        switch(position)
        {
            case 0: // SPM selected
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

            case 1: // STPM selected
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

            case 2: // UEC selected
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

            case 3: // O-Level selected
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

            case 4: // A-Level selected
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

            case 5: // STAM selected
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

        // after choose results type and set the adapters, make subjects and grades spinner enable
        // if both subject and grades spinner is disabled, make them both enable back
        if(position != -1)
        {
            filterButton.setEnabled(true);
            subjectsSpinner.setEnabled(true);
            gradesSpinner.setEnabled(true);
            addNewField.setEnabled(true);
            addNewField.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));

            if(parentLinearLayout.getChildCount() != 4 ) // if added new view
            {
                deleteFieldText.setEnabled(true);
                deleteFieldText.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                for(int i = parentLinearLayout.getChildCount(); i != 4; i--)
                {
                    parentLinearLayout.removeViewAt(parentLinearLayout.getChildCount() - 2);
                }
                try {
                    loadSpinnerData(subjectsSpinner1, gradesSpinner1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    public void setSpinnerScrollbar() {
        try
        {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            if(flagForNewField)
            {
                android.widget.ListPopupWindow newSubjectsListPopupWindow = (android.widget.ListPopupWindow) popup.get(subjectsSpinner1);
                android.widget.ListPopupWindow newGradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner1);
                newSubjectsListPopupWindow.setHeight(500);
                newGradesListPopupWindow.setHeight(500);
                return;
            }

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow resultsTypePopupWindow = (android.widget.ListPopupWindow) popup.get(resultsSpinner);
            android.widget.ListPopupWindow subjectsListPopupWindow = (android.widget.ListPopupWindow) popup.get(subjectsSpinner);
            android.widget.ListPopupWindow gradesListPopupWindow = (android.widget.ListPopupWindow) popup.get(gradesSpinner);

            // Set popupWindow height to 500px
            subjectsListPopupWindow.setHeight(500);
            gradesListPopupWindow.setHeight(500);
            resultsTypePopupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
