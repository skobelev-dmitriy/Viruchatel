package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.HelpMessage;
import hm.viruchatel.models.Message;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class MessagesFragment extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    ListView listView;
    CardView emptyList;
    private ArrayList<Message> msgList;
    private MessageAdapter myAdapter;

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
        View v=inflater.inflate(R.layout.fragment_messages_list, container, false);
        listView=(ListView)v.findViewById(R.id.listView);
        msgList=new ArrayList<Message>();
        myAdapter=new MessageAdapter();

        listView.setAdapter(myAdapter);
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
            Message message=new Message(12+":"+23,i*200+" рублей не хватает.:(","Человеческая особь № "+i,null );
            msgList.add(message);
            myAdapter.notifyDataSetChanged();
        }
    }
    private class MessageAdapter extends BaseAdapter {


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

                //emptyList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                Message msg = (Message) getItem(position);
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_message_list, null);

                TextView time = (TextView) convertView.findViewById(R.id.tv_time);

                TextView message = (TextView) convertView.findViewById(R.id.tv_message);
                TextView name = (TextView) convertView.findViewById(R.id.tv_name);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);



                time.setText(msg.getTime());

                message.setText(msg.getMessage());
                name.setText(msg.getName());

            }else{
                listView.setVisibility(View.GONE);
                emptyList=(CardView)convertView.findViewById(R.id.empty_list);
                emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
