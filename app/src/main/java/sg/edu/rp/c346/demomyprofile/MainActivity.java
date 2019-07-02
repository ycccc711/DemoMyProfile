package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName,etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        //step 2b retrieve the saved data with key "greeting" from the SharedPreference object
        //def = mo greetings (if no data it will take the def one otherwise will take the sharedpreferences de)

        String Name = prefs.getString("name","");
        float GPA = prefs.getFloat("gpa",0);
        String Gen = prefs.getString("gender","");

        if(Gen.equals("Male")){
            rgGender.check(R.id.radioButtonGenderMale);
        }else{
            rgGender.check(R.id.radioButtonGenderFemale);
        }
        etName.setText(Name);
        etGPA.setText(GPA+"");

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }


    public void saveData(){
        //step 1a get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        String gender = "";
        if(rgGender.getCheckedRadioButtonId() == R.id.radioButtonGenderMale){
            gender = "Male";
        }else{
            gender = "Female";
        }

        //step 1b obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        //step 1b obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //step 1c add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putString("gender",gender);

        //step 1d call commit() to save the changes into SharedPreferences
        prefEdit.commit();
    }
}
