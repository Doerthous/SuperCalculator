package com.sc.ui.programmer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.R;

public class HexKbSubFgm1Fragment extends Fragment {
    public static Fragment getInstance(View.OnClickListener parent){
        HexKbSubFgm1Fragment h = new HexKbSubFgm1Fragment();
        h.parent = parent;
        return h;
    }

    private View.OnClickListener parent;
    private View view;
    private HexKbSubFgm1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hex_kb_sub_fgm1, container, false);
        initButton();
        return view;
    }
    private void initButton(){
        view.findViewById(R.id.pgmBtnOr).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnXor).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnAnd).setOnClickListener(parent);
        view.findViewById(R.id.pgmBtnNot).setOnClickListener(parent);
    }
}
