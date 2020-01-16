package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.ntu.graduation.R;

public class MainHome extends Fragment{
    private View view;
    private MainHome fa;
    private RadioGroup homeRadioGroup;
    Fragment[] fragments={  new MainHomeStock().getFragment(), new MainHomeMoneyManagement().getFragment()};
    private int index;
    private int currentTabIndex;

    public MainHome getFragment() {
        if (fa == null) {
            fa = new MainHome();
        }
        return fa;
    }


    private void initView() {
        homeRadioGroup = (RadioGroup)view.findViewById(R.id.main_home_radio_group);
        homeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int arg1) {
                //遍历RadioGroup 里面所有的子控件。
                for (int i = 0; i < group.getChildCount(); i++) {
                    //获取到指定位置的RadioButton
                    RadioButton rb = (RadioButton)group.getChildAt(i);
                    //如果被选中
                    if (rb.isChecked()) {
                        index=i;
                        break;
                    }
                }
                if(currentTabIndex !=index) changeHomeFragment(index);
                currentTabIndex=index;
            }
        });
    }

    private void changeHomeFragment(int index) {
        FragmentManager supportFragmentManager = getFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.hide(fragments[currentTabIndex]);
        if(!fragments[index].isAdded()){
            transaction.add(R.id.main_home_container,fragments[index]);
        }
        transaction.show(fragments[index]).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_home, container, false);
        initView();
        changeHomeFragment(0);
        return view;
    }
}
