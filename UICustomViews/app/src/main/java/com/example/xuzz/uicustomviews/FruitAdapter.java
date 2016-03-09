package com.example.xuzz.uicustomviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xuzz on 2016/3/4.
 */
public class FruitAdapter extends ArrayAdapter<Fruit>
{
    private int resourceID;
    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);

        resourceID = resource;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent)
    {
        Fruit f = getItem(position);
        View v ;
        ViewHolder viewHolder;
        if(null==convertView)
        {
            v = LayoutInflater.from(getContext()).inflate(resourceID, null);

            viewHolder =new ViewHolder();
            viewHolder.fruitImage = (ImageView) v.findViewById(R.id.fruit_image);
            viewHolder.fruitName =(TextView)v.findViewById(R.id.fruit_name);

            v.setTag(viewHolder);
        }
        else
        {
            v = convertView;
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.fruitImage.setImageResource(f.getImageID());
        viewHolder.fruitName.setText(f.getName());
        return v;
    }

    class ViewHolder
    {
        public TextView fruitName;
        public ImageView fruitImage;
    }
}
