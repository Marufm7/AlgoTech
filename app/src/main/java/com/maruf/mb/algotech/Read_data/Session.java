package com.maruf.mb.algotech.Read_data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Session {
    private SharedPreferences preferences;

    public Session (Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences( context );
    }
    //..................User Name.............
    public void setuserNAme(String userNAme){
        preferences.edit().putString( "userNAme", userNAme ).commit();
    }
    public String getuserNAme(){
        String userNAme = preferences.getString( "userNAme", "" );
        return userNAme;
    }
    //..................Email.............
    public void seteamil(String email){
        preferences.edit().putString( "email", email ).commit();
    }
    public String getemail(){
        String email = preferences.getString( "email", "" );
        return email;
    }

    //..................Image.............
    public void setimage(String image){
        preferences.edit().putString( "image", image ).commit();
    }
    public String getimage(){
        String image = preferences.getString( "image", "" );
        return image;
    }
    //..................Title.............
public void settitle(String title){
        preferences.edit().putString( "title", title ).commit();
    }
    public String gettitle(){
        String title = preferences.getString( "title", "" );
        return title;
    }
    //..................Decription.............
public void setdecription(String decription){
        preferences.edit().putString( "decription", decription ).commit();
    }
    public String getdecription(){
        String decription = preferences.getString( "decription", "" );
        return decription;
    }

    //..................Users_interested Service.............
public void setUsers_interest(String Users_interest){
        preferences.edit().putString( "Users_interest", Users_interest ).commit();
    }
    public String getUsers_interest(){
        String Users_interest = preferences.getString( "Users_interest", "" );
        return Users_interest;
    }
}
