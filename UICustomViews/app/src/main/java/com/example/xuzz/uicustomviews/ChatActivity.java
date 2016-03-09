package com.example.xuzz.uicustomviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView chatList;
    EditText inputTxt;
    Button btnSend ;
    ChatMsgAdapter msgAdapter;

    List<ChatMsg> msgs = new ArrayList<ChatMsg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initMsgs();
        chatList =(ListView) findViewById(R.id.msg_list_view);
        inputTxt =(EditText) findViewById(R.id.input_text);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);
        chatList.setOnItemClickListener(this);

        this.registerForContextMenu(chatList);

        msgAdapter = new ChatMsgAdapter(ChatActivity.this,R.layout.chat_msg_item,msgs);
        chatList.setAdapter(msgAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initMsgs()
    {
        ChatMsg msg1 = new ChatMsg("Hello!",ChatMsg.TYPE_RECEIVED);
        msgs.add(msg1);
        ChatMsg msg2 = new ChatMsg("Hi! Who are you ? ",ChatMsg.TYPE_SENT);
        msgs.add(msg2);

        ChatMsg msg3 = new ChatMsg("My Name is Xuzz!",ChatMsg.TYPE_RECEIVED);
        msgs.add(msg3);

        ChatMsg msg4 = new ChatMsg("Waht are you doing ?",ChatMsg.TYPE_SENT);
        msgs.add(msg4);
        ChatMsg msg5 = new ChatMsg("Can you do me a favor ?",ChatMsg.TYPE_SENT);
        msgs.add(msg5);

        ChatMsg msg6 = new ChatMsg("OK,What can i do for you?",ChatMsg.TYPE_RECEIVED);
        msgs.add(msg6);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSend)
        {
            String sentTxt = inputTxt.getText().toString();
            if(!"".equals(sentTxt))
            {
                ChatMsg msg = new ChatMsg(sentTxt,ChatMsg.TYPE_SENT);
                msgs.add(msg);
                msgAdapter.notifyDataSetChanged();

                chatList.setSelection(msgs.size());

                inputTxt.setText("");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View view ,ContextMenu.ContextMenuInfo menuInfo)
    {
        if(view.getId()==R.id.msg_list_view)
        {
            menu.setHeaderTitle("消息操作");

            menu.add(0, 1, Menu.NONE, "删除");
            menu.add(0,2,Menu.NONE,"复制");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 得到当前被选中的item信息
        ContextMenu.ContextMenuInfo menuInfo = (ContextMenu.ContextMenuInfo) item.getMenuInfo();


        switch(item.getItemId()) {
            case 1:
            break;
            case 2:
            // do something
            break;
            case 3:
            // do something
            break;
            case 4:
            // do something
            break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
