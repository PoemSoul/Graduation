package edu.ntu.graduation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.ntu.graduation.fragment.MainHome;
import edu.ntu.graduation.fragment.MainInformation;
import edu.ntu.graduation.fragment.MainMarket;
import edu.ntu.graduation.fragment.MainOptional;
import edu.ntu.graduation.fragment.MainTrading;

public class MainActivity extends FragmentActivity /*implements View.OnClickListener*/{
    private Context context = MainActivity.this;
//    private Button btnRegister;
    private RadioButton main_home;
    private RadioButton main_market;
    private RadioButton main_optional;
    private RadioButton main_trading;
    private RadioButton main_information;
    private RadioGroup mainRadioGroup;
    Fragment[] fragments={  new MainHome().getFragment(),
            new MainMarket().getFragment(),
            new MainOptional().getFragment(),
            new MainTrading().getFragment(),
            new MainInformation().getFragment()};
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
//        initListener();
        initBottomBar();
        changeMainFragment(0);
    }
    private void initBottomBar() {
        main_home = (RadioButton) findViewById(R.id.radio_button_home);
        main_market = (RadioButton) findViewById(R.id.radio_button_market);
        main_optional = (RadioButton) findViewById(R.id.radio_button_optional);
        main_trading = (RadioButton) findViewById(R.id.radio_button_trading);
        main_information = (RadioButton) findViewById(R.id.radio_button_information);
        RadioButton[] rbs = {main_home, main_market, main_optional, main_trading,main_information};
        for (RadioButton rb : rbs) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            Drawable[] drs = rb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drs[1].getMinimumWidth() / 10, drs[1].getMinimumHeight() / 10);
            //定义一个Rect边界
            drs[1].setBounds(r);
            rb.setCompoundDrawables(null, drs[1], null, null);
        }
    }

    private void initFragment() {
        mainRadioGroup = (RadioGroup)findViewById(R.id.main_home_bottom_bar);
        mainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                if(currentTabIndex !=index) changeMainFragment(index);
                currentTabIndex=index;
            }
        });
    }

    private void changeMainFragment(int index) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.hide(fragments[currentTabIndex]);
        if(!fragments[index].isAdded()){
            transaction.add(R.id.main_container,fragments[index]);
        }
        transaction.show(fragments[index]).commit();
    }

//    private void changeHomeFragment(Fragment fm) {
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//        transaction.replace(R.id.main_home_container, fm);
//        transaction.commit();
//    }


//    private void initView() {
//        btnRegister=(Button)findViewById(R.id.register_btn);
//    }
//
//    private void initListener() {
//        btnRegister.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(view.getId()==R.id.register_btn){
//            ToastCustom.getInstance(context).show("请输入用户名", 3000);
//        }
//    }
}

