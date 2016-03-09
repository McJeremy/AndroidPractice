package com.example.xuzz.uicustomviews;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xuzz on 2016/3/4.
 */
public class ChatMsgAdapter extends ArrayAdapter<ChatMsg> {

    private int resourceID ;
    public ChatMsgAdapter(Context context, int resource, List<ChatMsg> objects) {
        super(context, resource, objects);

        resourceID = resource;
    }

    @Override
    public View getView(int position ,View convertView ,ViewGroup parentView)
    {
        ChatMsg msg = getItem(position);
        View v;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            v = LayoutInflater.from(getContext()).inflate(resourceID,null);

            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout)v.findViewById(R.id.msg_left_layout);
            viewHolder.rightLayout=(LinearLayout)v.findViewById(R.id.msg_right_layout);
            viewHolder.sentTxtView = (TextView)v.findViewById(R.id.msg_right);
            viewHolder.receiveTxtView = (TextView)v.findViewById(R.id.msg_left);
            convertView = v;

            convertView.setTag(viewHolder);
        }
        else
        {
            v = convertView;

            viewHolder =(ViewHolder) convertView.getTag();
        }

        if(msg.getType() == ChatMsg.TYPE_RECEIVED)
        {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);

            viewHolder.receiveTxtView.setText(msg.getContent());
        }
        else if (msg.getType()==ChatMsg.TYPE_SENT)
        {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);

            viewHolder.sentTxtView.setText(msg.getContent());
        }

        return v;
    }

    class ViewHolder
    {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView sentTxtView;
        TextView receiveTxtView;
    }
}
