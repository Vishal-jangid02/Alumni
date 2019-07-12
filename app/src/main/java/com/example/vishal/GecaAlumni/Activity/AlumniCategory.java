package com.example.vishal.GecaAlumni.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vishal.GecaAlumni.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AlumniCategory extends AppCompatActivity {

    Spinner spinnerCourseWise,spinnerBranchWise,spinnerPassingYear;
    Button btn_Category;
    EditText nameEditText;

    ArrayList<String> BranchList;
    ArrayList<String> YearList;

    String name="";
    HashMap<String,ArrayList<String>> categoryMap= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_category);

        spinnerCourseWise=(Spinner) findViewById(R.id.spinnerCourseWise);
        spinnerBranchWise =(Spinner) findViewById(R.id.spinnerBranchWise);
        spinnerPassingYear = (Spinner) findViewById(R.id.spinnerPassingYear);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        btn_Category = (Button) findViewById(R.id.btn_Category);

        //spinner select course
        BranchList=new ArrayList<>();
        BranchList.add("First Select Course");
        categoryMap.put("All Courses",BranchList);


        //select branch
        BranchList=new ArrayList<>();
        BranchList.add("All Branches");
        BranchList.add("Computer Science");
        BranchList.add("Information Technology");
        BranchList.add("Electrical");
        BranchList.add("Electronics");
        BranchList.add("Mechanical");
        BranchList.add("Electrical Instrumentation ControlSystem");
        BranchList.add("Civil");

        categoryMap.put("B.Tech",BranchList);

        //select mba
        BranchList=new ArrayList<>();
        BranchList.add("All Branches");
        BranchList.add("Finance");
        BranchList.add("HRM");
        BranchList.add("Marketing");
        BranchList.add("ITM");

        categoryMap.put("MBA",BranchList);

        //select m.tech
        BranchList=new ArrayList<>();
        BranchList.add("All Branches");
        BranchList.add("CSE");
        BranchList.add("DC");
        BranchList.add("SE");
        BranchList.add("Production Engineering");
        BranchList.add("IT");

        categoryMap.put("M.Tech",BranchList);

        //select mca
        BranchList=new ArrayList<>();
        BranchList.add("N/A");
        categoryMap.put("MCA",BranchList);

        YearList=new ArrayList<>();
        YearList.add("All Batches");
        //YearList.add("Passing Year");
        for(int i=2001;i<=2025;i++){
            YearList.add(i+"");}

            btn_Category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        //course arrayAdapter
        final ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.AllCourses,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourseWise.setAdapter(arrayAdapter);

        spinnerCourseWise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String selecteditem=parent.getItemAtPosition(pos).toString();
                ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(AlumniCategory.this,R.layout.support_simple_spinner_dropdown_item,categoryMap.get(selecteditem));
                spinnerBranchWise.setAdapter(arrayAdapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //select year
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(AlumniCategory.this,R.layout.support_simple_spinner_dropdown_item,YearList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPassingYear.setAdapter(arrayAdapter2);

        spinnerPassingYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s=parent.getItemAtPosition(position).toString();
                YearList = new ArrayList<>();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=nameEditText.getText().toString();

                Intent intent = new Intent(AlumniCategory.this,Findothers.class);
                intent.putExtra("Course",spinnerCourseWise.getSelectedItem().toString());
                intent.putExtra("Branch",spinnerBranchWise.getSelectedItem().toString());
                intent.putExtra("name",name);
                intent.putExtra("PassingYear",spinnerPassingYear.getSelectedItem().toString());
                startActivity(intent);

            }
        });

    }
}
