package com.maruf.mb.algotech.Commonly_used_class;

import android.content.Context;
import android.content.Intent;

import com.maruf.mb.algotech.About_Us;
import com.maruf.mb.algotech.Contact_us;
import com.maruf.mb.algotech.Courses_list;
import com.maruf.mb.algotech.Products_List;
import com.maruf.mb.algotech.R;
import com.maruf.mb.algotech.Service_List;
import com.maruf.mb.algotech.Test_Activity;


public class All_Menu_Class {

    public static boolean HandleMenu(Context context, int itemId) {
        switch (itemId){
            case R.id.nav_HomeID:
               Intent intent=new Intent(context, Courses_list.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               context.startActivity(intent);
                return true;
                case R.id.nav_contactID:
               Intent intent1=new Intent(context, Contact_us.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent1);
                return true;
                case R.id.nav_service_listID:
               Intent intent2=new Intent(context, Service_List.class);
//               intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent2);
                return true;
                case R.id.nav_productID:
               Intent intent3=new Intent(context, Products_List.class);
//                    intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent3);
                return true;
                case R.id.nav_aboutID:
               Intent intent4=new Intent(context, About_Us.class);
                    context.startActivity(intent4);
                return true;
            default:
                break;
        }
        return false;
    }
}
