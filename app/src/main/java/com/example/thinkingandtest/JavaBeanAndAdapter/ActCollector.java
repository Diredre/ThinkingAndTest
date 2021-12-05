package com.example.thinkingandtest.JavaBeanAndAdapter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addAct(Activity activity){
        activities.add(activity);
    }

    public static void removeAct(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }

        activities.clear();
    }
}
