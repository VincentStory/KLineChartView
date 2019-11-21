package com.github.fujianlian.klinechart;

import java.util.ArrayList;
import java.util.List;

import static com.github.fujianlian.klinechart.utils.Constants.RANG_ITEM;

/**
 * 数据适配器
 * Created by tifezh on 2016/6/18.
 */
public class KLineChartAdapter extends BaseKLineChartAdapter {

    private List<KLineEntity> datas = new ArrayList<>();
//    private List<String> dates = new ArrayList<>();


    public KLineChartAdapter() {

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < datas.size())
            return datas.get(position);
        else return datas.get(datas.size() - 1);
    }


    public List<KLineEntity> getDatas() {
        return datas;
    }

    @Override
    public String getDate(int position) {
        if (position < datas.size())
            return datas.get(position).Date;
        else return datas.get(datas.size() - 1).Date;


    }

    @Override
    public String getTimeStamp(int position) {
        if (position < datas.size())
            return datas.get(position).timestamp;
        else return datas.get(datas.size() - 1).timestamp;
    }


    /**
     * 向头部添加数据
     */
    public void addHeaderData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.clear();
            datas.addAll(data);
        }
    }

    /**
     * 向尾部添加数据
     */
    public void addFooterData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.clear();
            datas.addAll(data);
        }
    }


//    public void addDates(List<String> dates) {
//        if (dates != null && !dates.isEmpty()) {
//            this.dates.clear();
//            this.dates.addAll(dates);
//        }
//    }

    public void addData(KLineEntity entity, int choosePosition, String type) {
        if (datas.size() < RANG_ITEM)
            return;
        datas.add(datas.size() - RANG_ITEM, entity);
        DataHelper.calculateEndData(datas, choosePosition, type, entity.getOpenPrice());
        DataHelper.calculate(datas);

    }

    public void replaceData(KLineEntity entity, int choosePosition, String type) {
        if (datas.size() > 0) {
            datas.set(datas.size() - RANG_ITEM-1, entity);
        }
        DataHelper.calculateEndData(datas, choosePosition, type, entity.getOpenPrice());
        DataHelper.calculate(datas);
    }


    public KLineEntity getData() {

        if (datas.size() > 0)
            return datas.get(0);
        else return new KLineEntity();
    }

    /**
     * 改变某个点的值
     *
     * @param position 索引值
     */
    public void changeItem(int position, KLineEntity data) {
        datas.set(position, data);
        notifyDataSetChanged();
    }

    /**
     * 数据清除
     */
    public void clearData() {
        datas.clear();
        notifyDataSetChanged();
    }
}
