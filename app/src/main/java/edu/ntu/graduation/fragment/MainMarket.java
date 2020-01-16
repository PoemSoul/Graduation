package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ntu.graduation.R;

public class MainMarket extends Fragment {
    private View view;
    private MainMarket fa;

    public MainMarket getFragment() {
        if (fa == null) {
            fa = new MainMarket();
        }
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_market, container, false);
        return view;
    }
}
