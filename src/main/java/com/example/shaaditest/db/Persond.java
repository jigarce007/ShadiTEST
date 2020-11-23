package com.example.shaaditest.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Persond implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String login_username ;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "title_name")
    private String title_name;

    @ColumnInfo(name = "first_nmae")
    private String first_nmae;

    @ColumnInfo(name = "last_name")
    private String last_name;


    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "state")
    private String state;


    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "email")
    private String email;


    @ColumnInfo(name = "age")
    private String age;

    @ColumnInfo(name = "phone")
    private String phone;


    @ColumnInfo(name = "pic_large")
    private String pic_large;

    public String getLogin_username() {
        return login_username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getFirst_nmae() {
        return first_nmae;
    }

    public void setFirst_nmae(String first_nmae) {
        this.first_nmae = first_nmae;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPic_large() {
        return pic_large;
    }

    public void setPic_large(String pic_large) {
        this.pic_large = pic_large;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

}
