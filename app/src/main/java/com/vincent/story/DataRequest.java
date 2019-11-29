package com.vincent.story;

import android.content.Context;
import android.util.Log;

import com.github.fujianlian.klinechart.DataHelper;
import com.github.fujianlian.klinechart.KLineEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟网络请求
 * Created by tifezh on 2017/7/3.
 */

public class DataRequest {
    private static List<KLineEntity> datas = null;

    public static String getStringFromAssert(Context context, String fileName) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            return new String(buffer, 0, buffer.length, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("exception==",e.getMessage());
        }
        return "";
    }

    public static List<KLineEntity> getALL(Context context) {
        if (datas == null) {
//            String string = getStringFromAssert(context, "ibm.json");
//            Log.i("string==", string);
            final List<KLineEntity> data = new Gson().fromJson(getStringFromAssert(context, "ibm.json"), new TypeToken<List<KLineEntity>>() {
            }.getType());

            DataHelper.calculate(data);
            datas = data;
        }
        return datas;
    }

    public static List<KLineEntity> addData(Context context) {
        if (datas != null) {
            KLineEntity entity = new KLineEntity();
            entity.setClose((float) 86.875);
            entity.setDate("2016/10/28");
            entity.setHigh((float) 154.059998);
            entity.setLow((float) 152.020004);
            entity.setOpen((float) 152.820007);
            entity.setVolume((float) 4126800);
            datas.add(new KLineEntity());
            DataHelper.calculate(datas);
        }
        return datas;
    }


    /**
     * 分页查询
     *
     * @param context
     * @param offset  开始的索引
     * @param size    每次查询的条数
     */
    public static List<KLineEntity> getData(Context context, int offset, int size) {
        List<KLineEntity> all = getALL(context);
        List<KLineEntity> data = new ArrayList<>();
        int start = Math.max(0, all.size() - 1 - offset - size);
        int stop = Math.min(all.size(), all.size() - offset);
        for (int i = start; i < stop; i++) {
            data.add(all.get(i));
        }
        return data;
    }

}


