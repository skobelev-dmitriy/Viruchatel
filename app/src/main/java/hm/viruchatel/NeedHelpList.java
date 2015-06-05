package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.HelpMessage;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpList extends Fragment implements View.OnClickListener{
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    private HelpAdapter myAdapter;
    private ArrayList<HelpMessage> msgList;
    ListView listView;
    CardView emptyList;
    Button invite;
    SwipeRefreshLayout swipeRefreshLayout;

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
        View v=inflater.inflate(R.layout.fragment_need_help_list, container, false);
        msgList=new ArrayList<HelpMessage>();
        listView=(ListView)v.findViewById(R.id.listView);
        emptyList=(CardView)v.findViewById(R.id.empty_list);
        invite=(Button)v.findViewById(R.id.but_invite_friends);
        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadHelpMessages();
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        myAdapter=new HelpAdapter();

        listView.setAdapter(myAdapter);
     //loadHelpMessages();
        Log.d(TAG, "Fr_ onCreateView");



        return v;
    }

    private void loadHelpMessages(){
        for (int i=0; i<20; i++){
            HelpMessage message=new HelpMessage("ул. Спортивная, д."+i*5,i*100+"м.",i*3+" мин.", "Сломалась "+i+" машин.",i*200+"рублей не хватает.:(","Человеческая особь № "+i,null,null,i%3 );
            msgList.add(message);
            myAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
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
    private class HelpAdapter extends BaseAdapter {


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

                emptyList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                HelpMessage msg = (HelpMessage) getItem(position);
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_need_help, null);
                TextView adress = (TextView) convertView.findViewById(R.id.tv_adress);
                TextView distance = (TextView) convertView.findViewById(R.id.tv_distance);
                TextView time = (TextView) convertView.findViewById(R.id.tv_time);
                TextView caption = (TextView) convertView.findViewById(R.id.tv_caption);
                TextView message = (TextView) convertView.findViewById(R.id.tv_message);
                TextView name = (TextView) convertView.findViewById(R.id.tv_name);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);
                ImageView minimap = (ImageView) convertView.findViewById(R.id.im_minimap);
                Button userType = (Button) convertView.findViewById(R.id.but_user_type);

                adress.setText(msg.getAdress());
                distance.setText(msg.getDistance());
                time.setText(msg.getTime());
                caption.setText(msg.getCaption());
                message.setText(msg.getMessage());
                name.setText(msg.getName());
                int type = msg.getUserType();
                switch (type) {
                    case 0:
                        userType.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        userType.setBackgroundResource(R.drawable.badge_guest);
                        userType.setText(R.string.guest);
                        userType.setTextColor(getResources().getColor(R.color.text_color_blue_button));
                        break;
                    case 2:
                        userType.setBackgroundResource(R.drawable.badge_friend);
                        userType.setText(R.string.my_friend);
                        userType.setTextColor(getResources().getColor(R.color.text_color_green));

                        break;
                }
            }else{
                listView.setVisibility(View.GONE);
                emptyList=(CardView)convertView.findViewById(R.id.empty_list);
                emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
