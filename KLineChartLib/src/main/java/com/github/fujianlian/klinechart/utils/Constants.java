package com.github.fujianlian.klinechart.utils;

public class Constants {


    //    public static final String socketAddress = "ws://139.196.89.20:9002/ws/websocket";
//    public static final String socketAddress =  "ws://47.52.138.51:9001/ws/websocket";
//    public static final String socketAddress = "wss://stream.bctex-global.com/ws/websocket";
    public static final String socketAddress = "wss://stream.dcf-globe.com/ws/websocket";
//    public static final String socketAddress = "ws://192.168.0.108:9002/ws/websocket";

    //k线
    public static final String klineUrl = "/market/"; // "/market/BTC/USDT/1"
    public static final String klineHistoryUrl = "/user/";//  '/user/admin/history/kline'
    public static final String reqKlineHistoryUrl = "/req/history/kline/"; // "/req/history/kline/admin/BTC/USDT/1/1"
    //货币对
    public static final String bolUrl = "/market/trade"; // '/market/trade'
    public static final String bolHistoryUrl = "/user/"; // '/user/admin/history/trade'
    public static final String reqBolHistoryUrl = "/req/history/trade/List/"; // "/req/history/trade/List/admin"
    //系统最新时间
    public static final String dateUrl = "/market/date"; // '/market/trade'

    //持仓订单
    public static final String orderUrl = "/user/"; // '/user/{userId}/market/holdpositions/{type}'
    public static final String orderHistoryUrl = "/user/"; // '/user/{userId}/history/holdpositions'
    public static final String reqOrderHistoryUrl = "/req/history/holdpositions/"; // "/req/history/holdpositions/{userId}/{type}"   type 为1是虚拟，2是真实，3.是量化

    //结束订单
    public static final String finishOrderUrl = "/user/"; ///user/{userId}/market/cover/{1.虚拟，2.真实}

    //账户
    public static final String accountUrl = "/user/";//"/user/{userId}/market/account"  账户推送接口
    public static final String reqAccountUrl = "/req/history/account/";//"/req/history/account/{userId}" 获取历史账户


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
