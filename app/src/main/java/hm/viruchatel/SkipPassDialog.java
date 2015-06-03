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
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
     /*   switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }*/
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MaterialDesignLibraryTheme);
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
