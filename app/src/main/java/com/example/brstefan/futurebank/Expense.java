package com.example.brstefan.futurebank;

import android.util.Log;

public class Expense {

    private int Suma;
    private int Proprietar;
    //private String data;

    public Expense(int Suma,String data,int Proprietar){
        this.Suma=Suma;
        this.Proprietar=Proprietar;
        //this.data=data;
    }

    public Expense(){
        //empty constructor needed
    }

    public int getSuma() {
        Log.e("TAG",Suma + "");
        return Suma;
    }

    public int getProprietar() {
        return Proprietar;
    }

    /*public String getData() {
        return data;
    }*/
}
