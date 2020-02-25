package edu.ntu.graduation.tablayoutFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.R;

public class MarketAStocksFragment extends BaseFragment{
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private XTabLayout mTableLayout;
    private ViewPager mViewPager;
    private MarketHuShengFragment mMarketHuShengFragment=new MarketHuShengFragment();
    private MarketBanKuaiFragment mMarketBanKuaiFragment=new MarketBanKuaiFragment();
    private MarketKeChuangFragment mMarketKeChuangFragment=new MarketKeChuangFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.main_market_a_stocks, container, false);
            initData();
            initView();
            isPrepared = true;
//        实现懒加载
            lazyLoad();
        }
        //缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        Bundle bundle = getArguments();
        String args = bundle.getString("agrs1");
        mTableLayout = mView.findViewById(R.id.market_a_stocks_tl_tabs);
        mViewPager = mView.findViewById(R.id.market_a_stocks_vp_view);
        mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),getContext(),fragments,titles));
        mTableLayout.setupWithViewPager(mViewPager);//此方法就是让tablayout和ViewPager联动
    }
    private void initData() {
        fragments.add(mMarketHuShengFragment.newInstance("HuSheng"));
        fragments.add(mMarketBanKuaiFragment.newInstance("Bankuai"));
        fragments.add(mMarketKeChuangFragment.newInstance("KeChuangBan"));
        titles.add("沪深");
        titles.add("板块");
        titles.add("科创板");
    }
    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }
    public static MarketAStocksFragment newInstance(String param1) {
        MarketAStocksFragment fragment = new MarketAStocksFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
}
