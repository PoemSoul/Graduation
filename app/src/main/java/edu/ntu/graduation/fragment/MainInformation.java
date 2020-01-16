package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ntu.graduation.R;

public class MainInformation extends Fragment {
    private View view;
    private MainInformation fa;

    public MainInformation getFragment() {
        if (fa == null) {
            fa = new MainInformation();
        }
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_information, container, false);
        return view;
    }
}
