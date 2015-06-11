package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.HelpMessage;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpFragment extends Fragment implements View.OnClickListener{
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    private HelpAdapter myAdapter;
    private ArrayList<HelpMessage> msgList;
    ListView listView;
    CardView emptyList;
    Button invite;
    RelativeLayout relLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    TabHost tabs;
    TabHost.TabSpec helpTab, mapTab;
    MapView mapView, mapViewBack;
    GoogleMap map, map_back;
    AlertDialog dialog;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

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

        View v=inflater.inflate(R.layout.fragment_need_help, container, false);
        tabs=(TabHost)v.findViewById(R.id.tabHost);
        msgList=new ArrayList<HelpMessage>();
        relLayout=(RelativeLayout)v.findViewById(R.id.rel_list);
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
       invite.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_friend_need_help, null);
                Button close = (Button) v.findViewById(R.id.but_close);
                Button more_info = (Button) v.findViewById(R.id.but_more_info);
                close.setOnClickListener(NeedHelpFragment.this);
                more_info.setOnClickListener(NeedHelpFragment.this);


                AlertDialog.Builder aBuilder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                        .setView(v);


                dialog = aBuilder.create();

                dialog.show();
            }
        });
        myAdapter=new HelpAdapter();

        listView.setAdapter(myAdapter);

        tabs.setup();

        helpTab=tabs.newTabSpec("tag1");
        helpTab.setContent(R.id.rel_list);

        helpTab.setIndicator("CПИСОК");
        tabs.addTab(helpTab);
        mapTab=tabs.newTabSpec("tag2");
        mapTab.setContent(R.id.map);

        mapTab.setIndicator("КАРТА");

        tabs.addTab(mapTab);

        mapView=(MapView)v.findViewById(R.id.map);
      // mapViewBack=(MapView)v.findViewById(R.id.map_back);
        mapView.onCreate(savedInstanceState);
     //   mapViewBack.onCreate(savedInstanceState);


        MapsInitializer.initialize(getActivity());
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())){
            case ConnectionResult.SUCCESS:

                if (mapView!=null){
                    map=mapView.getMap();
                            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            Toast.makeText(getActivity(), "Карта загрузилась", Toast.LENGTH_LONG).show();
                        }
                    });


                    map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            map.snapshot(new GoogleMap.SnapshotReadyCallback() {


                                @Override
                                public void onSnapshotReady(Bitmap snapshot) {



                                  //  Drawable drawable= new BitmapDrawable(snapshot);

                                  //  relLayout.setBackground(drawable);

                                }
                            });
                        }
                    });


                    initMap();

                    CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(new LatLng(56.00385,92.9148),10.0f);
                    map.animateCamera(cameraUpdate);
                    map.moveCamera(cameraUpdate);
                }

                break;
            case ConnectionResult.SERVICE_MISSING:

                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:

                break;
        }

        return v;
    }

    private void loadHelpMessages(){
        for (int i=0; i<20; i++){
            HelpMessage message=new HelpMessage("ул. Спортивная, д."+i*5,i*100+"м.",i*3+" мин.", "Сломалась "+i+" машин.",i*200+"рублей не хватает.:(","Человеческая особь № "+i,null,null,i%3,56.00385+0.0003*i,92.9148-0.0002*i );
            msgList.add(message);
            myAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    private void initMap(){
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();

                return false;
            }
        });
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getActivity(), marker.getId(), Toast.LENGTH_LONG).show();
                listener.openFullHelpRequest();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.but_more_info:
                dialog.dismiss();
                listener.openFullHelpRequest();

                break;
            case R.id.but_close:
                dialog.dismiss();

                break;
            case R.id.but_invite_friends:
                listener.openInviteFriends();
                Toast.makeText(getActivity(), "Приглашаем друзей", Toast.LENGTH_LONG).show();
                break;


        }

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
                TextView name = (TextView) convertView.findViewById(R.id.tv_title);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);
                final ImageView minimap = (ImageView) convertView.findViewById(R.id.im_minimap);

               map.moveCamera(CameraUpdateFactory.newLatLngZoom(msg.getCoord(),14));
                map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_pin))
                .anchor(0.0f,1.0f)
                .position(msg.getCoord())
                .title(msg.getCaption())
                );

                 /*     map.snapshot(new GoogleMap.SnapshotReadyCallback() {


                          @Override
                          public void onSnapshotReady(Bitmap snapshot) {
                              //Bitmap bitmap;
                             // bitmap = snapshot;
                              int width=snapshot.getWidth();
                              int height=snapshot.getHeight();
                              Log.d(TAG, "Width="+width+" | Height="+height);
                              int x1=(width-120)/2;
                              int y1=(height-120)/2;
                              Bitmap bitmap=Bitmap.createBitmap(snapshot, x1, y1, 120, 120);
                              Drawable drawable= new BitmapDrawable(bitmap);

                              minimap.setBackground(drawable);

                          }
                      });*/
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
