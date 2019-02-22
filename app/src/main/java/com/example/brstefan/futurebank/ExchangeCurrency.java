package com.example.brstefan.futurebank;

import java.util.ArrayList;
import java.util.List;

public class ExchangeCurrency {

    private RatesEntity rates;

    public List<Exchange> getExchangeList(){
        List<Exchange>exchangeList=new ArrayList<>();
        exchangeList.add(new Exchange("AUD",rates.getAUD()));
        exchangeList.add(new Exchange("CAD",rates.getCAD()));
        exchangeList.add(new Exchange("CHF",rates.getCHF()));
        exchangeList.add(new Exchange("CLF",rates.getCLF()));
        exchangeList.add(new Exchange("CZK",rates.getCZK()));
        exchangeList.add(new Exchange("DKK",rates.getDKK()));
        exchangeList.add(new Exchange("DZD",rates.getDZD()));
        exchangeList.add(new Exchange("EGP",rates.getEGP()));
        exchangeList.add(new Exchange("EUR",rates.getEUR()));
        exchangeList.add(new Exchange("GBP",rates.getGBP()));
        exchangeList.add(new Exchange("JPY",rates.getJPY()));
        exchangeList.add(new Exchange("USD",rates.getUSD()));
        exchangeList.add(new Exchange("RON",rates.getRON()));
        return exchangeList;
    }

    public ExchangeCurrency(RatesEntity rates) {
        this.rates = rates;
    }

    public RatesEntity getRates() {
        return rates;
    }

    public static class RatesEntity{
        private double AUD;
        private double CAD;
        private double CHF;
        private double CLF;
        private double CZK;
        private double DKK;
        private double DZD;
        private double EGP;
        private double EUR;
        private double GBP;
        private double JPY;
        private double USD;
        private double RON;

        public double getRON() {
            return RON;
        }

        public double getCAD() {
            return CAD;
        }

        public double getCHF() {
            return CHF;
        }

        public double getCLF() {
            return CLF;
        }

        public double getCZK() {
            return CZK;
        }

        public double getDKK() {
            return DKK;
        }

        public double getDZD() {
            return DZD;
        }

        public double getEGP() {
            return EGP;
        }

        public double getEUR() {
            return EUR;
        }

        public double getGBP() {
            return GBP;
        }

        public double getJPY() {
            return JPY;
        }

        public double getUSD() {
            return USD;
        }

        public double getAUD() {
            return AUD;
        }

        public void setAUD(double AUD) {
            this.AUD = AUD;
        }
    }
}
