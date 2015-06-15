package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Group;
import hm.viruchatel.models.Profile;
import hm.viruchatel.models.UserFriend;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class UserFriendsFragment extends Fragment {


    ListView listView;
    ApplicationInterface listener;
    ArrayList<UserFriend> arrayList;
    TextView emptyList;
    UserFriendsAdapter adapter;
    MenuItem searchFriends;
public UserFriendsFragment(){

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_groups,container,false);
        setHasOptionsMenu(true);
        listView=(ListView)view.findViewById(R.id.listView);
        emptyList=(TextView)view.findViewById(R.id.empty_list);
        arrayList=new ArrayList<>();
        adapter=new UserFriendsAdapter();
        listView.setAdapter(adapter);

        loadFriends();
        return view;

    }
    private void loadFriends(){
        for (int i=0; i<20; i++){
            UserFriend friend=new UserFriend();
            arrayList.add(friend);
            adapter.notifyDataSetChanged();
        }
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       searchFriends = menu.add("search");
        searchFriends.setIcon(R.drawable.ic_search);
        searchFriends.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        searchFriends.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getActivity().getFragmentManager().popBackStackImmediate();

                return false;
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    private class UserFriendsAdapter extends BaseAdapter {


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

                emptyList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                UserFriend profile=(UserFriend)getItem(position);

                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_user_friend, null);

                TextView name = (TextView) convertView.findViewById(R.id.tv_user_name);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_user_photo);



                name.setText(profile.getName());



            }else{
                listView.setVisibility(View.GONE);

                emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
