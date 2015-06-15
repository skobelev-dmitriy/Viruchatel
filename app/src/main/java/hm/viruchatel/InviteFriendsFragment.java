package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.SimpleGestureFilter.SimpleGestureListener;
import hm.viruchatel.models.Friend;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class InviteFriendsFragment extends Fragment implements SimpleGestureListener {

    ApplicationInterface listener;
    TabHost tabs;
    ListView listViewPhone, listViewVK, listViewFB;
    ArrayList<Friend> arrayListPhone, arrayListVK, arrayListFB;
    FriendsAdapter adapterPhone, adapterVK, adapterFB;
    TabHost.TabSpec tabPhone, tabVK, tabFB;




public InviteFriendsFragment(){

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

        View view=inflater.inflate(R.layout.fragment_invite_friends,container,false);
        tabs=(TabHost)view.findViewById(R.id.tabHost2);
        listViewPhone=(ListView)view.findViewById(R.id.list_phone);
        listViewVK=(ListView)view.findViewById(R.id.list_vk);
        listViewFB=(ListView)view.findViewById(R.id.list_fb);

        arrayListPhone=new ArrayList<>();
        arrayListVK=new ArrayList<>();
        arrayListFB=new ArrayList<>();



        adapterPhone =new FriendsAdapter(arrayListPhone,listViewPhone);
        adapterVK=new FriendsAdapter(arrayListVK,listViewVK);
        adapterFB=new FriendsAdapter(arrayListFB,listViewFB);
        listViewPhone.setAdapter(adapterPhone);
        listViewVK.setAdapter(adapterVK);
        listViewFB.setAdapter(adapterFB);






        tabs.setup();
        loadFriends();


        tabPhone=tabs.newTabSpec("phone");
        tabPhone.setContent(R.id.list_phone);
        tabPhone.setIndicator("АДРЕСНАЯ КНИГА");
        tabs.addTab(tabPhone);

        tabVK = tabs.newTabSpec("vk");
        tabVK.setContent(R.id.list_vk);
        tabVK.setIndicator("ВКОНТАКТЕ");
        tabs.addTab(tabVK);

        tabFB = tabs.newTabSpec("vk");
        tabFB.setContent(R.id.list_fb);
        tabFB.setIndicator("ФЕЙСБУК");
        tabs.addTab( tabFB);

        tabs.setCurrentTabByTag("phone");

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // Toast.makeText(getActivity().getApplicationContext(),"Tab id="+tabId,Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
   private void loadFriends() {
       //Загружаем друзей из телефонной книги
       ContentResolver cr=getActivity().getContentResolver();
       Cursor cursor=cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
       if (cursor.getCount()>0) {
           while (cursor.moveToNext()) {
               int id=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
               String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
               int photo= cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));


               arrayListPhone.add(new Friend(id,name,photo));
           }
       }
        adapterPhone.notifyDataSetChanged();




            VKRequest currentRequest= VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "id, first_name,last_name,photo_50"));
          currentRequest.executeWithListener(new VKRequest.VKRequestListener() {
              @Override
              public void onComplete(VKResponse response) {
                  super.onComplete(response);
                  VKUsersArray usersArray=(VKUsersArray)response.parsedModel;
                  for (VKApiUserFull userFull: usersArray){
                      Friend friend=new Friend(userFull.id,userFull.first_name, userFull.last_name,userFull.photo_50);

                      arrayListVK.add(friend);
                      adapterVK.notifyDataSetChanged();
                  }
              }
              @Override
              public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                  super.attemptFailed(request, attemptNumber, totalAttempts);
                  Log.d("VkDemoApp", "attemptFailed " + request + " " + attemptNumber + " " + totalAttempts);
              }

              @Override
              public void onError(VKError error) {
                  super.onError(error);
                  Log.d("VkDemoApp", "onError: " + error);
              }

              @Override
              public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                  super.onProgress(progressType, bytesLoaded, bytesTotal);
                  Log.d("VkDemoApp", "onProgress " + progressType + " " + bytesLoaded + " " + bytesTotal);
              }
          });

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
        ListView listView;
        public FriendsAdapter (ArrayList<Friend> arrayList,ListView listView){
            this.arrayList=arrayList;
            this.listView=listView;
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


                listView.setVisibility(View.VISIBLE);
                Friend friend=(Friend)getItem(position);

                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_invite_friend, null);

                TextView name = (TextView) convertView.findViewById(R.id.tv_user_name);


                ImageView photo = (ImageView) convertView.findViewById(R.id.im_user_photo);
                Button invite=(Button)convertView.findViewById(R.id.but_invite_add);

                Transformation transformation = new RoundedTransformationBuilder()
                        .borderColor(Color.WHITE)
                        .borderWidthDp(2)
                        .cornerRadiusDp(48)
                        .oval(false)
                        .build();
                if (friend.getPhotoId()!=0){
                    Picasso.with(getActivity()).load(friend.getPhotoId()).transform(transformation).into(photo);
                }else{
                    Picasso.with(getActivity()).load(friend.getPhoto50()).transform(transformation).into(photo);
                }



                name.setText(friend.getName());
                invite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Приглашение отправлено",Toast.LENGTH_LONG).show();
                    }
                });



            }else{
               listView.setVisibility(View.GONE);



            }

            return convertView;
        }
    }
}
