package edu.ntu.graduation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.scrollablepanel.library.PanelAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.ntu.graduation.R;
import edu.ntu.graduation.model.ColumnTitle;
import edu.ntu.graduation.model.DataInfo;
import edu.ntu.graduation.model.StockInfo;

/**
 * Created by kelin on 16-11-18.
 */

public class ScrollablePanelAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 4;
    private static final int STOCK_TYPE = 0;
    private static final int COLUMN_TITLE_TYPE = 1;
    private static final int DATA_TYPE = 2;

    private List<StockInfo> stockInfoList =new ArrayList<>();
    private List<ColumnTitle> columnTitleList = new ArrayList<>();
    private List<List<DataInfo>> dataList =new ArrayList<>();


    @Override
    public int getRowCount() {
        return stockInfoList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return columnTitleList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case COLUMN_TITLE_TYPE:
                setColumnTitleView(column, (ColumnTitleViewHolder) holder);
                break;
            case STOCK_TYPE:
                setStockView(row, (StockViewHolder) holder);
                break;
            case DATA_TYPE:
                setDataView(row, column, (DataViewHolder) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setDataView(row, column, (DataViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return STOCK_TYPE;
        }
        if (row == 0) {
            return COLUMN_TITLE_TYPE;
        }
        return DATA_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COLUMN_TITLE_TYPE:
                return new ColumnTitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_column_title, parent, false));
            case STOCK_TYPE:
                return new StockViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_stock_info, parent, false));
            case DATA_TYPE:
                return new DataViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_data_info, parent, false));
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_data_info, parent, false));
    }


    private void setColumnTitleView(int pos, ColumnTitleViewHolder viewHolder) {
        ColumnTitle columnTitle = columnTitleList.get(pos - 1);
        if (columnTitle != null && pos > 0) {
            viewHolder.columnTitleTextView.setText(columnTitle.getColumnTitle());
        }
    }

    private void setStockView(int pos, StockViewHolder viewHolder) {
        StockInfo stockInfo = stockInfoList.get(pos - 1);
        if (stockInfo != null && pos > 0) {
            viewHolder.stockNameTextView.setText(stockInfo.getStockName());
            viewHolder.stockIdTextView.setText(stockInfo.getStockId());
        }
    }

    private void setDataView(final int row, final int column, DataViewHolder viewHolder) {
        final DataInfo dataInfo = dataList.get(row - 1).get(column - 1);
        if (dataInfo != null) {
            if (Integer.valueOf(dataInfo.getData())<0) {
                viewHolder.dataView.setTextColor(0xFF169619);
                viewHolder.dataView.setText(dataInfo.getData());
            }
            else {
                viewHolder.dataView.setTextColor(0xFFED382B);
                viewHolder.dataView.setText("+"+dataInfo.getData());
            }
        }
    }


    private static class ColumnTitleViewHolder extends RecyclerView.ViewHolder {
        public TextView columnTitleTextView;

        public ColumnTitleViewHolder(View itemView) {
            super(itemView);
            this.columnTitleTextView = (TextView) itemView.findViewById(R.id.column_title);
        }

    }

    private static class StockViewHolder extends RecyclerView.ViewHolder {
        public TextView stockNameTextView;
        public TextView stockIdTextView;

        public StockViewHolder(View view) {
            super(view);
            this.stockNameTextView = (TextView) view.findViewById(R.id.stock_name);
            this.stockIdTextView = (TextView) view.findViewById(R.id.stock_id);
        }
    }

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView dataView;
        public View view;

        public DataViewHolder(View view) {
            super(view);
            this.view = view;
            this.dataView = (TextView) view.findViewById(R.id.stock_data);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }


    public void setStockInfoList(List<StockInfo> stockInfoList) {
        this.stockInfoList = stockInfoList;
    }

    public void setColumnTitleList(List<ColumnTitle> columnTitleList) {
        this.columnTitleList = columnTitleList;
    }

    public void setDataList(List<List<DataInfo>> dataList) {
        this.dataList = dataList;
    }
}
