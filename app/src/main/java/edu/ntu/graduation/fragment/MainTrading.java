package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ntu.graduation.R;

public class MainTrading extends Fragment {
    private View view;
    private MainTrading fa;

    public MainTrading getFragment() {
        if (fa == null) {
            fa = new MainTrading();
        }
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_trading, container, false);
        return view;
    }
}
