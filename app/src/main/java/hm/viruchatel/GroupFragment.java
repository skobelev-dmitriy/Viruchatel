package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Message;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class GroupFragment extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    ListView listView;
    CardView emptyList;
    ImageView group_image, admin_photo, user_photo1, user_photo2, user_photo3, user_photo4;
    TextView admin_name, group_title, group_num_users,plus;
    Button  login_group, write_message, logout_group;
    TableRow member_view;
    LinearLayout adminButton;
    private ArrayList<Message> msgList;
    private MessageAdapter myAdapter;
    AlertDialog dialog;
    boolean member;

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
        int groupId=getArguments().getInt("id", 0);
        member=getArguments().getBoolean("member", false);
        View v=inflater.inflate(R.layout.fragment_group_view, container, false);







        listView=(ListView)v.findViewById(R.id.listView);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.fragment_group_header, listView, false);
        listView.addHeaderView(header, null, false);
        group_image=(ImageView)header.findViewById(R.id.im_group_title);
        admin_photo=(ImageView)header.findViewById(R.id.im_admin_photo);
        user_photo1=(ImageView)header.findViewById(R.id.im_gr_user_photo1);
        user_photo2=(ImageView)header.findViewById(R.id.im_gr_user_photo2);
        user_photo3=(ImageView)header.findViewById(R.id.im_gr_user_photo3);
        user_photo4=(ImageView)header.findViewById(R.id.im_gr_user_photo4);
        adminButton=(LinearLayout)header.findViewById(R.id.ll_admin);

        admin_name=(TextView)header.findViewById(R.id.tv_admin_name);
        group_title=(TextView)header.findViewById(R.id.tv_group_title);
        group_num_users=(TextView)header.findViewById(R.id.tv_group_num);

        member_view=(TableRow)header.findViewById(R.id.view_group_member);



        plus=(TextView)header.findViewById(R.id.but_plus);
        login_group=(Button)header.findViewById(R.id.but_login_group);
        write_message=(Button)header.findViewById(R.id.but_write);
        logout_group=(Button)header.findViewById(R.id.but_exit_group);

        adminButton.setOnClickListener(this);
        plus.setOnClickListener(this);
        login_group.setOnClickListener(this);
        logout_group.setOnClickListener(this);
        write_message.setOnClickListener(this);
        setupView(member);
        msgList=new ArrayList<Message>();
             myAdapter=new MessageAdapter();

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        loadMessages();


        return v;
    }

    private void setupView(boolean member) {
        if (member){
            login_group.setVisibility(View.INVISIBLE);
            member_view.setVisibility(View.VISIBLE);
        }else{
            login_group.setVisibility(View.VISIBLE);
            member_view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_plus:

                break;
            case R.id.but_login_group:
                member=true;
                setupView(member);
                break;
            case R.id.but_write:

                break;
            case R.id.but_exit_group:
                showDialog();

                break;
            case R.id.but_exit:
                dialog.dismiss();
                member=false;
                setupView(member);
                break;
            case R.id.but_cancel:
                dialog.dismiss();
                break;
            case R.id.ll_admin:
                listener.openProfile(1);
                break;

        }
    }
    private void showDialog(){
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_exit_group, null);
        Button cancel=(Button)v.findViewById(R.id.but_cancel);
        Button exit=(Button)v.findViewById(R.id.but_exit);

        cancel.setOnClickListener(this);
        exit.setOnClickListener(this);


        AlertDialog.Builder aBuilder=new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setView(v);



        dialog= aBuilder.create();
        dialog.show();
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

             //   emptyList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                Message msg = (Message) getItem(position);
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_message_list, null);

                TextView time = (TextView) convertView.findViewById(R.id.tv_time);

                TextView message = (TextView) convertView.findViewById(R.id.tv_message);
                TextView name = (TextView) convertView.findViewById(R.id.tv_title);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);



                time.setText(msg.getTime());

                message.setText(msg.getMessage());
                name.setText(msg.getName());

            }else{
                listView.setVisibility(View.GONE);
               // emptyList=(CardView)convertView.findViewById(R.id.empty_list);
              //  emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
