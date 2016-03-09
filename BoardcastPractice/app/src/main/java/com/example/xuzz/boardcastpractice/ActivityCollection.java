package com.example.xuzz.boardcastpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzz on 2016/3/7.
 */
public class ActivityCollection {
    private  static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity act)
    {
        activities.add(act);
    }

    public static void removeActivity(Activity act)
    {
        activities.remove(act);
    }
    
    public static void removeAll()
    {
        for(Activity act : activities)
        {
            if(!act.isFinishing())
            {
                act.finish();
            }
        }
    }
}
