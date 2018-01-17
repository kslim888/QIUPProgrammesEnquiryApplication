package com.example.ahshing.qiupprogrammesenquiryapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CaptureData extends AppCompatActivity
{
    private EditText editName,editIC,editContactNumber,editEmail,editRemark, editSchool;
    private TextInputLayout inputLayoutName, inputLayoutIC, inputLayoutContactNumber, inputLayoutEmail, inputLayoutSchool;
    boolean valid; // for form validation

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_data);

        //if there is no internet connection on device
        if(!isOnline())
        {
            AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("No Internet connection");
            builder.setMessage("You need Internet connection in order to proceed");

            builder.setPositiveButton("Open Settings", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(settingsIntent);
                }
            });
            builder.setNegativeButton("Return", null);

            final AlertDialog alert = builder.create();
            alert.show();
        }

        findResourceID();

        editRemark.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus)
                    editRemark.setHint("Leave blank if none");
                else
                    editRemark.setHint("");
            }
        });

        addTextListener();
    }

    public void addTextListener()
    {
        editName.addTextChangedListener(new CustomTextWatcher(editName));
        editIC.addTextChangedListener(new CustomTextWatcher(editIC));
        editContactNumber.addTextChangedListener(new CustomTextWatcher(editContactNumber));
        editEmail.addTextChangedListener(new CustomTextWatcher(editEmail));
        editRemark.addTextChangedListener(new CustomTextWatcher(editRemark));
    }

    private boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected() && netInfo.isAvailable())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void findResourceID()
    {
        editName = findViewById(R.id.editName);
        editIC = findViewById(R.id.editIC);
        editContactNumber = findViewById(R.id.editContactNumber);
        editEmail = findViewById(R.id.editEmail);
        editRemark = findViewById(R.id.editRemark);
        editSchool = findViewById(R.id.editSchool);

        inputLayoutName = findViewById(R.id.inputLayoutName);
        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
        inputLayoutContactNumber = findViewById(R.id.inputLayoutContactNumber);
        inputLayoutIC = findViewById(R.id.inputLayoutIC);
        inputLayoutSchool = findViewById(R.id.inputLayoutSchool);
    }

    //validate name
    private void validateName()
    {
        String editNameInput = editName.getText().toString();

        if (editName.getText().toString().trim().isEmpty() || !editNameInput.matches("[a-zA-Z][a-zA-Z ]*"))
        {
            editName.setError(getString(R.string.err_msg_name));
            requestFocus(editName);
            valid = false;
        }
        else
        {
            inputLayoutName.setErrorEnabled(false);
            valid = true;
        }
    }

    //validate IC
    private void validateIC()
    {
        if (editIC.getText().toString().trim().isEmpty() || editIC.getText().length() != 14)
        {
            editIC.setError(getString(R.string.err_IC));
            requestFocus(editIC);
            valid = false;
        }
        else
        {
            inputLayoutIC.setErrorEnabled(false);
            valid = true;
        }
    }

    //validate contact number
    private void validateContactNumber()
    {
        if (editContactNumber.getText().toString().trim().isEmpty()
                || editContactNumber.getText().length() > 11
                || editContactNumber.getText().length() < 10)
        {
            editContactNumber.setError(getString(R.string.err_contact_number));
            requestFocus(editContactNumber);
            valid = false;
        }
        else
        {
            inputLayoutContactNumber.setErrorEnabled(false);
            valid = true;
        }
    }

    //validate email
    private void validateEmail()
    {
        String email = editEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email))
        {
            editEmail.setError(getString(R.string.err_msg_email));
            requestFocus(editEmail);
            valid = false;
        }
        else
        {
            inputLayoutEmail.setErrorEnabled(false);
            valid = true;
        }
    }

    //to check whether email is valid or not
    private static boolean isValidEmail(String email)
    {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void validateSchoolName()
    {
        if (editSchool.getText().toString().trim().isEmpty() || editSchool.getText().length() < 12)
        {
            editSchool.setError(getString(R.string.err_school_name));
            requestFocus(editSchool);
            valid = false;
        }
        else
        {
            inputLayoutSchool.setErrorEnabled(false);
            valid = true;
        }
    }

    //request focus on android
    private void requestFocus(View view)
    {
        if (view.requestFocus())
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean getValid()
    {
        return valid;
    }

    //button submit post to google spreadsheets
    public void submitData(View view)
    {
        validateName();
        if (!getValid())
            return;

        validateEmail() ;
        if (!getValid())
            return;

        validateContactNumber();
        if (!getValid())
            return;

        validateIC();
        if (!getValid())
            return;

        validateSchoolName();
        if (!getValid())
            return;

        //get the input text
        String nameInput = editName.getText().toString();
        String icInput = editIC.getText().toString();
        String contactNumber = editContactNumber.getText().toString();
        String contactNumberInput = contactNumber.replaceAll("[\\s\\-()]", " ");
        String emailInput = editEmail.getText().toString();
        String remarkInput = editRemark.getText().toString();

        // use Retrofit library to make post
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        final SpreadsheetsAPI spreadsheetWebService = retrofit.create(SpreadsheetsAPI.class);
        Call<Void> postToSpreadsheetsCall = spreadsheetWebService.postToSpreadsheets(nameInput, icInput, contactNumberInput, emailInput, remarkInput);
        postToSpreadsheetsCall.enqueue(new Callback<Void>()
        {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_LONG).show();
                //clear text field upon successful submit
                editName.setText("");
                editIC.setText("");
                editContactNumber.setText("");
                editEmail.setText("");
                editRemark.setText("");
                editSchool.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Unable to submit. No Internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class CustomTextWatcher implements TextWatcher
    {
        private boolean isFormatting, deletingHyphen, deletingBackward;
        private int hyphenStart;
        private View view;

        CustomTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            if (isFormatting)
                return;

            // Make sure user is deleting one char, without a selection
            final int selStart = Selection.getSelectionStart(s);
            final int selEnd = Selection.getSelectionEnd(s);
            if (s.length() > 1 // Can delete another character
                    && count == 1 // Deleting only one character
                    && after == 0 // Deleting
                    && s.charAt(start) == '-' // a hyphen
                    && selStart == selEnd)
            {
                // no selection
                deletingHyphen = true;
                hyphenStart = start;
                // Check if the user is deleting forward or backward
                deletingBackward = selStart == start + 1;
            }
            else
                deletingHyphen = false;

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable text)
        {
            switch(view.getId())
            {
                case R.id.editName:
                {
                    inputLayoutName.setErrorEnabled(false);
                }
                break;

                case R.id.editIC:
                {
                    insertDashIC(text);
                    inputLayoutIC.setErrorEnabled(false);
                }

                break;
                case R.id.editContactNumber:
                {
                    inputLayoutContactNumber.setErrorEnabled(false);
                }
                break;
                case R.id.editEmail:
                {
                    inputLayoutEmail.setErrorEnabled(false);
                }
                break;
                case R.id.editSchool:
                {
                    inputLayoutSchool.setErrorEnabled(false);
                }
                break;
            }
        }

        // insert dash/hypen for IC
        void insertDashIC(Editable text)
        {
            if (isFormatting)
                return;

            isFormatting = true;

            // If deleting hyphen, also delete character before or after it
            if (deletingHyphen && hyphenStart > 0)
            {
                if (deletingBackward)
                {
                    if (hyphenStart - 1 < text.length())
                    {
                        text.delete(hyphenStart - 1, hyphenStart);
                    }
                }
                else if (hyphenStart < text.length())
                {
                    text.delete(hyphenStart, hyphenStart + 1);
                }
            }

            // find at which EditText and switching it
            // insert dash/hyphen also
            if(view.getId() == R.id.editIC)
            {
                if (text.length() == 6 || text.length() == 9)
                {
                    text.append('-');
                }
            }
            isFormatting = false;
        }
    }
}
