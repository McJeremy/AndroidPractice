package com.example.xuzz.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by xuzz on 2016/3/3.
 */
public class MyTask implements  Runnable
{
    Handler handler;
    public MyTask(Handler h)
    {
        super();
        this.handler=h;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(5000);
            Message m = prepareMessage();

            handler.sendMessage(m);
        }
        catch (InterruptedException e)
        {}
    }

    private Message prepareMessage()
    {
        Message msg = handler.obtainMessage();
        Bundle b = new Bundle();
        b.putString("message", "hello xuzz");
        msg.setData(b);

        return msg;
    }
}
