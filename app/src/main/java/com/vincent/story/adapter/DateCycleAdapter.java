package com.vincent.story.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vincent.story.DataBean;
import com.vincent.story.R;

import java.util.List;

/**
 * 时间周期适配器
 */
public class DateCycleAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    private DateListener listener;


    public void setListener(DateListener listener) {
        this.listener = listener;
    }

    public DateCycleAdapter(Context context, int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.tv_data, item.getData());
        if (item.isSelected())
            ((TextView) helper.getView(R.id.tv_data)).setTextColor(ContextCompat.getColor(mContext, R.color.color_6384FF));
        else
            ((TextView) helper.getView(R.id.tv_data)).setTextColor(ContextCompat.getColor(mContext, R.color.white));

        helper.getView(R.id.tv_data).setOnClickListener(l -> {
//            initData();
            listener.chooseDate(item.getData(), item.getPosition());
        });

    }

//
//    public void initData() {
////        List<DataBean> list = getData();
//        for (DataBean bean : getData()) {
//            bean.setSelected(false);
//        }
//    }

    public interface DateListener {
        void chooseDate(String period, int position);
    }

}
