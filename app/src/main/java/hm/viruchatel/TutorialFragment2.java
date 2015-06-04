package hm.viruchatel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TutorialFragment2 extends Fragment {
    private int page;




    public static TutorialFragment2 newInstance(int page) {
        TutorialFragment2 fragment = new TutorialFragment2();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.about_app2, container, false);
        Button butNext=(Button)view.findViewById(R.id.butt_next);
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              Intent  intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }



}
