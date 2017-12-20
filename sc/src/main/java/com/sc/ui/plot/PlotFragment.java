package com.sc.ui.plot;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.R;
import com.sc.calculate.plot.Plot;
import com.sc.datastructrue.records.PlotCalResult;
import com.sc.ui.FragmentBase;
import com.sc.utity.Keyboard;
import com.sc.utity.UIUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlotFragment extends FragmentBase {

    public PlotFragment() {
        // Required empty public constructor
        fragmentId = R.layout.fragment_plot;
    }

    private PaintBoard pb;
    private TextView expr;
    private TextView xmin;
    private TextView xmax;
    private TextView ymin;
    private TextView ymax;
    private TextView unit;
    private Plot plot = new Plot();

    @Override
    public void init() {
        view.findViewById(R.id.plotBtnZoomIn).setOnClickListener(this);
        view.findViewById(R.id.plotBtnZoomOut).setOnClickListener(this);
        view.findViewById(R.id.plotBtnClear).setOnClickListener(this);
        view.findViewById(R.id.plotBtnReset).setOnClickListener(this);
        view.findViewById(R.id.plotBtnPlot).setOnClickListener(this);
        view.findViewById(R.id.plotBtn0).setOnClickListener(this);
        view.findViewById(R.id.plotBtn1).setOnClickListener(this);
        view.findViewById(R.id.plotBtn2).setOnClickListener(this);
        view.findViewById(R.id.plotBtn3).setOnClickListener(this);
        view.findViewById(R.id.plotBtn4).setOnClickListener(this);
        view.findViewById(R.id.plotBtn5).setOnClickListener(this);
        view.findViewById(R.id.plotBtn6).setOnClickListener(this);
        view.findViewById(R.id.plotBtn7).setOnClickListener(this);
        view.findViewById(R.id.plotBtn8).setOnClickListener(this);
        view.findViewById(R.id.plotBtn9).setOnClickListener(this);
        view.findViewById(R.id.plotBtn10).setOnClickListener(this);
        view.findViewById(R.id.plotBtn11).setOnClickListener(this);
        view.findViewById(R.id.plotBtn12).setOnClickListener(this);
        view.findViewById(R.id.plotBtn13).setOnClickListener(this);
        view.findViewById(R.id.plotBtn14).setOnClickListener(this);
        view.findViewById(R.id.plotBtn15).setOnClickListener(this);
        view.findViewById(R.id.plotBtn16).setOnClickListener(this);
        view.findViewById(R.id.plotBtn17).setOnClickListener(this);
        view.findViewById(R.id.plotBtn18).setOnClickListener(this);
        view.findViewById(R.id.plotBtn19).setOnClickListener(this);
        view.findViewById(R.id.plotBtn20).setOnClickListener(this);
        view.findViewById(R.id.plotBtn21).setOnClickListener(this);
        view.findViewById(R.id.plotBtn22).setOnClickListener(this);
        view.findViewById(R.id.plotBtn23).setOnClickListener(this);
        view.findViewById(R.id.plotBtn24).setOnClickListener(this);
        view.findViewById(R.id.plotBtn25).setOnClickListener(this);
        view.findViewById(R.id.plotBtn26).setOnClickListener(this);
        view.findViewById(R.id.plotBtn27).setOnClickListener(this);
        view.findViewById(R.id.plotBtn28).setOnClickListener(this);
        //
        UIUtils.autoScrollWithData((HorizontalScrollView) view.findViewById(R.id.plotScrv), View.FOCUS_RIGHT);
        //
        pb = view.findViewById(R.id.plotPb);
        pb.setOnTouchListener(this);
        //
        expr = view.findViewById(R.id.plotTvExpr);
        xmin = view.findViewById(R.id.plotTvXmin);
        xmax = view.findViewById(R.id.plotTvXmax);
        ymin = view.findViewById(R.id.plotTvYmin);
        ymax = view.findViewById(R.id.plotTvYmax);
        unit = view.findViewById(R.id.plotTvUnit);
        //
        pb.post(new Runnable() {
            @Override
            public void run() {
                pb.init();
                updateAxisRegion();
            }
        });
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.plotBtnZoomIn:
                pb.zoomIn();
                plot();
                break;
            case R.id.plotBtnZoomOut:
                pb.zoomOut();
                plot();
                break;
            case R.id.plotBtnPlot:
                plot();
                break;
            case R.id.plotBtnClear:
                pb.clear();
                break;
            case R.id.plotBtnReset:
                pb.toOrigin();
                plot();
                break;
            default:
                String tag = (String) view.getTag();
                if(Keyboard.is(Keyboard.CLR, tag)){
                    plot.input(Keyboard.CLR);
                } else if(Keyboard.is(Keyboard.NB, tag)){
                    plot.input(Keyboard.NB);
                }
                else {
                    plot.input(((Button) view).getText().toString());
                }
        }
        update();
    }

    private void plot(){
        plot.setXMin(pb.getXMin());
        plot.setXMax(pb.getXMax());
        plot.setYMin(pb.getYMin());
        plot.setYMax(pb.getYMax());
        plot.input(Keyboard.EQU);
        PlotCalResult op = plot.output();
        if(op.getException().length() > 0){
            Toast.makeText(getContext(), op.getException(), Toast.LENGTH_SHORT).show();
        } else {
            pb.plot(op.getX(), op.getY());
        }
    }
    private void update(){
        //plot.output();
        pb.update();
        expr.setText(plot.output().getEpxr());
        updateAxisRegion();
    }
    private void updateAxisRegion() {
        xmin.setText(String.valueOf(pb.getXMin()));
        xmax.setText(String.valueOf(pb.getXMax()));
        ymin.setText(String.valueOf(pb.getYMin()));
        ymax.setText(String.valueOf(pb.getYMax()));
        unit.setText(String.valueOf(1/pb.getScale()));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        updateAxisRegion();
        plot();
        return true;
    }

}

