package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.Date;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Conversation;
import hm.viruchatel.models.Message;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class ChatFragment extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    ListView listView;
   // CardView emptyList;
    EditText textEdit;
    ImageButton send;
    private ArrayList<Conversation> msgList;
    private ChatAdapter myAdapter;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        try {
            listener=(ApplicationInterface)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ApplicationInterface");

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);
        //   api=new Api();
        View v=inflater.inflate(R.layout.fragment_chat, container, false);
        textEdit=(EditText)v.findViewById(R.id.txt);
        send=(ImageButton)v.findViewById(R.id.btn_send);
        listView=(ListView)v.findViewById(R.id.listView);
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

        loadMessages();


        return v;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

       /* if (asyncLogin!=null) {
            Log.d(TAG, "asyncLogin cancel " + asyncLogin.cancel(false));
        }*/
    }
    private void loadMessages(){
        for (int i=0; i<20; i++){
            Conversation message=new Conversation();
            msgList.add(message);
            myAdapter.notifyDataSetChanged();
        }
    }
    private  void sendMessage(){
        if (textEdit.length()==0)
            return;

        InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (c.isSent()){
                    convertView = inflater.inflate(R.layout.chat_item_sent, null);
                }else
                    convertView = inflater.inflate(R.layout.chat_item_sent, null);


                TextView time = (TextView) convertView.findViewById(R.id.lbl2);

                TextView message = (TextView) convertView.findViewById(R.id.lbl1);

                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);



                time.setText(DateUtils.getRelativeDateTimeString(getActivity(), c.getDate().getTime(), DateUtils.SECOND_IN_MILLIS, DateUtils.DAY_IN_MILLIS, 0));

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
