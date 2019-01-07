package com.scientist.quickmvvm;

public class City {

    private String name;

    private String pinyin;

    private int code;

    private int population;


    public City(String name, String pinyin, int code, int population) {
        this.name = name;
        this.pinyin = pinyin;
        this.code = code;
        this.population = population;
    }


    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public int getCode() {
        return code;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    @Override
    public String toString() {
        return "code " + code + ", name " + name + ", pinyin " + pinyin + ", population " + population;
    }
}
