package com.progressoft.induction;

import com.progressoft.induction.entities.*;
import com.progressoft.induction.errorsex.AccsessDenideException;
import com.progressoft.induction.errorsex.InvalidInputException;
import com.progressoft.induction.errorsex.NotFoundExcepion;
import com.progressoft.induction.service.MachineService;
import org.junit.Test;



public class MachineTest {

    @Test
    public void testCaseWhenValidInputs() throws AccsessDenideException {

        //Initialize the inputs
        User user = new User(1,"Ahmad" , UserType.ADMIN);
        Snack snack = new Snack(1,"Chocolate" , 0.50, SnackSize.SMAUL, SnackType.SWEETS);

        MachineService service = new MachineService();

        service.addUser(user);
        service.addSnack(snack);

        try {
            service.buySnack(0.50 , "Chocolate" , user);
        } catch (InvalidInputException | NotFoundExcepion e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaseWhenUserTypeIsCustomer() throws AccsessDenideException {

        //Initialize the inputs
        User user = new User(1,"Ahmad" , UserType.CUSTOMER);
        Snack snack = new Snack(1,"Chocolate" , 0.50, SnackSize.SMAUL, SnackType.SWEETS);

        MachineService service = new MachineService();

        service.addUser(user);
        service.addSnack(snack);

        try {
            service.buySnack(0.50 , "Chocolate" , user);
        } catch (InvalidInputException | NotFoundExcepion e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaseWhenWrongPrice() throws AccsessDenideException {

        //Initialize the inputs
        User user = new User(1,"Ahmad" , UserType.CUSTOMER);
        Snack snack = new Snack(1,"Chocolate" , 0.00, SnackSize.SMAUL, SnackType.SWEETS);

        MachineService service = new MachineService();

        service.addUser(user);
        service.addSnack(snack);

        try {
            service.buySnack(0.00 , "Chocolate" , user);
        } catch (InvalidInputException | NotFoundExcepion e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaseWhenInvalidSnackName() throws AccsessDenideException {
        //Initialize the inputs
        User user = new User(1,"Ahmad" , UserType.CUSTOMER);
        Snack snack = new Snack(1,"" , 0.00, SnackSize.SMAUL, SnackType.SWEETS);

        MachineService service = new MachineService();

        service.addUser(user);
        service.addSnack(snack);

        try {
            service.buySnack(0.50 , "Chocolate" , user);
        } catch (InvalidInputException | NotFoundExcepion e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaseWhenRecivedMoney() throws AccsessDenideException {
        //Initialize the inputs
        User user = new User(1,"Ahmad" , UserType.CUSTOMER);
        Snack snack = new Snack(1,"Chipes" , 5.00, SnackSize.SMAUL, SnackType.SWEETS);

        MachineService service = new MachineService();

        service.addUser(user);
        service.addSnack(snack);

        try {
            service.buySnack(10.00 , "Chipes" , user);
        } catch (InvalidInputException | NotFoundExcepion e) {
            e.printStackTrace();
        }
    }

}
