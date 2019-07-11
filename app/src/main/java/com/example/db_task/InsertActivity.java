package com.example.db_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.db_task.database.DaoSession;
import com.example.db_task.database.DbConnection;
import com.example.db_task.database.Employee;
import com.example.db_task.database.EmployeeDao;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Or;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.List;

public class InsertActivity extends AppCompatActivity implements Validator.ValidationListener {
    private DbConnection dbConnection;
    private EmployeeDao employeeDao;
    private DaoSession daoSession;
    private Validator validator;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private Button insertButton;
    private Button showButton ;
    private String name;
    private int age;
    private String jobTitle;
    private String gender;
    private boolean male;
    private boolean female;

    // validate each field

    @NotEmpty
    @Length(min = 10,message = "from 10 to 20 letters" , max = 20)
    private EditText nameEditText;

    @NotEmpty
    @Length(min = 2, message = "from 20 to 60 years" , max = 2)
    @Min(20)
    @Max(60)
    private EditText ageEditText;


    @NotEmpty
    @Length(min = 10,message = "from 10 to 30 letter" ,max = 30)
    private EditText jobTitleEditText;

    @Checked
    private RadioGroup genderRadioGroup ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        // Edit Text validator
        validator = new Validator(this);
        validator.setValidationListener(this);


        // fields
        nameEditText = (EditText) findViewById(R.id.nameField);
        ageEditText = (EditText) findViewById(R.id.ageField);
        jobTitleEditText = (EditText) findViewById(R.id.jobTitleField);

        // gender indicatiors
        male = false;
        female = false;



        // gender radio buttons
        genderRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        maleRadio = (RadioButton) findViewById(R.id.maleRadio);


        // male checked
        maleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleChecked();
            }
        });


        femaleRadio = (RadioButton) findViewById(R.id.femaleRadio);

        // female checked
        femaleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleChecked();
            }
        });



        insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate fields
                validator.validate();
            }
        });

        showButton = (Button) findViewById(R.id.displaytButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });




        // database connection
        dbConnection = new DbConnection(this);
        dbConnection.connect();
        daoSession = dbConnection.getDaoSession();

        // employee table session
        employeeDao = daoSession.getEmployeeDao();


    }

    public void showData() {
        Intent intent = new Intent(InsertActivity.this , ShowDataActivity.class);
        startActivity(intent);
    }

    public void femaleChecked() {
        female = true;
    }

    public void getParameters() {
        name = nameEditText.getText().toString();
        age = Integer.parseInt(ageEditText.getText().toString());
        jobTitle = jobTitleEditText.getText().toString();
        if (male) {
            gender = "male";
        } else {
            gender = "female";
        }

    }

    public void maleChecked() {
        male = true;
    }


    public void insertRecord() {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);

        // insert the record
        employeeDao.insert(employee);
        Toast.makeText(this, "inserted name " + name + " age " + age
                + " title " + jobTitle + " gender" + gender, Toast.LENGTH_SHORT).show();

    }

    // validation callbacks

    @Override
    public void onValidationSucceeded() {
        // all data is valid
        getParameters();
        insertRecord();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }


    }
}
