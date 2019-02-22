package com.example.brstefan.futurebank;

public class Exchange{
    private String name;
    private double rate;

    public Exchange(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}

