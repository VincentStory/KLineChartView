package com.vincent.story;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.fujianlian.klinechart.KLineChartAdapter;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.draw.Status;
import com.github.fujianlian.klinechart.utils.Constants;
import com.vincent.story.adapter.DateCycleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.github.fujianlian.klinechart.utils.Constants.BOLL;
import static com.github.fujianlian.klinechart.utils.Constants.CANDLE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.LINE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_ITEM;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_TYPE;


public class MainActivity extends AppCompatActivity {

    private KLineChartView kLineChartView;

    private List<KLineEntity> datas;//模拟历史数据

    private List<KLineEntity> newDatas = new ArrayList<>();//模拟最新数据

    protected KLineChartAdapter adapter = new KLineChartAdapter();

    private int position = 6;//默认关闭子view

    private Unbinder mBinder;

    private Timer timer = new Timer();//当前时间刷新

    private int mSecond = 0;//秒数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mBinder = ButterKnife.bind(this);
        kLineChartView = findViewById(R.id.kLineChartView);

        kLineChartView.setAdapter(adapter);
        kLineChartView.justShowLoading();


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

        setChildDraw(Constants.CLEAR);

        refreshData();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }

    /**
     * 每秒刷新一次数据 ，每5秒新增一条数据
     */
    private void refreshData() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mSecond + RANG_ITEM + 1 >= newDatas.size()) {
                    mSecond = 0;
                }
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


    /**
     * 显示底部k线view和MA BOLL SAR
     *
     * @param position
     */
    public void setChildDraw(int position) {
        this.position = position;
        if (kLineChartView != null) {
            if (position == Constants.MA) {
                kLineChartView.changeMainDrawType(Status.MA);
            } else if (position == Constants.BOLL) {
                kLineChartView.changeMainDrawType(Status.BOLL);
            } else if (position != Constants.CLEAR) {
                position -= 2;
                kLineChartView.setChildDraw(position);
            } else {
                hideChildView();
            }

        }
        adapter.notifyDataSetChanged();
    }

    public void hideChildView() {
        if (kLineChartView != null) {
            kLineChartView.hideChildDraw();
            kLineChartView.changeMainDrawType(Status.NONE);
        }
    }

    public void setKlineType(String klineType) {
        if (kLineChartView != null) {
            kLineChartView.setKlineType(klineType);
        }
        adapter.notifyDataSetChanged();

    }


    @OnClick({R.id.tv_rang, R.id.tv_line, R.id.tv_candle, R.id.tv_ma, R.id.tv_boll, R.id.tv_macd
            , R.id.tv_kdj, R.id.tv_rsi, R.id.tv_vol, R.id.tv_clear})
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
            case R.id.tv_ma:
                setChildDraw(Constants.MA);
                break;
            case R.id.tv_boll:
                setChildDraw(Constants.BOLL);
                break;
            case R.id.tv_macd:
                setChildDraw(Constants.MACD);
                break;
            case R.id.tv_kdj:
                setChildDraw(Constants.KDJ);
                break;
            case R.id.tv_rsi:
                setChildDraw(Constants.RSI);
                break;
            case R.id.tv_vol:
                setChildDraw(Constants.VOL);
                break;
            case R.id.tv_clear:
                setChildDraw(Constants.CLEAR);
                break;
        }
    }
}
