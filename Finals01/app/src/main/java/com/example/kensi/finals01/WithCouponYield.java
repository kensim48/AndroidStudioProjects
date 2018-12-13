package com.example.kensi.finals01;

public class WithCouponYield implements YieldCalculation {

   public double yieldToMaturity(Bond bond) {
        double rup = Math.pow(10, -5);
        double rdown = Math.pow(10, -5);
        double rmid;
        double delta = rup - rdown;

        while (delta > Math.pow(10, -5)) {
            rmid = 0.5 * (rup + rdown);
            double frup = func(bond, rup);
            double frdown = func(bond, rdown);
            double frmid = func(bond, rmid);

            if (frmid / frup > 0) {
                rup = rmid;
            } else if (frmid / frdown > 0) {
                rdown = rmid;
            }
            delta = rup - rdown;
        }
        return 0.5*(rup+rdown);
    }


    double func(Bond bond, double r) {
        Double face = bond.getFaceValue();
        Double sell = bond.getSellingPrice();
        Double dura = bond.getDuration();
        Double inte = bond.getInterestPayment();


        double div = (Math.pow(Double.valueOf(1) / (Double.valueOf(1 + r)), dura)) / r;
        double div2 = face / (Math.pow(Double.valueOf(1) + r, dura));

        double f = sell - (inte * div) - div2;
        return f;
    }

}
