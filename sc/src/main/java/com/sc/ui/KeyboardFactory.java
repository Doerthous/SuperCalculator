package com.sc.ui;

import android.support.v4.app.Fragment;
import android.view.View;

import com.sc.ui.programmer.BitKeyboardFragment;
import com.sc.ui.programmer.HexNumberKeyboardFragment;

import com.sc.ui.programmer.HexKbSubFgm1Fragment;
import com.sc.ui.programmer.HexKbSubFgm2Fragment;

/**
 * Created by Doerthous on 2017/6/30.
 */

public class KeyboardFactory {
    public static final String HEX_KEYBOARD = "HEX_KEYBOARD";
    public static final String HEX_SUB_KEYBOARD1 = "HEX_SUB_KEYBOARD1";
    public static final String HEX_SUB_KEYBOARD2 = "HEX_SUB_KEYBOARD2";
    public static final String BIT_KEYBOARD = "BIT_KEYBOARD";
    public static Fragment getKeyboard(String name, View.OnClickListener parent){
        if(HEX_KEYBOARD.equals(name)){
            return HexNumberKeyboardFragment.getInstance(parent);
        } else if(HEX_SUB_KEYBOARD1.equals(name)){
            return HexKbSubFgm1Fragment.getInstance(parent);
        } else if(HEX_SUB_KEYBOARD2.equals(name)){
            return HexKbSubFgm2Fragment.getInstance(parent);
        } else if(BIT_KEYBOARD.equals(name)){
            return BitKeyboardFragment.getInstance(parent);
        }
        return null;
    }
}
