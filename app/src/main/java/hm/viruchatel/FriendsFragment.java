package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Friend;
import hm.viruchatel.SimpleGestureFilter.SimpleGestureListener;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class FriendsFragment extends Fragment implements SimpleGestureListener {

    ApplicationInterface listener;
    TabHost tabs;
    ListView listViewFr, listViewReq;
    ArrayList<Friend> arrayListFr, arrayListReq;
    FriendsAdapter adapter1;
    NewFriendsAdapter adapter2;
    TabHost.TabSpec tabFriends, tabRequests;




public FriendsFragment(){

}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener=(ApplicationInterface)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ApplicationInterface");

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_friends,container,false);
        tabs=(TabHost)view.findViewById(R.id.tabHost2);
        listViewFr=(ListView)view.findViewById(R.id.listView);
        listViewReq=(ListView)view.findViewById(R.id.listView_request);

        arrayListFr=new ArrayList<>();
        arrayListReq=new ArrayList<>();



        adapter1=new FriendsAdapter(arrayListFr);
        adapter2=new NewFriendsAdapter(arrayListReq);
        listViewFr.setAdapter(adapter1);
        listViewReq.setAdapter(adapter2);
        listViewFr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int user_id = arrayListFr.get(position).getId();
                listener.openProfile(user_id);

            }
        });
        listViewReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int user_id = arrayListReq.get(position).getId();
                listener.openProfile(user_id);

            }
        });
        listViewFr.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                Toast.makeText(getActivity().getApplicationContext(), "Action index " + event.getActionIndex(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        tabs.setup();
        loadFriends();


        tabFriends=tabs.newTabSpec("tag1");
       tabFriends.setContent(R.id.listView);

        tabFriends.setIndicator("ДРУЗЬЯ: " + arrayListFr.size());
        tabs.addTab(tabFriends);

        tabRequests = tabs.newTabSpec("tag2");
        tabRequests.setContent(R.id.listView_request);

        tabRequests.setIndicator("ЗАЯВКИ: " + arrayListReq.size());
        tabs.addTab(tabRequests);
        tabs.setCurrentTabByTag("tag1");

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // Toast.makeText(getActivity().getApplicationContext(),"Tab id="+tabId,Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
   private void loadFriends() {
       for (int i = 0; i < 20; i++) {
           Friend friend = new Friend();
           arrayListFr.add(friend);

           adapter1.notifyDataSetChanged();


       }
      for (int j=0; j<2; j++){
           Friend friend=new Friend();
           arrayListReq.add(friend);
           adapter2.notifyDataSetChanged();
       }


   }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSwipe(int direction) {
        switch (direction) {
            case SimpleGestureFilter.SWIPE_LEFT:
                Toast.makeText(getActivity().getApplicationContext(),"Swipe Left",Toast.LENGTH_SHORT).show();
                break;
            case SimpleGestureFilter.SWIPE_RIGHT:
                Toast.makeText(getActivity().getApplicationContext(),"Swipe Right",Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void onDoubleTap() {

    }


    private class FriendsAdapter extends BaseAdapter {
        ArrayList<Friend>arrayList;
        public FriendsAdapter (ArrayList<Friend> arrayList){
            this.arrayList=arrayList;
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getCount()!=0) {

              //  emptyList.setVisibility(View.GONE);
              //  listView.setVisibility(View.VISIBLE);

                Friend friend=(Friend)getItem(position);

                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_friend, null);

                TextView name = (TextView) convertView.findViewById(R.id.tv_user_name);

                TextView rating = (TextView) convertView.findViewById(R.id.tv_rating);
                TextView top = (TextView) convertView.findViewById(R.id.tv_top);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_user_photo);



                name.setText(friend.getName());

                rating.setText(friend.getRating());
                top.setText(friend.getTop());

            }else{
               // listView.setVisibility(View.GONE);

             //   emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }

    private class NewFriendsAdapter extends BaseAdapter {
        ArrayList<Friend>arrayList;
        public NewFriendsAdapter (ArrayList<Friend> arrayList){
            this.arrayList=arrayList;
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getCount()!=0) {

                //  emptyList.setVisibility(View.GONE);
                //  listView.setVisibility(View.VISIBLE);

                Friend friend=(Friend)getItem(position);

                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_new_friend, null);

                TextView name = (TextView) convertView.findViewById(R.id.tv_user_name);


                ImageView photo = (ImageView) convertView.findViewById(R.id.im_user_photo);
                Button reject=(Button)convertView.findViewById(R.id.but_reject);
                Button accept=(Button)convertView.findViewById(R.id.but_accept);
                reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.rejectFriend();
                    }
                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listener.acceptFriend();
                    }
                });


                        name.setText(friend.getName());



            }else{
                // listView.setVisibility(View.GONE);

                //   emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
