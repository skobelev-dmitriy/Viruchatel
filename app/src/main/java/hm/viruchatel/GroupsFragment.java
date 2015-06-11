package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Group;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class GroupsFragment extends Fragment {
    ListView listView;
    ApplicationInterface listener;
    ArrayList<Group> arrayList;
    TextView emptyList;
    GroupAdapter adapter;

public GroupsFragment(){

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
        listView=(ListView)view.findViewById(R.id.listView);
        emptyList=(TextView)view.findViewById(R.id.empty_list);
        arrayList=new ArrayList<>();
        adapter=new GroupAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title=arrayList.get(position).getTitle();
                int grId=arrayList.get(position).getId();
                listener.openGroup(title,grId);

            }
        });

        loadGroups();



        return view;

    }
    private void loadGroups(){
        for (int i=0; i<20; i++){
            Group group=new Group();
            arrayList.add(group);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    private class GroupAdapter extends BaseAdapter {


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
                Group group=(Group)getItem(position);

                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_group, null);

                TextView title = (TextView) convertView.findViewById(R.id.tv_title);

                TextView description = (TextView) convertView.findViewById(R.id.tv_description);
                TextView numUsers = (TextView) convertView.findViewById(R.id.tv_num_users);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_user_photo);



                title.setText(group.getTitle());

                description.setText(group.getDescription());
                numUsers.setText(group.getNum_users());

            }else{
                listView.setVisibility(View.GONE);

               emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
