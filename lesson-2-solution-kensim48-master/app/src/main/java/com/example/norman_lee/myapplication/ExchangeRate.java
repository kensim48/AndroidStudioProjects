package com.example.norman_lee.myapplication;

public class ExchangeRate {

    public static double calculateExchangeRate(){
        return 2.95;
    }

    //TODO 3.9 Calculate the exchange rate
    public static double calculateExchangeRate(String A, String B)
            throws NumberFormatException, ArithmeticException {
        //NumberFormatException is thrown if string is empty
        double valueA = Double.valueOf(A);
        double valueB = Double.valueOf(B);
        if( Math.abs(valueA) <= 1e-10 ) throw new ArithmeticException();
        double exchangeRate = valueB/valueA;
        return exchangeRate;
    }


}
