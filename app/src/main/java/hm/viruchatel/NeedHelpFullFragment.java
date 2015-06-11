package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.HelpMessage;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpFullFragment extends Fragment implements View.OnClickListener{
    public static final String TAG ="myLogs";
    ApplicationInterface listener;





    MapView mapView;
    GoogleMap map;



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

        View v=inflater.inflate(R.layout.fragment_need_help_full, container, false);













        MapsInitializer.initialize(getActivity());
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())){
            case ConnectionResult.SUCCESS:
                mapView=(MapView)v.findViewById(R.id.map);
                mapView.onCreate( savedInstanceState);
                if (mapView!=null){
                    map=mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(true);
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
    private void loadHelpMessage(){


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

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){


        }

    }

}
