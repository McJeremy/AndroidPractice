package com.example.xuzz.uicustomviews;

/**
 * Created by xuzz on 2016/3/4.
 */
public class ChatMsg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;

    public ChatMsg(String content,int type)
    {
        this.content=content;
        this.type=type;
    }

    public String getContent()
    {return this.content;}

    public int getType()
    {
        return this.type;
    }
}
