package com.github.fujianlian.klinechart.entity;

public class OrderEntity {
    private long orderTimeStamp;
    private long orderStopTimeStamp;
    private String amount;
    private boolean isUp;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getOrderTimeStamp() {
        return orderTimeStamp;
    }

    public void setOrderTimeStamp(long orderTimeStamp) {
        this.orderTimeStamp = orderTimeStamp;
    }

    public long getOrderStopTimeStamp() {
        return orderStopTimeStamp;
    }

    public void setOrderStopTimeStamp(long orderStopTimeStamp) {
        this.orderStopTimeStamp = orderStopTimeStamp;
    }


    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderTimeStamp=" + orderTimeStamp +
                ", orderStopTimeStamp=" + orderStopTimeStamp +
                ", amount='" + amount + '\'' +
                ", isUp=" + isUp +
                '}';
    }
}
