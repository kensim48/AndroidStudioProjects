package com.example.norman_lee.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    double delta = 1e-10;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkDefaultExchangeRate(){
        assertEquals( 2.95,
                ExchangeRate.calculateExchangeRate(),
                delta);
    }

    //TODO 5.4 Write unit tests to check calculateExchangeRate
    @Test(expected=ArithmeticException.class)
    public void exchangeRate_Infinity(){
        ExchangeRate.calculateExchangeRate("0.0","1.0");
    }

    @Test(expected=NumberFormatException.class)
    public void exchangeRate_EmptyString(){
        ExchangeRate.calculateExchangeRate("1000","");
    }

    @Test
    public void exchangeRate_Check(){
        assertEquals(0.2,
                ExchangeRate.calculateExchangeRate("5.0","1.0"),
                delta);
    }


}