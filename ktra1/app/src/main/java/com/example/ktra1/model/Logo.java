package com.example.ktra1.model;

public class Logo {
    private int img;
    private String name;
    private  double desc,price;
    private boolean wifi, dieuhoa, maygiat;

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isDieuhoa() {
        return dieuhoa;
    }

    public void setDieuhoa(boolean dieuhoa) {
        this.dieuhoa = dieuhoa;
    }

    public boolean isMaygiat() {
        return maygiat;
    }

    public void setMaygiat(boolean maygiat) {
        this.maygiat = maygiat;
    }

    public Logo() {
    }

    public Logo(int img, String name, double desc, double price) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDesc() {
        return desc;
    }

    public void setDesc(double desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
