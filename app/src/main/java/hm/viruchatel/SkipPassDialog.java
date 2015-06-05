package hm.viruchatel;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Дмитрий on 03.06.2015.
 */
public   class SkipPassDialog  extends DialogFragment {
    int mNum;
    static SkipPassDialog newInstance(int num){
        SkipPassDialog dialog= new SkipPassDialog();
        Bundle args = new Bundle();
        args.putInt("num", num);
        dialog.setArguments(args);
        return dialog;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_skip_pass, container, false);

        EditText email=(EditText)v.findViewById(R.id.ed_new_email);
        Button cancel=(Button)v.findViewById(R.id.but_cancel);
        Button send=(Button)v.findViewById(R.id.but_send);
        View tv = v.findViewById(R.id.text);

        // Watch for button clicks.
      /*  Button button = (Button)v.findViewById(R.id.show);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                ((FragmentDialog)getActivity()).showDialog();
            }
        });*/

        return v;
    }
}
