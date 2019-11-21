package com.github.fujianlian.klinechart;


import android.util.Log;

import com.github.fujianlian.klinechart.utils.Constants;
import com.github.fujianlian.klinechart.utils.DateUtil;

import java.util.List;



import static com.github.fujianlian.klinechart.utils.Constants.RANG_ITEM;

/**
 * 数据辅助类 计算macd rsi等
 * Created by tifezh on 2016/11/26.
 */
public class DataHelper {

    /**
     * 计算RSI
     *
     * @param dataList
     */
    static void calculateRSI(List<KLineEntity> dataList) {
        Float rsi;
        float rsiABSEma = 0;
        float rsiMaxEma = 0;
        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            final float closePrice = point.getClosePrice();
            if (i == 0) {
                rsi = 0f;
                rsiABSEma = 0;
                rsiMaxEma = 0;
            } else {
                float Rmax = Math.max(0, closePrice - dataList.get(i - 1).getClosePrice());
                float RAbs = Math.abs(closePrice - dataList.get(i - 1).getClosePrice());

                rsiMaxEma = (Rmax + (14f - 1) * rsiMaxEma) / 14f;
                rsiABSEma = (RAbs + (14f - 1) * rsiABSEma) / 14f;
                rsi = (rsiMaxEma / rsiABSEma) * 100;
            }
            if (i < 13) {
                rsi = 0f;
            }
            if (rsi.isNaN())
                rsi = 0f;
            point.rsi = rsi;
        }
    }

    /**
     * 计算kdj
     *
     * @param dataList
     */
    static void calculateKDJ(List<KLineEntity> dataList) {
        float k = 0;
        float d = 0;
        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            final float closePrice = point.getClosePrice();
            int startIndex = i - 13;
            if (startIndex < 0) {
                startIndex = 0;
            }
            float max14 = Float.MIN_VALUE;
            float min14 = Float.MAX_VALUE;
            for (int index = startIndex; index <= i; index++) {
                if (dataList.get(index) == null)
                    return;
                max14 = Math.max(max14, dataList.get(index).getHighPrice());
                min14 = Math.min(min14, dataList.get(index).getLowPrice());
            }
            Float rsv = 100f * (closePrice - min14) / (max14 - min14);
            if (rsv.isNaN()) {
                rsv = 0f;
            }
            if (i == 0) {
                k = 50;
                d = 50;
            } else {
                k = (rsv + 2f * k) / 3f;
                d = (k + 2f * d) / 3f;
            }
            if (i < 13) {
                point.k = 0;
                point.d = 0;
                point.j = 0;
            } else if (i == 13 || i == 14) {
                point.k = k;
                point.d = 0;
                point.j = 0;
            } else {
                point.k = k;
                point.d = d;
                point.j = 3f * k - 2 * d;
            }
        }

    }

    /**
     * 计算wr
     *
     * @param dataList
     */
    static void calculateWR(List<KLineEntity> dataList) {
        Float r;
        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            int startIndex = i - 14;
            if (startIndex < 0) {
                startIndex = 0;
            }
            float max14 = Float.MIN_VALUE;
            float min14 = Float.MAX_VALUE;
            for (int index = startIndex; index <= i; index++) {

                max14 = Math.max(max14, dataList.get(index).getHighPrice());
                min14 = Math.min(min14, dataList.get(index).getLowPrice());
            }
            if (i < 13) {
                point.r = -10;
            } else {
                r = -100 * (max14 - dataList.get(i).getClosePrice()) / (max14 - min14);
                if (r.isNaN()) {
                    point.r = 0;
                } else {
                    point.r = r;
                }
            }
        }

    }

    /**
     * 计算macd
     *
     * @param dataList
     */
    static void calculateMACD(List<KLineEntity> dataList) {
        float ema12 = 0;
        float ema26 = 0;
        float dif = 0;
        float dea = 0;
        float macd = 0;

        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            final float closePrice = point.getClosePrice();
            if (i == 0) {
                ema12 = closePrice;
                ema26 = closePrice;
            } else {
                // EMA（12） = 前一日EMA（12） X 11/13 + 今日收盘价 X 2/13
                ema12 = ema12 * 11f / 13f + closePrice * 2f / 13f;
                // EMA（26） = 前一日EMA（26） X 25/27 + 今日收盘价 X 2/27
                ema26 = ema26 * 25f / 27f + closePrice * 2f / 27f;
            }
            // DIF = EMA（12） - EMA（26） 。
            // 今日DEA = （前一日DEA X 8/10 + 今日DIF X 2/10）
            // 用（DIF-DEA）*2即为MACD柱状图。
            dif = ema12 - ema26;
            dea = dea * 8f / 10f + dif * 2f / 10f;
            macd = (dif - dea) * 2f;
            point.dif = dif;
            point.dea = dea;
            point.macd = macd;
        }

    }

    /**
     * 计算 BOLL 需要在计算ma之后进行
     *
     * @param dataList
     */
    static void calculateBOLL(List<KLineEntity> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            if (i < 19) {
                point.mb = 0;
                point.up = 0;
                point.dn = 0;
            } else {
                int n = 20;
                float md = 0;
                for (int j = i - n + 1; j <= i; j++) {
                    float c = dataList.get(j).getClosePrice();
                    float m = point.getMA20Price();
                    float value = c - m;
                    md += value * value;
                }
                md = md / (n - 1);
                md = (float) Math.sqrt(md);
                point.mb = point.getMA20Price();
                point.up = point.mb + 2f * md;
                point.dn = point.mb - 2f * md;
            }
        }

    }

    /**
     * @param dataList
     */
    static void calculateSAR(List<KLineEntity> dataList) {

        //SAR指标py代码
        //def psar(barsdata, iaf = 0.02, maxaf = 0.2):
        //    length = len(barsdata)
        //    dates = list(barsdata['Date'])
        //    high = list(barsdata['High'])
        //    low = list(barsdata['Low'])
        //    close = list(barsdata['Close'])
        //    psar = close[0:len(close)]
        //    psarbull = [None] * length
        //    psarbear = [None] * length
        //    bull = True
        //    af = iaf
        //    ep = low[0]
        //    hp = high[0]
        //    lp = low[0]
        //    
        //    for i in range(2,length):
        //        if bull:
        //            psar[i] = psar[i - 1] + af * (hp - psar[i - 1])
        //        else:
        //            psar[i] = psar[i - 1] + af * (lp - psar[i - 1])
        //        
        //        reverse = False
        //        
        //        if bull:
        //            if low[i] < psar[i]:
        //                bull = False
        //                reverse = True
        //                psar[i] = hp
        //                lp = low[i]
        //                af = iaf
        //        else:
        //            if high[i] > psar[i]:
        //                bull = True
        //                reverse = True
        //                psar[i] = lp
        //                hp = high[i]
        //                af = iaf
        //    
        //        if not reverse:
        //            if bull:
        //                if high[i] > hp:
        //                    hp = high[i]
        //                    af = min(af + iaf, maxaf)
        //                if low[i - 1] < psar[i]:
        //                    psar[i] = low[i - 1]
        //                if low[i - 2] < psar[i]:
        //                    psar[i] = low[i - 2]
        //            else:
        //                if low[i] < lp:
        //                    lp = low[i]
        //                    af = min(af + iaf, maxaf)
        //                if high[i - 1] > psar[i]:
        //                    psar[i] = high[i - 1]
        //                if high[i - 2] > psar[i]:
        //                    psar[i] = high[i - 2]
        //                    
        //        if bull:
        //            psarbull[i] = psar[i]
        //        else:
        //            psarbear[i] = psar[i]
        // 
        //    return {"dates":dates, "high":high, "low":low, "close":close, "psar":psar, "psarbear":psarbear, "psarbull":psarbull}
        //
        //这段代码有点问题 需要修改下才能使用

        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);

        }

    }

    /**
     * 计算ma
     *
     * @param dataList
     */
    static void calculateMA(List<KLineEntity> dataList) {
        float ma5 = 0;
        float ma10 = 0;
        float ma20 = 0;
        float ma30 = 0;
        float ma60 = 0;

        for (int i = 0; i < dataList.size(); i++) {
            KLineEntity point = dataList.get(i);
            final float closePrice = point.getClosePrice();

            ma5 += closePrice;
            ma10 += closePrice;
            ma20 += closePrice;
            ma30 += closePrice;
            ma60 += closePrice;
            if (i == 4) {
                point.MA5Price = ma5 / 5f;
            } else if (i >= 5) {
                ma5 -= dataList.get(i - 5).getClosePrice();
                point.MA5Price = ma5 / 5f;
            } else {
                point.MA5Price = 0f;
            }
            if (i == 9) {
                point.MA10Price = ma10 / 10f;
            } else if (i >= 10) {
                ma10 -= dataList.get(i - 10).getClosePrice();
                point.MA10Price = ma10 / 10f;
            } else {
                point.MA10Price = 0f;
            }
            if (i == 19) {
                point.MA20Price = ma20 / 20f;
            } else if (i >= 20) {
                ma20 -= dataList.get(i - 20).getClosePrice();
                point.MA20Price = ma20 / 20f;
            } else {
                point.MA20Price = 0f;
            }
            if (i == 29) {
                point.MA30Price = ma30 / 30f;
            } else if (i >= 30) {
                ma30 -= dataList.get(i - 30).getClosePrice();
                point.MA30Price = ma30 / 30f;
            } else {
                point.MA30Price = 0f;
            }
            if (i == 59) {
                point.MA60Price = ma60 / 60f;
            } else if (i >= 60) {
                ma60 -= dataList.get(i - 60).getClosePrice();
                point.MA60Price = ma60 / 60f;
            } else {
                point.MA60Price = 0f;
            }
        }
    }

    /**
     * 计算MA BOLL RSI KDJ MACD
     *
     * @param dataList
     */
    public static void calculate(List<KLineEntity> dataList) {
        calculateMA(dataList);
//        calculateSAR(dataList);
        calculateMACD(dataList);
        calculateBOLL(dataList);
        calculateRSI(dataList);
        calculateKDJ(dataList);
        calculateWR(dataList);
        calculateVolumeMA(dataList);
    }

    private static String[] periods = new String[]{Constants.ONE_MINUTE, Constants.FIVE_MINUTE, Constants.FIFTEEN_MINUTE,
            Constants.THIRTY_MINUTE, Constants.SIXTY_MINUTE, Constants.ONE_DAY, Constants.ONE_WEEK, Constants.ONE_MON};

    private static String[] optionPeriods = new String[]{Constants.FIVE_SECOND};

    public static String[] getPeriods(String currentType) {
        if (currentType.equals("2")) {
            return periods;
        } else {
            return optionPeriods;
        }
    }

    /**
     * 计算最后二十条数据时间
     *
     * @param entries
     */
    public static void calculateEndData(List<KLineEntity> entries, int choosePosition, String currentType, float openPirce) {
//        DateUtil.DatePattern datePattern;
        if (entries.size() == 0)
            return;
        String time = entries.get(entries.size() - (Constants.RANG_ITEM + 1)).getTimestamp();//时间戳
        for (int i = entries.size() - (Constants.RANG_ITEM + 1); i < entries.size(); i++) {
            Long newTime = Long.valueOf(time) + DataHelper.getT(getPeriods(currentType)[choosePosition]) * (i - (entries.size() - (Constants.RANG_ITEM + 1)));
            entries.get(i).setDate(DateUtil.getStrTime(newTime + "", getDatePattern(getPeriods(currentType)[choosePosition])));
            entries.get(i).setTimestamp(newTime + "");
            entries.get(i).setOpen(openPirce);
        }
    }


    public static DateUtil.DatePattern getDatePattern(String time) {

        DateUtil.DatePattern datePattern = DateUtil.DatePattern.ONLY_HOUR_MINUTE;
        switch (time) {
            case Constants.FIVE_SECOND:
                datePattern = DateUtil.DatePattern.ONLY_TIME;
                break;
            case Constants.ONE_MINUTE:
            case Constants.FIVE_MINUTE:
            case Constants.FIFTEEN_MINUTE:
            case Constants.THIRTY_MINUTE:
                datePattern = DateUtil.DatePattern.ONLY_HOUR_MINUTE;
                break;
            case Constants.SIXTY_MINUTE:
            case Constants.ONE_DAY:
            case Constants.ONE_WEEK:
                datePattern = DateUtil.DatePattern.ONLY_MONTH_SEC;
                break;
            case Constants.ONE_MON:
                datePattern = DateUtil.DatePattern.ONLY_DAY;
                break;
        }

        return datePattern;
    }

    public static Long getT(String time) {
        long t = 0;
        switch (time) {
            case Constants.FIVE_SECOND:
                t = 1000 * 5;
                break;
            case Constants.ONE_MINUTE:
                t = 1000 * 60;
                break;
            case Constants.FIVE_MINUTE:
                t = 1000 * 60 * 5;
                break;
            case Constants.FIFTEEN_MINUTE:
                t = 1000 * 60 * 15;
                break;
            case Constants.THIRTY_MINUTE:
                t = 1000 * 60 * 30;
                break;
            case Constants.SIXTY_MINUTE:
                t = 1000 * 60 * 60;
                break;
            case Constants.ONE_DAY:
                t = 1000 * 60 * 60 * 24;
                break;
            case Constants.ONE_WEEK:
                t = 1000 * 60 * 60 * 24 * 7;
                break;
            case Constants.ONE_MON:
                t = 1000 * 60 * 60 * 24 * 30;
                break;
        }

        return t;
    }

    private static void calculateVolumeMA(List<KLineEntity> entries) {
        float volumeMa5 = 0;
        float volumeMa10 = 0;

        for (int i = 0; i < entries.size(); i++) {
            KLineEntity entry = entries.get(i);
            volumeMa5 += entry.getVolume();
            volumeMa10 += entry.getVolume();

            if (i == 4) {
                entry.MA5Volume = (volumeMa5 / 5f);
            } else if (i > 4) {
                volumeMa5 -= entries.get(i - 5).getVolume();
                entry.MA5Volume = volumeMa5 / 5f;
            } else {
                entry.MA5Volume = 0f;
            }

            if (i == 9) {
                entry.MA10Volume = volumeMa10 / 10f;
            } else if (i > 9) {
                volumeMa10 -= entries.get(i - 10).getVolume();
                entry.MA10Volume = volumeMa10 / 10f;
            } else {
                entry.MA10Volume = 0f;
            }
        }
    }
}
