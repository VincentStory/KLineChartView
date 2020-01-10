package com.github.fujianlian.klinechart.utils;

public class Constants {


    /**
     * case R.id.tv_ma:
     *                 setChildDraw(0);
     *                 break;
     *             case R.id.tv_boll:
     *                 setChildDraw(1);
     *                 break;
     *             case R.id.tv_macd:
     *                 setChildDraw(2);
     *                 break;
     *             case R.id.tv_kdj:
     *                 setChildDraw(3);
     *                 break;
     *             case R.id.tv_rsi:
     *                 setChildDraw(4);
     *                 break;
     *             case R.id.tv_vol:
     *                 setChildDraw(5);
     *                 break;
     *             case R.id.tv_clear:
     *                 setChildDraw(6);
     *                 break;
     */
    public static final int MA = 0;
    public static final int BOLL = 1;
    public static final int MACD = 2;
    public static final int KDJ = 3;
    public static final int RSI = 4;
    public static final int VOL = 5;
    public static final int CLEAR = 6;

    public static String CURRENT_LINE_TYPE = "RANG_TYPE";
    public static final String RANG_TYPE = "RANG_TYPE";
    public static final String LINE_TYPE = "LINE_TYPE";
    public static final String CANDLE_TYPE = "CANDLE_TYPE";
    public static final int RANG_ITEM = 30; //添加假数据实现k线左移

    //"1min", "5min", "15min", "30min", "60min", "1day", "1week", "1mon"};
    public static final String FIVE_SECOND = "5s";
    public static final String ONE_MINUTE = "1min";
    public static final String FIVE_MINUTE = "5min";
    public static final String FIFTEEN_MINUTE = "15min";
    public static final String THIRTY_MINUTE = "30min";
    public static final String SIXTY_MINUTE = "60min";
    public static final String ONE_DAY = "1day";
    public static final String ONE_WEEK = "1week";
    public static final String ONE_MON = "1mon";
}
