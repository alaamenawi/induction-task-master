package com.progressoft.induction;

import com.progressoft.induction.entities.Money;
import com.progressoft.induction.service.MachineService;

import java.io.IOException;
import java.math.BigDecimal;

public class MachineClass {

    public static void main(String [] args) throws IOException {
        new MachineService().createHtmlPage(new Money(BigDecimal.valueOf(10)) , 10.0);
    }
}
