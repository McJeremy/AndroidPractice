package com.example.xuzz.uicustomviews;

/**
 * Created by xuzz on 2016/3/4.
 */
public class Fruit
{
    private String name;
    private int imageID;
    public Fruit(String name,int imageID)
    {
        this.name=name;
        this.imageID=imageID;
    }

    public String getName()
    {return this.name;}

    public int getImageID()
    {
        return this.imageID;
    }
}
