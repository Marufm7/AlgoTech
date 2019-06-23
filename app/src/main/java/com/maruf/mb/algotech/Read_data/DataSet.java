package com.maruf.mb.algotech.Read_data;


/**
 * Created by sm on 12-17-2018.
 */

public class DataSet {
    private String firstname, lastname, course_name,course_details, cource_duration,course_image ;


    public String getfirstname() {
        return firstname;
    }
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getlastname() {
        return lastname;
    }
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }


//    public String getFirstname() {
//        return firstname;
//    }
//    public String getLastname(){
//        return lastname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public void setLastname(String lastname){
//        this.lastname = lastname;
//    }

    /*public String getreg_date() { return reg_date;  }
    public void setreg_date(String reg_date) {this.reg_date = reg_date;}*/

    public String get_course_name() {
        return course_name;
    }

    public void set_course_name(String course_name) {
        this.course_name = course_name;

    }
    //.............course_details.............
    public void set_course_details(String course_details) {
        this.course_details = course_details;

    }
    public String get_course_details() {
        return course_details;

    }


    //.............course_duration.............
    public String get_cource_duration(){ return  cource_duration;}
    public  void  set_cource_duration(String cource_duration){ this.cource_duration = cource_duration;}

    //.............course_Image.............
    public  void  set_course_image(String course_image){ this.course_image = course_image;}
    public String get_course_image(){ return  course_image;}



//.............course_Name.............
    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    //.............course_details.............
    public String getCourse_details(){
        return course_details;
    }

    public void setCourse_details(String course_details){
        this.course_details = course_details;
    }
}
