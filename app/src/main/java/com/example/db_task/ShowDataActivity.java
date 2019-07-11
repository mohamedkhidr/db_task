package com.example.db_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.db_task.database.DaoSession;
import com.example.db_task.database.DbConnection;
import com.example.db_task.database.Employee;
import com.example.db_task.database.EmployeeDao;

import java.util.ArrayList;
import java.util.Collections;

public class ShowDataActivity extends AppCompatActivity {

    private ArrayList<Employee> employees ;
    private EmployeeAdapter  employeeArrayAdapter ;
    private DbConnection dbConnection;
    private DaoSession daoSession;
    private EmployeeDao employeeDao;
    private ListView menuListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        menuListView = (ListView)  findViewById(R.id.list);

        employees = new ArrayList<Employee> ();
        // database connection
        dbConnection = new DbConnection(this);
        dbConnection.connect();
        daoSession = dbConnection.getDaoSession();

        // employee table session
        employeeDao = daoSession.getEmployeeDao();

        fetch();
        show();
    }

    public void fetch() {
        employees.clear();
        // Load all items
        employees.addAll(employeeDao.loadAll());
        // order by the newest
        Collections.reverse(employees);



    }

    public void show(){
        employeeArrayAdapter = new EmployeeAdapter(this, employees);
        menuListView.setAdapter(employeeArrayAdapter);
    }
}
