package com.github.fujianlian.klinechart.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.github.fujianlian.klinechart.R;
import com.github.fujianlian.klinechart.BaseKLineChartView;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.base.IChartDraw;
import com.github.fujianlian.klinechart.base.IValueFormatter;
import com.github.fujianlian.klinechart.entity.ICandle;
import com.github.fujianlian.klinechart.entity.IKLine;
import com.github.fujianlian.klinechart.formatter.ValueFormatter;
import com.github.fujianlian.klinechart.utils.Constants;
import com.github.fujianlian.klinechart.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import static com.github.fujianlian.klinechart.utils.Constants.CANDLE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.LINE_TYPE;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_ITEM;
import static com.github.fujianlian.klinechart.utils.Constants.RANG_TYPE;

/**
 * 主图的实现类
 * Created by tifezh on 2016/6/14.
 */
public class MainDraw implements IChartDraw<ICandle> {

    private final Context context;
    private float mCandleWidth = 0;
    private float mCandleLineWidth = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mGreenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma5Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma10Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint ma30Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint highPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint openPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint lowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint closePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mSelectorTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mSelectorBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context mContext;

    private boolean mCandleSolid = true;
    // 是否画范围
    private boolean isRangeLine1 = false;
    //是否画线
    private boolean isLine = false;
    private Status status = Status.MA;
    private KLineChartView kChartView;
    private String klineType = Constants.CANDLE_TYPE;

    private int mDigit = 2;

    public MainDraw(BaseKLineChartView view) {
        context = view.getContext();
        kChartView = (KLineChartView) view;
        mContext = context;
        mRedPaint.setColor(ContextCompat.getColor(context, R.color.color_D04B63));
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.color_53B4A9));
        mLinePaint.setColor(ContextCompat.getColor(context, R.color.chart_line));
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 700, ContextCompat.getColor(context, R.color.chart_line_white), ContextCompat.getColor(context, R.color.chart_line_background), Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        highPaint.setColor(ContextCompat.getColor(context, R.color.color_6384FF));
        openPaint.setColor(ContextCompat.getColor(context, R.color.color_D04B63));
        lowPaint.setColor(ContextCompat.getColor(context, R.color.color_E8D28E));
        closePaint.setColor(ContextCompat.getColor(context, R.color.color_57BEB1));

//        LinearGradient linearGradient1 = new LinearGradient(1000, 0, 0, 0, ContextCompat.getColor(context, R.color.color_2982D1), ContextCompat.getColor(context, R.color.chart_ma10), Shader.TileMode.MIRROR);
//        mLinePaint.setShader(linearGradient1);
//        paint.setColor(ContextCompat.getColor(context, R.color.chart_line_background));
//        LinearGradient linearGradient =new LinearGradient(0,0,0,300,ContextCompat.getColor(context, R.color.chart_line_whtie),ContextCompat.getColor(context, R.color.chart_line_background), Shader.TileMode.MIRROR);
//        paint.setShader(linearGradient);

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setmDigit(int mDigit) {
        this.mDigit = mDigit;
    }

    @Override
    public void drawTranslated(@Nullable ICandle lastPoint, @NonNull ICandle curPoint, float lastX, float curX, @NonNull Canvas canvas, @NonNull BaseKLineChartView view, int position) {
        switch (klineType) {
            case RANG_TYPE:
                view.drawMainLine(canvas, mLinePaint, lastX, lastPoint.getClosePrice(), curX, curPoint.getClosePrice());

                view.drawMainMinuteLine(canvas, paint, lastX, lastPoint.getClosePrice(), curX, curPoint.getClosePrice());
                if (status == Status.MA) {
                    //画ma60
//                    if (lastPoint.getMA60Price() != 0) {
//                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA60Price(), curX, curPoint.getMA60Price());
//                    }
                    //画ma5
                    if (lastPoint.getMA5Price() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getMA5Price(), curX, curPoint.getMA5Price());
                    }
//                    //画ma10
                    if (lastPoint.getMA10Price() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA10Price(), curX, curPoint.getMA10Price());
                    }
//                    //画ma30
                    if (lastPoint.getMA30Price() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getMA30Price(), curX, curPoint.getMA30Price());
                    }
                } else if (status == Status.BOLL) {
                    //画boll
//                    if (lastPoint.getMb() != 0) {
//                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMb(), curX, curPoint.getMb());
//                    }
                    if (lastPoint.getUp() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getUp(), curX, curPoint.getUp());
                    }
                    if (lastPoint.getMb() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMb(), curX, curPoint.getMb());
                    }
                    if (lastPoint.getDn() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getDn(), curX, curPoint.getDn());
                    }
                }
                break;
            case LINE_TYPE:
                view.drawMainLine(canvas, mLinePaint, lastX, lastPoint.getClosePrice(), curX, curPoint.getClosePrice());
//            view.drawMainMinuteLine(canvas, paint, lastX, lastPoint.getClosePrice(), curX, curPoint.getClosePrice());
                if (status == Status.MA) {
                    //画ma60
//                    if (lastPoint.getMA60Price() != 0) {
//                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA60Price(), curX, curPoint.getMA60Price());
//                    }
                    //画ma5
                    if (lastPoint.getMA5Price() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getMA5Price(), curX, curPoint.getMA5Price());
                    }
//                    //画ma10
                    if (lastPoint.getMA10Price() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA10Price(), curX, curPoint.getMA10Price());
                    }
//                    //画ma30
                    if (lastPoint.getMA30Price() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getMA30Price(), curX, curPoint.getMA30Price());
                    }
                } else if (status == Status.BOLL) {
                    //画boll

                    if (lastPoint.getUp() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getUp(), curX, curPoint.getUp());
                    }
                    if (lastPoint.getMb() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMb(), curX, curPoint.getMb());
                    }
                    if (lastPoint.getDn() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getDn(), curX, curPoint.getDn());
                    }
                }
                break;
            case CANDLE_TYPE:
                drawCandle(view, canvas, curX, curPoint.getHighPrice(), curPoint.getLowPrice(), curPoint.getOpenPrice(), curPoint.getClosePrice());
                if (status == Status.MA) {
//                    //画ma5
                    if (lastPoint.getMA5Price() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getMA5Price(), curX, curPoint.getMA5Price());
                    }
//                    //画ma10
                    if (lastPoint.getMA10Price() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMA10Price(), curX, curPoint.getMA10Price());
                    }
//                    //画ma30
                    if (lastPoint.getMA30Price() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getMA30Price(), curX, curPoint.getMA30Price());
                    }
                } else if (status == Status.BOLL) {
                    //画boll
                    if (lastPoint.getUp() != 0) {
                        view.drawMainLine(canvas, ma5Paint, lastX, lastPoint.getUp(), curX, curPoint.getUp());
                    }
                    if (lastPoint.getMb() != 0) {
                        view.drawMainLine(canvas, ma10Paint, lastX, lastPoint.getMb(), curX, curPoint.getMb());
                    }
                    if (lastPoint.getDn() != 0) {
                        view.drawMainLine(canvas, ma30Paint, lastX, lastPoint.getDn(), curX, curPoint.getDn());
                    }
                }
                break;
        }


    }

    @Override
    public void drawText(@NonNull Canvas canvas, @NonNull BaseKLineChartView view, int position, float x, float y) {
        ICandle point = (IKLine) view.getItem(view.getNewPosition());
        y = y - 5;
        Log.i("y==", y + "");
//        canvas.drawText(view.getNewInfo(), 0, y, ma10Paint);
        String hightText = "     高: " + getValueFormatter().format(point.getHighPrice(), mDigit);
        String openText = "       开: " + getValueFormatter().format(point.getOpenPrice(), mDigit);
        String lowText = "       低: " + getValueFormatter().format(point.getLowPrice(), mDigit);
        String closeText = "       收: " + getValueFormatter().format(point.getClosePrice(), mDigit);
        canvas.drawText(hightText, 0, y, highPaint);
        canvas.drawText(openText, openPaint.measureText(hightText), y, openPaint);
        canvas.drawText(lowText, openPaint.measureText(hightText) * 2, y, lowPaint);
        canvas.drawText(closeText, openPaint.measureText(hightText) * 3, y, closePaint);
        if (isRangeLine1) {
//            if (status == Status.MA) {
//                if (point.getMA60Price() != 0) {
//                    String text = "MA60:" + view.formatValue(point.getMA60Price()) + "  ";
//                    canvas.drawText(text, x, y, ma10Paint);
//                }
//            } else if (status == Status.BOLL) {
//                if (point.getMb() != 0) {
//                    String text = "BOLL:" + view.formatValue(point.getMb()) + "  ";
//                    canvas.drawText(text, x, y, ma10Paint);
//                }
//            }
        } else {
//            if (status == Status.MA) {
//                String text;
//                if (point.getMA5Price() != 0) {
//                    text = "MA5:" + view.formatValue(point.getMA5Price()) + "  ";
//                    canvas.drawText(text, x, y, ma5Paint);
//                    x += ma5Paint.measureText(text);
//                }
//                if (point.getMA10Price() != 0) {
//                    text = "MA10:" + view.formatValue(point.getMA10Price()) + "  ";
//                    canvas.drawText(text, x, y, ma10Paint);
//                    x += ma10Paint.measureText(text);
//                }
//                if (point.getMA20Price() != 0) {
//                    text = "MA30:" + view.formatValue(point.getMA30Price());
//                    canvas.drawText(text, x, y, ma30Paint);
//                }
//            } else if (status == Status.BOLL) {
//                if (point.getMb() != 0) {
//                    String text = "BOLL:" + view.formatValue(point.getMb()) + "  ";
//                    canvas.drawText(text, x, y, ma10Paint);
//                    x += ma5Paint.measureText(text);
//                    text = "UB:" + view.formatValue(point.getUp()) + "  ";
//                    canvas.drawText(text, x, y, ma5Paint);
//                    x += ma10Paint.measureText(text);
//                    text = "LB:" + view.formatValue(point.getDn());
//                    canvas.drawText(text, x, y, ma30Paint);
//                }
//            }
        }
        if (view.isLongPress()) {
            drawSelector(view, canvas);
        }
    }

    @Override
    public float getMaxValue(ICandle point) {
        if (status == Status.BOLL) {
            if (Float.isNaN(point.getUp())) {
                if (point.getMb() == 0) {
                    return point.getHighPrice();
                } else {
                    return point.getMb();
                }
            } else if (point.getUp() == 0) {
                return point.getHighPrice();
            } else {
                return point.getUp();
            }
        } else {
            return Math.max(point.getHighPrice(), point.getMA30Price());
        }
    }

    @Override
    public float getMinValue(ICandle point) {
        if (status == Status.BOLL) {
            if (point.getDn() == 0) {
                return point.getLowPrice();
            } else {
                return point.getDn();
            }
        } else {
            if (point.getMA30Price() == 0f) {
                return point.getLowPrice();
            } else {
                return Math.min(point.getMA30Price(), point.getLowPrice());
            }
        }
    }

    @Override
    public IValueFormatter getValueFormatter() {
        return new ValueFormatter();
    }

    /**
     * 画Candle
     *
     * @param canvas
     * @param x          x轴坐标
     * @param highPrice  最高价
     * @param lowPrice   最低价
     * @param openPrice  开盘价
     * @param closePrice 收盘价
     */
    private void drawCandle(BaseKLineChartView view, Canvas canvas, float x, float highPrice, float lowPrice, float openPrice, float closePrice) {
        float high = view.getMainY(highPrice);
        float low = view.getMainY(lowPrice);
        float open = view.getMainY(openPrice);
        float close = view.getMainY(closePrice);
        float r = mCandleWidth / 2;
//        float lineR = mCandleLineWidth / 2;
        float lineR = (float) 0.5;
        if (openPrice > closePrice) {//跌
            //实心
            if (mCandleSolid) {
                canvas.drawRect(x - r, close, x + r, open, mRedPaint);
                canvas.drawRect(x - lineR, high, x + lineR, low, mRedPaint);
            } else {
                mRedPaint.setStrokeWidth(mCandleLineWidth);
                canvas.drawLine(x, high, x, close, mRedPaint);
                canvas.drawLine(x, open, x, low, mRedPaint);
                canvas.drawLine(x - r + lineR, open, x - r + lineR, close, mRedPaint);
                canvas.drawLine(x + r - lineR, open, x + r - lineR, close, mRedPaint);
                mRedPaint.setStrokeWidth(mCandleLineWidth * view.getScaleX());
                canvas.drawLine(x - r, open, x + r, open, mRedPaint);
                canvas.drawLine(x - r, close, x + r, close, mRedPaint);
            }

        } else if (openPrice < closePrice) {//涨
            canvas.drawRect(x - r, open, x + r, close, mGreenPaint);
            canvas.drawRect(x - lineR, high, x + lineR, low, mGreenPaint);
        } else {
            canvas.drawRect(x - r, open, x + r, close + 1, mGreenPaint);
            canvas.drawRect(x - lineR, high, x + lineR, low, mGreenPaint);
        }
    }

    /**
     * draw选择器
     *
     * @param view
     * @param canvas
     */
    private void drawSelector(BaseKLineChartView view, Canvas canvas) {
        Paint.FontMetrics metrics = mSelectorTextPaint.getFontMetrics();
        float textHeight = metrics.descent - metrics.ascent;

        int index = view.getSelectedIndex();
        float padding = ViewUtil.Dp2Px(mContext, 5);
        float margin = ViewUtil.Dp2Px(mContext, 5);
        float width = 0;
        float left;
        float top = margin + view.getTopPadding();
        float height = padding * 8 + textHeight * 5;


        ICandle point = (ICandle) view.getItem(index);
        List<String> strings = new ArrayList<>();
        strings.add(view.getAdapter().getDate(index));
        strings.add("高:" +   getValueFormatter().format(point.getHighPrice(), mDigit));
        strings.add("开:" + getValueFormatter().format(point.getOpenPrice(), mDigit));
        strings.add("低:" +   getValueFormatter().format(point.getLowPrice(), mDigit));
        strings.add("收:" + getValueFormatter().format(point.getClosePrice(), mDigit));

        for (String s : strings) {
            width = Math.max(width, mSelectorTextPaint.measureText(s));
        }
        width += padding * 2;

        float x = view.translateXtoX(view.getX(index));
        if (x > view.getChartWidth() / 2) {
            left = margin;
        } else {
            left = view.getChartWidth() - width - margin;
        }

        RectF r = new RectF(left, top, left + width, top + height);
        canvas.drawRoundRect(r, padding, padding, mSelectorBackgroundPaint);
        float y = top + padding * 2 + (textHeight - metrics.bottom - metrics.top) / 2;

        for (String s : strings) {
            canvas.drawText(s, left + padding, y, mSelectorTextPaint);
            y += textHeight + padding;
        }


    }

    /**
     * 设置蜡烛宽度
     *
     * @param candleWidth
     */
    public void setCandleWidth(float candleWidth) {
        mCandleWidth = candleWidth;
    }

    /**
     * 设置蜡烛线宽度
     *
     * @param candleLineWidth
     */
    public void setCandleLineWidth(float candleLineWidth) {
        mCandleLineWidth = candleLineWidth;
    }

    /**
     * 设置ma5颜色
     *
     * @param color
     */
    public void setMa5Color(int color) {
        this.ma5Paint.setColor(color);
    }

    /**
     * 设置ma10颜色
     *
     * @param color
     */
    public void setMa10Color(int color) {
        this.ma10Paint.setColor(color);
    }

    /**
     * 设置ma30颜色
     *
     * @param color
     */
    public void setMa30Color(int color) {
        this.ma30Paint.setColor(color);
    }

    /**
     * 设置选择器文字颜色
     *
     * @param color
     */
    public void setSelectorTextColor(int color) {
        mSelectorTextPaint.setColor(color);
    }

    /**
     * 设置选择器文字大小
     *
     * @param textSize
     */
    public void setSelectorTextSize(float textSize) {
        mSelectorTextPaint.setTextSize(textSize);
    }

    /**
     * 设置选择器背景
     *
     * @param color
     */
    public void setSelectorBackgroundColor(int color) {
        mSelectorBackgroundPaint.setColor(color);
    }

    /**
     * 设置曲线宽度
     */
    public void setLineWidth(float width) {
        ma30Paint.setStrokeWidth(width);
        ma10Paint.setStrokeWidth(width);
        ma5Paint.setStrokeWidth(width);
        mLinePaint.setStrokeWidth(width);
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float textSize) {
        ma30Paint.setTextSize(textSize);
        ma10Paint.setTextSize(textSize);
        ma5Paint.setTextSize(textSize);
        highPaint.setTextSize(textSize);
        openPaint.setTextSize(textSize);
        lowPaint.setTextSize(textSize);
        closePaint.setTextSize(textSize);

    }

    /**
     * 蜡烛是否实心
     */
    public void setCandleSolid(boolean candleSolid) {
        mCandleSolid = candleSolid;
    }

    public void setRange(boolean line) {
        if (isRangeLine1 != line) {
            isRangeLine1 = line;
            if (isRangeLine1) {
                kChartView.setCandleWidth(kChartView.dp2px(6f));
            } else {
                kChartView.setCandleWidth(kChartView.dp2px(7f));
            }
        }
    }

    public void setKlineType(String klineType) {
        this.klineType = klineType;
    }

    public void setLine(boolean line) {
        if (isLine != line) {
            isLine = line;
            if (isLine) {
                kChartView.setCandleWidth(kChartView.dp2px(7f));
            } else {
                kChartView.setCandleWidth(kChartView.dp2px(6f));
            }
        }
    }

    public boolean isLine() {
        return isRangeLine1;
    }
}
