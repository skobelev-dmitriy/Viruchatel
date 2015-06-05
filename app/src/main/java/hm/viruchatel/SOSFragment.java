package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.rengwuxian.materialedittext.MaterialEditText;

import hm.viruchatel.Api.ApplicationInterface;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class SOSFragment extends Fragment {
    MaterialEditText caption;
    MaterialEditText message;
    RadioGroup radioGroup;
    Button send;
    boolean errors;
    ApplicationInterface listener;

public SOSFragment(){

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

        View view=inflater.inflate(R.layout.fragment_sos,container,false);
        caption=(MaterialEditText)view.findViewById(R.id.et_caption);
        message=(MaterialEditText)view.findViewById(R.id.et_message);
        radioGroup=(RadioGroup)view.findViewById(R.id.radio);
        send=(Button)view.findViewById(R.id.but_send_alarm);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAlarm();
            }
        });


        return view;

    }
    private void sendAlarm(){
        checkViews();
        if (!errors ){
            String sCaption=caption.getText().toString();
            String sMessage=message.getText().toString();
            int type=radioGroup.getCheckedRadioButtonId();
            listener.sendAlarm(sCaption,sMessage,type);
        }
    }
    private void checkViews(){
        errors=false;

        checkEmpty(caption);
        checkEmpty(message);



    }
    private void checkEmpty(MaterialEditText v){
        String error="Не заполнено поле!";


        if(TextUtils.isEmpty(v.getText().toString())){
            v.setError(error);
            errors=true;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
