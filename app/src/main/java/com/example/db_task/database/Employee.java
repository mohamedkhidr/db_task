package com.example.db_task.database;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;



@Entity // a table in the database
public class Employee {
    @Id(autoincrement = true)
    private Long Id;

    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private String jobTitle;
    @NotNull
    private String gender;
    @Generated(hash = 1232861794)
    public Employee(Long Id, @NotNull String name, int age,
            @NotNull String jobTitle, @NotNull String gender) {
        this.Id = Id;
        this.name = name;
        this.age = age;
        this.jobTitle = jobTitle;
        this.gender = gender;
    }
    @Generated(hash = 202356944)
    public Employee() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}