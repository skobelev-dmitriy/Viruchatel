package hm.viruchatel;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Conversation;


public class ChatActivity extends ActionBarActivity {
    public static final String TAG ="myLogs";
    Toolbar toolbar;
    ListView listView;
    // CardView emptyList;
    EditText textEdit;
    ImageButton send;
    private ArrayList<Conversation> msgList;
    private ChatAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        textEdit=(EditText)findViewById(R.id.txt);
        send=(ImageButton)findViewById(R.id.btn_send);
        listView=(ListView)findViewById(R.id.listView);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        msgList=new ArrayList<Conversation>();
        myAdapter=new ChatAdapter();

        listView.setAdapter(myAdapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setStackFromBottom(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.text_color3));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle("Анастасия Максимова");
        setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadMessages();
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
        if (id == R.id.action_new_message) {
            Toast.makeText(this,"Новое сообщение",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadMessages(){
        for (int i=0; i<2; i++){
            Conversation message=new Conversation();
            msgList.add(message);

        }
        myAdapter.notifyDataSetChanged();
    }
    private  void sendMessage(){
        if (textEdit.length()==0)
            return;

        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(textEdit.getWindowToken(),0);

        String s=textEdit.getText().toString();

        final Conversation c= new Conversation(s,new Date(), "я");
        c.setStatus(Conversation.STATUS_SENDING);
        msgList.add(c);
        myAdapter.notifyDataSetChanged();
        textEdit.setText(null);

       /*  ParseObject po=new ParseObject("Chat");
        po.put("sender",UserList.user.getUsername());
        po.put("receiver",buddy);
        po.put("message", s);
        po.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null)
                    c.setStatus(Conversation.STATUS_SENT);
                else
                    c.setStatus(Conversation.STATUS_FAILED);
                adapter.notifyDataSetChanged();
            }
        });*/

    }
    private class ChatAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return msgList.size();
        }

        @Override
        public Object getItem(int position) {
            return msgList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getCount()!=0) {

                // emptyList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                Conversation c = (Conversation) getItem(position);
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (c.isSent()){
                    convertView = inflater.inflate(R.layout.chat_item_rcv, null);
                }else
                    convertView = inflater.inflate(R.layout.chat_item_sent, null);


                TextView time = (TextView) convertView.findViewById(R.id.lbl2);

                TextView message = (TextView) convertView.findViewById(R.id.lbl1);

                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);



                time.setText(DateUtils.getRelativeDateTimeString(ChatActivity.this, c.getDate().getTime(), DateUtils.SECOND_IN_MILLIS, DateUtils.DAY_IN_MILLIS, 0));

                message.setText(c.getMessage());


            }else{
                listView.setVisibility(View.GONE);
                //  emptyList=(CardView)convertView.findViewById(R.id.empty_list);
                //  emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
