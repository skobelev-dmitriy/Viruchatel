package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hm.viruchatel.Api.ApplicationInterface;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class GPSFragment extends Fragment {
    ApplicationInterface listener;

public GPSFragment(){

}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ApplicationInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ApplicationInterface");

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_gps,container,false);

        Button order=(Button)view.findViewById(R.id.but_order);
        Button link=(Button)view.findViewById(R.id.but_link);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.orderGPS();
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openLinkGPS();
            }
        });

        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
