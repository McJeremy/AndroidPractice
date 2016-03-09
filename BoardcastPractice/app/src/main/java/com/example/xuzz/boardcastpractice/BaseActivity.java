package com.example.xuzz.boardcastpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by xuzz on 2016/3/7.
 */
public class BaseActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActivityCollection.addActivity(this);
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        ActivityCollection.removeActivity(this);
    }
}
