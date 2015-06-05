package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hm.viruchatel.Api.ApplicationInterface;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpFragment extends Fragment implements View.OnClickListener{
    public static final String TAG ="myLogs";
    ApplicationInterface listener;

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


        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
