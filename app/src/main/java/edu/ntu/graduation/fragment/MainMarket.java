package edu.ntu.graduation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.R;
import edu.ntu.graduation.tablayoutFragment.MarketAStocksFragment;
import edu.ntu.graduation.tablayoutFragment.MarketHongKongStocksFragment;
import edu.ntu.graduation.tablayoutFragment.MyPagerAdapter;

public class MainMarket extends Fragment {
    private View view;
    private MainMarket fa;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private XTabLayout mTableLayout;
    private ViewPager mViewPager;
    private MarketAStocksFragment mMarketAStocksFragment=new MarketAStocksFragment();
    private MarketHongKongStocksFragment mMarketHongKongStocksFragment=new MarketHongKongStocksFragment();

    public MainMarket getFragment() {
        if (fa == null) {
            fa = new MainMarket();
        }
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_market, container, false);
        initData();
        initView();
        return view;
    }
    private void initView() {
        mTableLayout = view.findViewById(R.id.market_tl_tabs);
        mViewPager = view.findViewById(R.id.market_vp_view);
        mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),getContext(),fragments,titles));
        mTableLayout.setupWithViewPager(mViewPager);//此方法就是让tablayout和ViewPager联动

    }

    private void initData() {
        fragments.add(mMarketAStocksFragment.newInstance("A Stocks"));
        fragments.add(mMarketHongKongStocksFragment.newInstance("HongKong Stocks"));
        titles.add("A股");
        titles.add("港股");
    }
}
