package com.github.fujianlian.klinechart;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.fujianlian.klinechart.entity.IKLine;

import java.io.Serializable;

/**
 * K线实体
 * Created by tifezh on 2016/5/16.
 */
public class KLineEntity implements IKLine, Parcelable {

    public String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return Date;
    }

    @Override
    public float getOpenPrice() {
        return Open;
    }

    @Override
    public float getHighPrice() {
        return High;
    }

    @Override
    public float getLowPrice() {
        return Low;
    }

    @Override
    public float getClosePrice() {
        return Close;
    }

    @Override
    public float getMA5Price() {
        return MA5Price;
    }

    @Override
    public float getMA10Price() {
        return MA10Price;
    }

    @Override
    public float getMA20Price() {
        return MA20Price;
    }

    @Override
    public float getMA30Price() {
        return MA30Price;
    }

    @Override
    public float getMA60Price() {
        return MA60Price;
    }

    @Override
    public float getDea() {
        return dea;
    }

    @Override
    public float getDif() {
        return dif;
    }

    @Override
    public float getMacd() {
        return macd;
    }

    @Override
    public float getK() {
        return k;
    }

    @Override
    public float getD() {
        return d;
    }

    @Override
    public float getJ() {
        return j;
    }

    @Override
    public float getR() {
        return r;
    }

    @Override
    public float getRsi() {
        return rsi;
    }

    @Override
    public float getUp() {
        return up;
    }

    @Override
    public float getMb() {
        return mb;
    }

    @Override
    public float getDn() {
        return dn;
    }

    @Override
    public float getVolume() {
        return Volume;
    }

    @Override
    public float getMA5Volume() {
        return MA5Volume;
    }

    @Override
    public float getMA10Volume() {
        return MA10Volume;
    }

    public int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String Date;
    public String timestamp;
    public String t;
    public float Open;
    public float High;
    public float Low;
    public float Close;
    public float Volume;

    public float MA5Price;

    public float MA10Price;

    public float MA20Price;

    public float MA30Price;

    public float MA60Price;

    public float dea;

    public float dif;

    public float macd;

    public float k;

    public float d;

    public float j;

    public float r;

    public float rsi;

    public float up;

    public float mb;

    public float dn;

    public float MA5Volume;

    public float MA10Volume;

    public void setDate(String date) {
        Date = date;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setOpen(float open) {
        Open = open;
    }

    public void setHigh(float high) {
        High = high;
    }

    public void setLow(float low) {
        Low = low;
    }

    public void setClose(float close) {
        Close = close;
    }

    public void setVolume(float volume) {
        Volume = volume;
    }

    public void setMA5Price(float MA5Price) {
        this.MA5Price = MA5Price;
    }

    public void setMA10Price(float MA10Price) {
        this.MA10Price = MA10Price;
    }

    public void setMA20Price(float MA20Price) {
        this.MA20Price = MA20Price;
    }

    public void setMA30Price(float MA30Price) {
        this.MA30Price = MA30Price;
    }

    public void setMA60Price(float MA60Price) {
        this.MA60Price = MA60Price;
    }

    public void setDea(float dea) {
        this.dea = dea;
    }

    public void setDif(float dif) {
        this.dif = dif;
    }

    public void setMacd(float macd) {
        this.macd = macd;
    }

    public void setK(float k) {
        this.k = k;
    }

    public void setD(float d) {
        this.d = d;
    }

    public void setJ(float j) {
        this.j = j;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setRsi(float rsi) {
        this.rsi = rsi;
    }

    public void setUp(float up) {
        this.up = up;
    }

    public void setMb(float mb) {
        this.mb = mb;
    }

    public void setDn(float dn) {
        this.dn = dn;
    }

    public void setMA5Volume(float MA5Volume) {
        this.MA5Volume = MA5Volume;
    }

    public void setMA10Volume(float MA10Volume) {
        this.MA10Volume = MA10Volume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeInt(this.type);
        dest.writeString(this.Date);
        dest.writeFloat(this.Open);
        dest.writeFloat(this.High);
        dest.writeFloat(this.Low);
        dest.writeFloat(this.Close);
        dest.writeFloat(this.Volume);
        dest.writeFloat(this.MA5Price);
        dest.writeFloat(this.MA10Price);
        dest.writeFloat(this.MA20Price);
        dest.writeFloat(this.MA30Price);
        dest.writeFloat(this.MA60Price);
        dest.writeFloat(this.dea);
        dest.writeFloat(this.dif);
        dest.writeFloat(this.macd);
        dest.writeFloat(this.k);
        dest.writeFloat(this.d);
        dest.writeFloat(this.j);
        dest.writeFloat(this.r);
        dest.writeFloat(this.rsi);
        dest.writeFloat(this.up);
        dest.writeFloat(this.mb);
        dest.writeFloat(this.dn);
        dest.writeFloat(this.MA5Volume);
        dest.writeFloat(this.MA10Volume);
    }

    public KLineEntity() {
    }

    protected KLineEntity(Parcel in) {
        this.address = in.readString();
        this.type = in.readInt();
        this.Date = in.readString();
        this.Open = in.readFloat();
        this.High = in.readFloat();
        this.Low = in.readFloat();
        this.Close = in.readFloat();
        this.Volume = in.readFloat();
        this.MA5Price = in.readFloat();
        this.MA10Price = in.readFloat();
        this.MA20Price = in.readFloat();
        this.MA30Price = in.readFloat();
        this.MA60Price = in.readFloat();
        this.dea = in.readFloat();
        this.dif = in.readFloat();
        this.macd = in.readFloat();
        this.k = in.readFloat();
        this.d = in.readFloat();
        this.j = in.readFloat();
        this.r = in.readFloat();
        this.rsi = in.readFloat();
        this.up = in.readFloat();
        this.mb = in.readFloat();
        this.dn = in.readFloat();
        this.MA5Volume = in.readFloat();
        this.MA10Volume = in.readFloat();
    }

    public static final Parcelable.Creator<KLineEntity> CREATOR = new Parcelable.Creator<KLineEntity>() {
        @Override
        public KLineEntity createFromParcel(Parcel source) {
            return new KLineEntity(source);
        }

        @Override
        public KLineEntity[] newArray(int size) {
            return new KLineEntity[size];
        }
    };
}
