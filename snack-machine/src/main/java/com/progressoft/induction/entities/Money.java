package com.progressoft.induction.entities;

import com.progressoft.induction.data.DataSource;
import com.progressoft.induction.errorsex.InvalidInputException;

import java.math.BigDecimal;

public class Money {

    private BigDecimal decimal;
    private double currentMoney;
    public static final double ZERO = 0;
    public static final double QUARTER_DINAR = 0.25;
    public static final double HALF_DINAR = 0.50;
    public static final double DINAR = 1;

    public Money(BigDecimal valueOf) {
        if(valueOf != null) {
            decimal = valueOf;
        }

        if(valueOf.doubleValue() == -1) {
            throw new IllegalArgumentException("You Must Insert Money");
        }

        if(valueOf.doubleValue() == Money.DINAR) {
            currentMoney = valueOf.doubleValue();
            DataSource.insertMoney(1.0);
        } else if(valueOf.doubleValue() == Money.HALF_DINAR) {
            currentMoney = valueOf.doubleValue();
            DataSource.insertMoney(0.50);
        } else if(valueOf.doubleValue() == Money.QUARTER_DINAR) {
            currentMoney = valueOf.doubleValue();
            DataSource.insertMoney(0.25);
        } else {
//            throw new InvalidInputException("You Must Insert Money To Buy a Snake");
        }
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public Money add(Money halfDinar)  {
        System.out.println(decimal.doubleValue() + halfDinar.getDecimal().doubleValue());
        return new Money(BigDecimal.valueOf(decimal.doubleValue() + halfDinar.getDecimal().doubleValue()));
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public boolean isLessThan(Money price)  {
        return getDecimal().doubleValue() < price.getDecimal().doubleValue();
    }

    public Money subtract(Money money) throws IllegalArgumentException {
        if(money == null) {
            throw new IllegalArgumentException("You Must Insert Money");
        }

        if(getCurrentMoney() - money.getCurrentMoney() < 0) {
            throw new IllegalArgumentException("Invalid Params");
        }


        System.out.println(getCurrentMoney() - money.getCurrentMoney());
        return new Money(BigDecimal.valueOf(getCurrentMoney() - money.getCurrentMoney()));
    }
}
