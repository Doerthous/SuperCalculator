package com.sc.ui.time;


import android.widget.Button;
import com.sc.R;
import com.sc.ui.FragmentBase;

public class NumberKeyboardFragment extends FragmentBase {

    public NumberKeyboardFragment() {
        fragmentId = R.layout.fragment_number_keyboard;
    }

    @Override
    protected void init(){
        int[] id = {
                R.id.button19,R.id.button20,R.id.button21,R.id.button22,
                R.id.button23,R.id.button24,R.id.button25,R.id.button26,
                R.id.button27,R.id.button28,R.id.button29,R.id.button30,
                R.id.button31,R.id.button32,R.id.button3
        };
        for(int i = 0; i < id.length; ++i) {
            Button btn = view.findViewById(id[i]);
            btn.setOnClickListener(parent);
        }
    }
}
