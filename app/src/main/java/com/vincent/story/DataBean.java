package com.vincent.story;

public class DataBean {
    private String data;
    private boolean isSelected;
    private int type;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "data='" + data + '\'' +
                ", isSelected=" + isSelected +
                ", type=" + type +
                ", position=" + position +
                '}';
    }
}
