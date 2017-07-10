package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
EditText etName;
    EditText etGPA;
RadioGroup rgGender;
    CheckBox ckbLike;


    @Override
    protected void onPause() {
        super.onPause();
        //Step 1a: Retrieve data input of the user
        String strName =etName.getText().toString();
        float flGPA= Float.valueOf(etGPA.getText().toString());
        boolean bLike=ckbLike.isChecked();
        int intGenderId=rgGender.getCheckedRadioButtonId();
        //Step 1b: Obtain an instance of the Shared Preference
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1c: Obtain an instance of the Shared Preference Editor for update later
        SharedPreferences.Editor prefEdit=prefs.edit();
        //Step 1d: Add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa",flGPA);
        prefEdit.putBoolean("like",bLike);
        prefEdit.putInt("genderId",intGenderId);
        //Step 1e: Call commit () method to save the changes into the Shared Preference
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a: Obtain an instance of the Shared Preferences
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data with the key, name from the SharedPreferences Object
        String strName=prefs.getString("name","John");
        float gpa= prefs.getFloat("gpa",0);

        //Part 2
        //Retrieve boolean value using key "like"
        boolean bLike =prefs.getBoolean("like",false);
        int intGenderId=prefs.getInt("genderId",R.id.radioButtonGenderMale);

        //Step 2c: Update the UI element with the value.
        etName.setText(strName);
        etGPA.setText(gpa+"");

        //Part 2
        ckbLike.setChecked(bLike);
        rgGender.check(intGenderId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=(EditText) findViewById(R.id.editTextName);
        etGPA=(EditText) findViewById(R.id.editTextGPA);
        rgGender=(RadioGroup)findViewById(R.id.RadioGroupGender);
        ckbLike=(CheckBox)findViewById(R.id.checkBoxLikeProgramming);
    }
}
