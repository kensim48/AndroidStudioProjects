package com.example.kensi.finals01;

public class ZeroCouponYield implements YieldCalculation{

    public double yieldToMaturity (Bond bond){
        Double face = bond.getFaceValue();
        Double sell = bond.getSellingPrice();
        Double dura = bond.getDuration();

        Double power = Double.valueOf(1)/dura;
        Double r = Math.pow(face/sell,power) - Double.valueOf(1);
        return r;
    }
}
