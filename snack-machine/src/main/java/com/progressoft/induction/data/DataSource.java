package com.progressoft.induction.data;

import com.progressoft.induction.entities.Machine;
import com.progressoft.induction.entities.Snack;
import com.progressoft.induction.entities.User;
import com.progressoft.induction.errorsex.NotFoundExcepion;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static Machine currentMachine;
    private static List<Snack> snacks;
    private static List<User> users;

    static {
        snacks=new ArrayList<>(20);
        users=new ArrayList<>();
        currentMachine = new Machine();
    }

    private DataSource(){

    }

        public static Snack getSnackByName(String name) throws NotFoundExcepion {
            for (int i = 0; i < snacks.size(); i++)
                if (snacks.get(i).getName().equals(name))
                    return snacks.get(i);

                    throw new NotFoundExcepion("Data Not Found");

        }


        public static User getUserByName(String name) throws NotFoundExcepion {
            for(int i=0;i<users.size();i++)
                if (users.get(i).getName().equals(name))
                    return users.get(i);

                throw new NotFoundExcepion("Users Not Found");

    }

    public static void addSnack(Snack snack) {
        snacks.add(snack);
        currentMachine.setSnack(snack);
    }


    public static void addUser(User user) {
        users.add(user);
    }

    public static Machine getCurrentMachine(){
        return DataSource.currentMachine;
    }

    public static double getAmmount(){
        return currentMachine.getAmount();
    }

    public static void insertMoney(double money) {
        currentMachine.setAmount(currentMachine.getAmount() + money);
    }

}
