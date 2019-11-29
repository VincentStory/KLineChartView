package com.vincent.story;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.fujianlian.klinechart.KLineChartAdapter;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.draw.Status;
import com.vincent.story.adapter.DateCycleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.fujianlian.klinechart.utils.Constants.CANDLE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.LINE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_ITEM;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_TYPE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private KLineChartView kLineChartView;

    private RecyclerView mRecyclerView;

    private TextView mTvRang, mTvLine, mTvCandle;

    private List<KLineEntity> datas;//模拟历史数据

    private List<KLineEntity> newDatas = new ArrayList<>();//模拟最新数据

    protected KLineChartAdapter adapter = new KLineChartAdapter();

    protected DateCycleAdapter dateCycleAdapter;

    private int position = 6;//默认关闭子view

    protected List<DataBean> dataBeanList = new ArrayList<>();//时间周期集合

    protected String[] cycleDate = new String[]{"1m", "5m", "15m", "30m", "60m", "1D", "1W", "1M"};

    private Timer timer = new Timer();//当前时间刷新

    private int mSecond = 0;//秒数


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        kLineChartView = findViewById(R.id.kLineChartView);
        mRecyclerView = findViewById(R.id.recycerView);
        mTvRang = findViewById(R.id.tv_rang);
        mTvLine = findViewById(R.id.tv_line);
        mTvCandle = findViewById(R.id.tv_candle);

        kLineChartView.setAdapter(adapter);
        kLineChartView.justShowLoading();

        mTvRang.setOnClickListener(this);
        mTvLine.setOnClickListener(this);
        mTvCandle.setOnClickListener(this);

        datas = DataRequest.getALL(MainActivity.this).subList(0, 500);
        newDatas.addAll(datas);
        Log.i("data==", datas.toString());

        adapter.addFooterData(datas);
        adapter.notifyDataSetChanged();

        if (kLineChartView != null) {
            kLineChartView.startAnimation();
            kLineChartView.refreshEnd();
            kLineChartView.hideChildDraw();
        }

        setChildDraw(6);

        initDateView(0);


        refreshData();


    }


    /**
     * 每秒刷新一次数据 ，每5秒新增一条数据
     */
    private void refreshData() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mSecond + RANG_ITEM + 1 >= newDatas.size())
                    return;
                KLineEntity kLineEntity = newDatas.get(newDatas.size() - mSecond - RANG_ITEM - 1);
                mSecond++;
                if (mSecond % 5 == 0) {
                    datas.add(datas.size() - RANG_ITEM, kLineEntity);
                    adapter.addData(kLineEntity, 0, "2");
                } else {
                    adapter.replaceData(kLineEntity, 0, "2");
                    datas.set(datas.size() - RANG_ITEM - 1, kLineEntity);
                }
//                adapter.addFooterData(datas);
                adapter.notifyDataSetChanged();

            }
        }, 1000, 1000);
    }

    private void initDateView(int choosePosition) {
//        mPeriods = getPeriods(currentType);
//        if (choosePosition >= mPeriods.length)
//            return;
//        period = mPeriods[choosePosition];
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 8);

        dataBeanList.clear();
        for (int i = 0; i < cycleDate.length; i++) {
            DataBean dataBean = new DataBean();
            if (i == choosePosition) dataBean.setSelected(true);
            dataBean.setData(cycleDate[i]);
            dataBean.setPosition(i);
            dataBeanList.add(dataBean);
        }

        // 设置布局管理器
        mRecyclerView.setLayoutManager(manager);

        dateCycleAdapter = new DateCycleAdapter(MainActivity.this, R.layout.item_tv_layout, dataBeanList);
        dateCycleAdapter.setListener((period, position) ->
        {
//            setSymbolAndPeriod(getCurrencyInfo(), position);
        });

        mRecyclerView.setAdapter(dateCycleAdapter);

    }

    /**
     * 显示底部k线view和MA BOLL SAR
     *
     * @param position
     */
    public void setChildDraw(int position) {
        this.position = position;
        if (kLineChartView != null) {
            if (position == 0) {
                kLineChartView.changeMainDrawType(Status.MA);
            } else if (position == 1) {
                kLineChartView.changeMainDrawType(Status.BOLL);
            } else if (position != 6) {
                position -= 2;
                kLineChartView.setChildDraw(position);
            } else {
                hideChildView();
            }


        }
    }

    public void hideChildView() {
        if (kLineChartView != null) {
            kLineChartView.hideChildDraw();
            kLineChartView.changeMainDrawType(Status.NONE);
        }
    }

    public void setKlineType(String klineType) {
//        lineType = type;
        if (kLineChartView != null) {
            kLineChartView.setKlineType(klineType);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rang:
                setKlineType(RANG_TYPE);
                break;
            case R.id.tv_line:
                setKlineType(LINE_TYPE);
                break;
            case R.id.tv_candle:
                setKlineType(CANDLE_TYPE);
                break;
        }
    }
}
