package com.sc.ui.programmer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.R;

public class HexKbSubFgm2Fragment extends Fragment {
    public static Fragment getInstance(View.OnClickListener parent){
        HexKbSubFgm2Fragment h = new HexKbSubFgm2Fragment();
        h.parent = parent;
        return h;
    }

    private View.OnClickListener parent;
    private View view;
    private HexKbSubFgm2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hex_kb_sub_fgm2, container, false);
        initButton();
        return view;
    }
    private void initButton(){
        view.findViewById(R.id.pgmBtnRoL).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnRoR).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnLsh).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnRsh).setOnClickListener(parent);
    }
}
