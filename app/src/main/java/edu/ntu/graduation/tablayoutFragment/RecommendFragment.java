package edu.ntu.graduation.tablayoutFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.Adapter.RecommendListAdapter;
import edu.ntu.graduation.R;
import edu.ntu.graduation.model.RecommendList;


public class RecommendFragment extends BaseFragment {
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private List<RecommendList> recommendLists=new ArrayList<RecommendList>();
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            // 需要inflate一个布局文件 填充Fragment
            mView = inflater.inflate(R.layout.main_home_stock_recommend_fragment, container, false);
            initView();
            initRecommendList();
            RecommendListAdapter adapter=new RecommendListAdapter(getActivity(),R.layout.main_home_stock_recommend_list,recommendLists);
            listView=(ListView)mView.findViewById(R.id.recommend_list);
            listView.setAdapter(adapter);
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
    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }
    public static RecommendFragment newInstance(String param1) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * 初始化ListView
     */
    private void initRecommendList(){
        RecommendList news1=new RecommendList();
        news1.setTitle("News1");
        news1.setAuthor("Author1");
        news1.setDate("Date1");
        news1.setImageId(R.drawable.img1);
        RecommendList news2=new RecommendList();
        news2.setTitle("News2");
        news2.setAuthor("Author2");
        news2.setDate("Date2");
        news2.setImageId(R.drawable.img1);
        RecommendList news3=new RecommendList();
        news3.setTitle("News3");
        news3.setAuthor("Author3");
        news3.setDate("Date3");
        news3.setImageId(R.drawable.img1);
        RecommendList news4=new RecommendList();
        news4.setTitle("News4");
        news4.setAuthor("Author4");
        news4.setDate("Date4");
        news4.setImageId(R.drawable.img1);
        RecommendList news5=new RecommendList();
        news5.setTitle("News5");
        news5.setAuthor("Author5");
        news5.setDate("Date5");
        news5.setImageId(R.drawable.img1);
        RecommendList news6=new RecommendList();
        news6.setTitle("News6");
        news6.setAuthor("Author6");
        news6.setDate("Date6");
        news6.setImageId(R.drawable.img1);
        RecommendList news7=new RecommendList();
        news7.setTitle("News7");
        news7.setAuthor("Author7");
        news7.setDate("Date7");
        news7.setImageId(R.drawable.img1);
        recommendLists.add(news1);
        recommendLists.add(news2);
        recommendLists.add(news3);
        recommendLists.add(news4);
        recommendLists.add(news5);
        recommendLists.add(news6);
        recommendLists.add(news7);
    }
}
