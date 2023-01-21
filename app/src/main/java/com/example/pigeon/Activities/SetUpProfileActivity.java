package com.example.pigeon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pigeon.R;

public class SetUpProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        Bundle bundle = getIntent().getExtras();

        String applicantID=bundle.getString("ID");
        String applicantName = bundle.getString("Name");
        String aboutApplicant = bundle.getString("aboutMe");
        String skills= bundle.getString("Skills");
        String applicantTransport= bundle.getString("applicantTransport");
        String applicantLocation = bundle.getString("jobLocation");
        String workExp=bundle.getString("workExp");
        String applicantSkills=bundle.getString("applicantSkills");


    }
}