# KLineChartView

Android仿IQ Option K线图实现（包含MA,BOLL,MACD,KDJ,RSI,VOL指标）

本项目是在 https://github.com/fujianlian/KLineChart 基础上进行修改的，对UI展示进行了修改。

本项目模拟了socket实时推送数据效果，如果项目要使用StompProtocol可以参考我另外一个项目：https://github.com/VincentStory/StompForAndroid ，有具体的接入过程

## 效果预览 

### 效果图中包括范围图，线性图，柱状图随意切换，以及各种指标线展示,自定义显示和隐藏VOL指标

![效果图](https://github.com/VincentStory/KLineChartView/blob/master/IMG_4115.GIF)
![截图](https://github.com/VincentStory/KLineChartView/blob/master/img1.jpg)
![截图](https://github.com/VincentStory/KLineChartView/blob/master/img2.jpg)


## 引入项目流程

### 1.xml引入KChartView
```
<com.github.tifezh.kchartlib.chart.KChartView
        android:id="@+id/kchart_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
</com.github.tifezh.kchartlib.chart.KChartView>
  
```
### 2.添加数据并刷新view
```
 datas = DataRequest.getALL(MainActivity.this).subList(0, 500);
 adapter.addFooterData(datas);
        adapter.notifyDataSetChanged();

        if (kLineChartView != null) {
            kLineChartView.startAnimation();
            kLineChartView.refreshEnd();
            kLineChartView.hideChildDraw();
        }
  ```
 ### 3.显示和隐藏指标线
 
  ```
                setChildDraw(Constants.MA);
              
                setChildDraw(Constants.BOLL);
              
                setChildDraw(Constants.MACD);
             
                setChildDraw(Constants.KDJ);
             
                setChildDraw(Constants.RSI);
              
                setChildDraw(Constants.VOL);
                         
                setChildDraw(Constants.CLEAR);
 ```
 ### 4.如有其他特定需求，需在BaseKLineChartView改写代码完成逻辑计算，和绘制特定效果
 
-----------------------完成--------------------

如果对你有所帮助的话，欢迎Star或者Fork,有遇到其他问题可以加我vx进行沟通
 QQ：459005147 备注：android交流
 
 ```
Copyright 2018 VincentStory

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```


