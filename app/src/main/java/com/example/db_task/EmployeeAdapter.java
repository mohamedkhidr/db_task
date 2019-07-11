package com.example.db_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.db_task.database.Employee;

import java.util.ArrayList;

// custom apdapter with to display results in 4 text views

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {

        super(context, 0, employees);
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        // get the current employee record to be displayed
        Employee currentEmployee = getItem(position);

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent , false);
        }

        // attach each field to its view

        // name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);

        nameTextView.setText("Name : "+currentEmployee.getName());

        // age

        TextView ageTextView = (TextView) listItemView.findViewById(R.id.age);

        ageTextView.setText("Age : "+currentEmployee.getAge());

        // JobTitle

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        titleTextView.setText("Job title : "+currentEmployee.getJobTitle());

        // gender

        TextView genderTextView = (TextView) listItemView.findViewById(R.id.gender);

        genderTextView.setText("Gender : "+currentEmployee.getGender());


        return listItemView;
    }
}
