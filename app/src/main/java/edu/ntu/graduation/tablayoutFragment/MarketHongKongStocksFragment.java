package edu.ntu.graduation.tablayoutFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.scrollablepanel.library.ScrollablePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.ntu.graduation.Adapter.ScrollablePanelAdapter;
import edu.ntu.graduation.R;
import edu.ntu.graduation.model.ColumnTitle;
import edu.ntu.graduation.model.DataInfo;
import edu.ntu.graduation.model.StockInfo;

public class MarketHongKongStocksFragment extends  BaseFragment{
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
            mView = inflater.inflate(R.layout.main_market_hong_kong_stocks, container, false);
            initView();
//            initData();
            final ScrollablePanel scrollablePanel = mView.findViewById(R.id.hong_kong_stocks_scrollable_panel);
            final ScrollablePanelAdapter scrollablePanelAdapter = new ScrollablePanelAdapter();
            generateTestData(scrollablePanelAdapter);
            scrollablePanel.setPanelAdapter(scrollablePanelAdapter);
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

    private void generateTestData(ScrollablePanelAdapter scrollablePanelAdapter) {
        List<StockInfo> stockInfoList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setStockName("股票名称");
            stockInfo.setRoomId(i);
            stockInfo.setStockId("20" + i);
            stockInfoList.add(stockInfo);
        }
        for (int i = 6; i < 30; i++) {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setStockName("股票名称");
            stockInfo.setRoomId(i);
            stockInfo.setStockId("30" + i);
            stockInfoList.add(stockInfo);
        }
        scrollablePanelAdapter.setStockInfoList(stockInfoList);

        List<ColumnTitle> columnTitleList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            ColumnTitle columnTitle = new ColumnTitle();
            String column = "列"+i;
            columnTitle.setColumnTitle(column);
            columnTitleList.add(columnTitle);
        }

        scrollablePanelAdapter.setColumnTitleList(columnTitleList);

        List<List<DataInfo>> dataList = new ArrayList<>();
        Random rand=new Random();
        for (int i = 0; i < 30; i++) {
            List<DataInfo> dataInfoList = new ArrayList<>();
            for (int j = 0; j < 14; j++) {
                DataInfo dataInfo = new DataInfo();
                dataInfo.setData(String.valueOf(rand.nextInt(20)-10));
                dataInfoList.add(dataInfo);
            }
            dataList.add(dataInfoList);
        }
        scrollablePanelAdapter.setDataList(dataList);
    }


    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }
    public static MarketHongKongStocksFragment newInstance(String param1) {
        MarketHongKongStocksFragment fragment = new MarketHongKongStocksFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
}
