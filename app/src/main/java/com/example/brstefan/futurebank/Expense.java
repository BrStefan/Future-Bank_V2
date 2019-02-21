package com.example.brstefan.futurebank;

import android.util.Log;

public class Expense {

    private int Suma;
    private String Moneda;

    public void Expense(){
        //empty constructor required
    }

    public void Expense(int suma,String Moneda)
    {
        this.Suma=suma;
        this.Moneda=Moneda;
    }

    public int getSuma() {
        return Suma;
    }

    public String getMoneda() {
        return Moneda;
    }
}
