package edu.ntu.graduation.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ntu.graduation.R;
import edu.ntu.graduation.model.RecommendList;

public class RecommendListAdapter extends ArrayAdapter<RecommendList> {
    private int resourceId;
    public RecommendListAdapter(@NonNull Context context, int resource, @NonNull List<RecommendList> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RecommendList recommendList=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.recommendListTitle=(TextView)view.findViewById(R.id.recommend_list_title);
            viewHolder.recommendListAuthor=(TextView)view.findViewById(R.id.recommend_list_author);
            viewHolder.recommendListDate=(TextView)view.findViewById(R.id.recommend_list_date);
            viewHolder.recommendListImage=(ImageView)view.findViewById(R.id.recommend_list_image);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.recommendListTitle.setText(recommendList.getTitle());
        viewHolder.recommendListAuthor.setText(recommendList.getAuthor());
        viewHolder.recommendListDate.setText(recommendList.getDate());
        viewHolder.recommendListImage.setImageResource(recommendList.getImageId());
        return view;
    }

    class ViewHolder{
        TextView recommendListTitle;
        TextView recommendListAuthor;
        TextView recommendListDate;
        ImageView recommendListImage;
    }
}
