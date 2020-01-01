package com.jack.paham.model;

public class Score {

    private String name, address;
    private int nilai;

    public Score() {
    }

    public Score(String name, String address, int nilai) {
        this.name = name;
        this.address = address;
        this.nilai = nilai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}
