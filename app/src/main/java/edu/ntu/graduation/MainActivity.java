package edu.ntu.graduation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.ntu.graduation.fragment.MainHome;
import edu.ntu.graduation.fragment.MainTrading;
import edu.ntu.graduation.fragment.MainMarket;
import edu.ntu.graduation.fragment.MainOptional;
import edu.ntu.graduation.fragment.MainCommunity;

public class MainActivity extends FragmentActivity /*implements View.OnClickListener*/{
    private Context context = MainActivity.this;
//    private Button btnRegister;
    private RadioButton main_home;
    private RadioButton main_market;
    private RadioButton main_optional;
    private RadioButton main_community;
    private RadioButton main_trading;
    private RadioGroup mainRadioGroup;
    Fragment[] fragments={  new MainHome().getFragment(),
            new MainMarket().getFragment(),
            new MainOptional().getFragment(),
            new MainCommunity().getFragment(),
            new MainTrading().getFragment()};
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initButton();
        initFragment();
        initBottomBar();
        changeMainFragment(0);
    }

    /**
     * 新闻正文Button初始化代码，非activity_main中代码
     */

    private void initButton() {
        RadioButton newsPraised = findViewById(R.id.news_body_praised_btn);
        Button weChat = findViewById(R.id.news_body_wechat_btn);
        Button circleOfFriends = findViewById(R.id.news_body_circle_of_friends_btn);
        RadioButton commentsPraised = findViewById(R.id.news_body_comments_praised);
        Drawable newsPraisedImage=getResources().getDrawable(R.drawable.praised_image_selector);
        Drawable weChatImage=getResources().getDrawable(R.drawable.wechat);
        Drawable circleOfFriendsImage=getResources().getDrawable(R.drawable.circle_of_friends);
        Drawable commentsPraisedImage=getResources().getDrawable(R.drawable.praised_image_selector);
        newsPraisedImage.setBounds(0,0,60,60);
        weChatImage.setBounds(0,0,60,60);
        circleOfFriendsImage.setBounds(0,0,60,60);
        commentsPraisedImage.setBounds(0,0,60,60);
        newsPraised.setCompoundDrawables(newsPraisedImage,null,null,null);
        weChat.setCompoundDrawables(weChatImage,null,null,null);
        circleOfFriends.setCompoundDrawables(circleOfFriendsImage,null,null,null);
        commentsPraised.setCompoundDrawables(null,null,commentsPraisedImage,null);
    }

    private void initBottomBar() {
        main_home = (RadioButton) findViewById(R.id.radio_button_home);
        main_market = (RadioButton) findViewById(R.id.radio_button_market);
        main_optional = (RadioButton) findViewById(R.id.radio_button_optional);
        main_community = (RadioButton) findViewById(R.id.radio_button_community);
        main_trading = (RadioButton) findViewById(R.id.radio_button_trading);
        RadioButton[] rbs = {main_home, main_market, main_optional, main_community,main_trading};
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


}

