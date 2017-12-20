package com.sc.ui.tax;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlavaryFragment extends Fragment {

    View.OnClickListener parent;

    public SlavaryFragment() {
        // Required empty public constructor
    }
    public void setParent(View.OnClickListener parent){
        this.parent = parent;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slavary, container, false);
    }

}
