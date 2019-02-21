package com.example.brstefan.futurebank;

import android.util.Log;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;


public class Expense {

    private int Suma;
    private int Tip;
    private String Moneda;
    private Timestamp Data;
    private String Categorie;

    public void Expense(){
        //empty constructor required
    }

    public void Expense(int suma, int tip, String Moneda, Timestamp Data, String Categorie)
    {
        this.Suma=suma;
        this.Tip=tip;
        this.Moneda=Moneda;
        this.Data=Data;
        this.Categorie=Categorie;
    }

    public int getTip() {
        return Tip;
    }

    public int getSuma() {
        return Suma;
    }

    public String getMoneda() {
        return Moneda;
    }

    public String getData() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return format.format(Data.toDate().getTime());
    }

    public String getCategorie() {
        return Categorie;
    }
}
