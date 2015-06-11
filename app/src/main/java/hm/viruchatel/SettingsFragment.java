package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import hm.viruchatel.Api.ApiInterface;
import hm.viruchatel.Api.ApplicationInterface;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    TableRow user_photo;
    TextView user_data, change_pass, gps_num,but_link_fb, but_link_vk;
    LinearLayout but_gps;
    ImageView photo;
    AlertDialog  dialog;


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
        View v=inflater.inflate(R.layout.fragment_settings, container, false);
         user_photo=(TableRow)v.findViewById(R.id.but_user_photo);
        user_data=(TextView)v.findViewById(R.id.but_user_info);

        change_pass=(TextView)v.findViewById(R.id.but_chanche_pass);
        gps_num=(TextView)v.findViewById(R.id.tv_gps_number);
        but_link_fb=(TextView)v.findViewById(R.id.but_link_fb);
        but_link_vk=(TextView)v.findViewById(R.id.but_link_vk);

         but_gps=(LinearLayout)v.findViewById(R.id.ll_gps_num);
        photo=(ImageView)v.findViewById(R.id.im_photo);

        user_photo.setOnClickListener(this);
        user_data.setOnClickListener(this);
        change_pass.setOnClickListener(this);
        but_gps.setOnClickListener(this);
        but_link_fb.setOnClickListener(this);
        but_link_vk.setOnClickListener(this);
        Log.d(TAG, "Fr_ onCreateView");

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_user_photo:
                showPhotoDialog();

                break;
            case R.id.but_user_info:
                listener.openUserInfo();


                break;
            case R.id.but_chanche_pass:
                listener.openChangePass();
                break;
            case R.id.but_link_fb:

                break;
            case R.id.but_link_vk:

                break;
            case R.id.ll_gps_num:
                listener.openLinkGPS();

                break;
            case R.id.but_close:
                dialog.dismiss();

                break;
            case R.id.but_take_photo:
                dialog.dismiss();
                listener.takePhoto();

                break;
            case R.id.but_choose_image:
                dialog.dismiss();
                listener.openImage();

                break;

        }
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
    private  void showPhotoDialog(){
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_new_photo, null);
        ImageView cancel=(ImageView)v.findViewById(R.id.but_close);
        TableRow take_photo=(TableRow)v.findViewById(R.id.but_take_photo);
        TableRow choose_image=(TableRow)v.findViewById(R.id.but_choose_image);

        cancel.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        choose_image.setOnClickListener(this);


        AlertDialog.Builder aBuilder=new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setView(v);



      dialog= aBuilder.create();
        dialog.show();
    }
}
