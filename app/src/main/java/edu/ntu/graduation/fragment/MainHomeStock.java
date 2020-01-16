package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.R;
import edu.ntu.graduation.tablayoutFragment.CommunityFragment;
import edu.ntu.graduation.tablayoutFragment.MyPagerAdapter;
import edu.ntu.graduation.tablayoutFragment.NewsFlashFragment;
import edu.ntu.graduation.tablayoutFragment.QuotationFragment;
import edu.ntu.graduation.tablayoutFragment.RecommendFragment;

public class MainHomeStock extends Fragment{
    private View view;
    private MainHomeStock  fa;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private RecommendFragment mRecommendFragment=new RecommendFragment();
    private NewsFlashFragment mNewsFlashFragment=new NewsFlashFragment();
    private CommunityFragment mCommunityFragment=new CommunityFragment();
    private QuotationFragment mQuotationFragment=new QuotationFragment();

    public MainHomeStock  getFragment() {
        if (fa == null) {
            fa = new MainHomeStock ();
        }
        return fa;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_home_stock, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mTableLayout = view.findViewById(R.id.tl_tabs);
        mViewPager = view.findViewById(R.id.vp_view);
        mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),getContext(),fragments,titles));
        mTableLayout.setupWithViewPager(mViewPager);//此方法就是让tablayout和ViewPager联动

    }

    private void initData() {
        fragments.add(mRecommendFragment.newInstance("recommend"));
        fragments.add(mNewsFlashFragment.newInstance("news flash"));
        fragments.add(mCommunityFragment.newInstance("community"));
        fragments.add(mQuotationFragment.newInstance("quotation"));
        titles.add("推荐");
        titles.add("快讯");
        titles.add("社区");
        titles.add("盘面");
    }
}
