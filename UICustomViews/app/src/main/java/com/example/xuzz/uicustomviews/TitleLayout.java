package com.example.xuzz.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xuzz on 2016/3/4.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button btnBack = (Button)findViewById(R.id.title_back);
        Button btnEdit = (Button)findViewById(R.id.title_edit);
        TextView txtTitle = (TextView) findViewById(R.id.title_text);

        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_edit:
                Toast.makeText(getContext(),"you click edit",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
