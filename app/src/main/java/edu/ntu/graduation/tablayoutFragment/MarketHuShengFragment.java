package edu.ntu.graduation.tablayoutFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.R;
import edu.ntu.graduation.util.TextBannerView;

public class MarketHuShengFragment extends BaseFragment{
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.main_market_hu_sheng, container, false);
            initDataBanner();
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
    }

    private void initDataBanner(){
        TextBannerView dataBanner = mView.findViewById(R.id.hu_sheng_data_banner);
        List<String> dataList = new ArrayList<>();
        dataList.add(stockData(-46.13)+"A股指数");
        dataList.add(stockData(63.12)+"B股指数");
        dataList.add(stockData(-5.82)+"工业指数");
        dataList.add(stockData(12.37)+"商业指数");
        dataList.add(stockData(-1.95)+"地产指数");
        dataList.add(stockData(-21.76)+"公用指数");
        dataList.add(stockData(6.32)+"综合指数");
        dataBanner.setDatas(dataList);
    }

    private String stockData(double data){
        String str=null;
        if (data>0)
            str="<font color='#ED382B'><big>+"+data+"亿</big></font><br/>";
        else
            str="<font color='#169619'><big>"+data+"亿</big></font><br/>";
        return str;
    }
    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }
    public static MarketHuShengFragment newInstance(String param1) {
        MarketHuShengFragment fragment = new MarketHuShengFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
}
