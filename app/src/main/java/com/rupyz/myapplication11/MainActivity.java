package com.rupyz.myapplication11;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtDob, edtPhone;
    private Button addCourseBtn, readCourseBtn;
    private RadioGroup btnGender;
    private DBHandler dbHandler;
    String strName, strDob, strEmail, strPhone,strGender;
    boolean isUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        edtDob = findViewById(R.id.edt_dob);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);
        btnGender = findViewById(R.id.radioGroup);
        dbHandler = new DBHandler(MainActivity.this);

        strName = getIntent().getStringExtra("name");
        strEmail = getIntent().getStringExtra("email");
        strPhone = getIntent().getStringExtra("phone");
        strDob = getIntent().getStringExtra("dob");
        strGender = getIntent().getStringExtra("gender");

        edtName.setText(strName);
        edtEmail.setText(strEmail);
        edtPhone.setText(strPhone);
        edtDob.setText(strDob);



        if(strName != null && !strName.isEmpty()){
            addCourseBtn.setText("Update");
            isUpdate = true;
            readCourseBtn.setVisibility(View.GONE);
            if (strGender.equalsIgnoreCase("Male")){
                    btnGender.check(R.id.radioMale);
                }
                else {
                    btnGender.check(R.id.radioFemale);
                }
        }


        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isUpdate){
                    dbHandler.updateCourse(strName, edtName.getText().toString(), edtEmail.getText().toString(), edtPhone.getText().toString(), edtDob.getText().toString(), strGender);

                    Toast.makeText(MainActivity.this, "Item Updated..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ViewCourses.class);
                    startActivity(i);
                    finish();

                }


                else {
                    String name = edtName.getText().toString();
                    String email = edtEmail.getText().toString();
                    String phone = edtPhone.getText().toString();
                    String dob = edtDob.getText().toString();
                    if (name.isEmpty() && name.isEmpty() && email.isEmpty() && email.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    dbHandler.addNewCourse(name, email, phone, dob, strGender);
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();

                    edtName.setText("");
                    edtEmail.setText("");
                    edtPhone.setText("");
                    edtDob.setText("");
                }
            }
        });
        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewCourses.class);
                startActivity(i);
            }
        });

        btnGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    if(checkedRadioButton.getText().equals("Male")){
                        strGender = "Male";
                    }
                    if (checkedRadioButton.getText().equals("Female")){
                        strGender = "Female";
                    }
                    Toast.makeText(MainActivity.this, strGender, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
