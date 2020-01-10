# KLineChartView

Android仿IQ Option K线图实现（包含MA,BOLL,MACD,KDJ,RSI,VOL指标）

本项目是在 https://github.com/fujianlian/KLineChart 基础上进行修改的，对UI展示进行了修改。

本项目模拟了socket实时推送数据效果

## 效果预览 

### 范围图，线性图切换加各种指标线展示

![效果图](https://github.com/VincentStory/KLineChartView/blob/master/IMG_4115.GIF)

## 引入项目流程

### 1.xml引入KChartView
```
<com.github.tifezh.kchartlib.chart.KChartView
        android:id="@+id/kchart_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
</com.github.tifezh.kchartlib.chart.KChartView>
  
```



