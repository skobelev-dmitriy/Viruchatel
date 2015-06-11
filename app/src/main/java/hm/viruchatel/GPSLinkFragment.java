package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import hm.viruchatel.Api.ApplicationInterface;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class GPSLinkFragment extends Fragment {
    ApplicationInterface listener;

public GPSLinkFragment(){

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

        View view=inflater.inflate(R.layout.fragment_gps_link,container,false);
        MaterialEditText gps_num=(MaterialEditText)view.findViewById(R.id.edit_gps_num);


        return view;

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
