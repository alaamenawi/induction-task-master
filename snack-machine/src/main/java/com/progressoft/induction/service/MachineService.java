package com.progressoft.induction.service;

import com.progressoft.induction.data.DataSource;
import com.progressoft.induction.entities.*;
import com.progressoft.induction.errorsex.AccsessDenideException;
import com.progressoft.induction.errorsex.InvalidInputException;
import com.progressoft.induction.errorsex.NotFoundExcepion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;

import static com.progressoft.induction.data.DataSource.getCurrentMachine;

public class MachineService {

    private int finalGums = 0;
    private int finalChips = 0;
    private int finalChocolate = 0;
    private double currentAmmount;
    private double commonPricesp[]={
      0.25,0.50,1.00,5.00,10.00
    };

    public MachineService(){

    }

    public double getAmount(User user) throws AccsessDenideException {
        if(user.getType() ==UserType.ADMIN) {
            return getCurrentMachine().getAmount();
        }

            throw new AccsessDenideException("You can't get the ammount only the admin can get it");

    }

    public void addSnack(Snack snack) {
        getCurrentMachine().setSnack(snack);
        DataSource.addSnack(snack);
    }

    public void addUser(User user) throws AccsessDenideException{
        if (user.getType() == UserType.ADMIN)
            DataSource.addUser(user);

        throw new AccsessDenideException("you must be admin to add new users");


    }

    public Snack buySnack(double price, String name , User user) throws InvalidInputException, NotFoundExcepion {
        if(user.getType() == UserType.ADMIN) {
            printSnackDetails(DataSource.getSnackByName(name));
            return DataSource.getSnackByName(name);
        } else {
            if(price < commonPricesp[0]){
                throw new InvalidInputException("you can't buy Snacks at this price");
            } else if(price > commonPricesp[4]) {
                throw new InvalidInputException("The Maximum Price is 10 JD");
            } else {
                if(!name.equals("")) {
                    printSnackDetails(DataSource.getSnackByName(name));
                    Snack result =  DataSource.getSnackByName(name);

                    if(price > result.getPrice()) {
                        double finalPrice = price - result.getPrice();
                        System.out.println("You Recived : " + finalPrice + " JD");
                    } else {
                        throw new InvalidInputException("The Price is : " + result.getPrice() + " Your Money is not enough");
                    }

                    this.currentAmmount = getCurrentMachine().getAmount();
                    this.currentAmmount += price;
                    getCurrentMachine().setAmount(currentAmmount);

                    return result;

                } else {
                    throw new InvalidInputException("You Should Provide The Snake Name");
                }
            }
        }
    }

    public void printSnackDetails(Snack snack) {
        System.out.println("The Snack Details is : " + snack.toString());
    }

    public boolean moneyInside() {
        return getCurrentMachine().getAmount() > 0;
    }

    public void insertMoney(double money) throws InvalidInputException {
        if(money == Money.DINAR) {
            getCurrentMachine().setAmount(getCurrentMachine().getAmount() + 1.0);
        } else if(money == Money.HALF_DINAR) {
            getCurrentMachine().setAmount(getCurrentMachine().getAmount() + 0.50);
        } else if(money == Money.QUARTER_DINAR) {
            getCurrentMachine().setAmount(getCurrentMachine().getAmount() + 0.25);
        } else {
            throw new InvalidInputException("You Must Insert Money To Buy a Snake");
        }
    }

    public boolean moneyInTransaction() {
        return false;
    }

    public void insertMoney(Money money) {
        DataSource.getCurrentMachine().setAmount(DataSource.getCurrentMachine().getAmount() + money.getDecimal().doubleValue());
    }

    public Money buySnack(SnackType type) throws IOException {
        Snack result = null;
        double number = 0;
        for(int i = 0; i < DataSource.getCurrentMachine().getSnacks().size(); i++) {
            if(DataSource.getCurrentMachine().getSnacks().get(i).getType() == type) {
                result = DataSource.getCurrentMachine().getSnacks().get(i);
                number += DataSource.getCurrentMachine().getSnacks().get(i).getPrice();
            }
        }

        Money money = new Money(BigDecimal.valueOf(result.getPrice()) );
        createHtmlPage(money, number);

        return money;
    }

    public Machine chocolates() {
        for(int i = 0; i < DataSource.getCurrentMachine().getSnacks().size(); i++) {
            if(DataSource.getCurrentMachine().getSnacks().get(i).getType() == SnackType.CHOCOLATE) {
                finalChocolate++;
            }
        }
        DataSource.getCurrentMachine().setCurentQuantity(finalChocolate);
        return DataSource.getCurrentMachine();
    }

    public Machine chips() {
        for(int i = 0; i < DataSource.getCurrentMachine().getSnacks().size(); i++) {
            if(DataSource.getCurrentMachine().getSnacks().get(i).getType() == SnackType.CHIPS) {
                finalChips++;
            }
        }
        DataSource.getCurrentMachine().setCurentQuantity(finalChips);
        return DataSource.getCurrentMachine();
    }

    public Machine chewingGums() {
        for(int i = 0; i < DataSource.getCurrentMachine().getSnacks().size(); i++) {
            if(DataSource.getCurrentMachine().getSnacks().get(i).getType() == SnackType.CHEWING_GUM) {
                finalGums++;
            }
        }
        DataSource.getCurrentMachine().setCurentQuantity(finalGums);
        return DataSource.getCurrentMachine();
    }

    public void createHtmlPage(Money money , double price) throws IOException {
        String content = "<html>" +
                            "<head>" +
                                "<title>Machine Service Main Page</title>" +
                                "<meta charset=\"utf-8\">" +
                            "</head>"+
                            "<body>" +
                                "<center>" +
                                    "<h1>" +
                                        "Types Of Snakes : " + SnackType.CHIPS + " . " + SnackType.CHOCOLATE + " , " + SnackType.CHEWING_GUM +
                                    "</h1>" +
                                "</center>" +
                                "<br>" +
                                "<center><h2>" +
                                    "The Ammount At The Machine Is : " + DataSource.getCurrentMachine().getAmount() +
                                "</h2></center>" +
                                "<br>" +
                                "The Money Of The Current Transition : " + money.getDecimal().doubleValue() +
                                "<br>" +
                                "<h2>" +
                                    "Chocolate Number is : " + finalChocolate + " , " + "Chips Number : " + finalChips + " , " + "Gums Number : " + finalGums +
                                "</h2>" +
                                "<br>" +
                                "<h2>" +
                                    "The Result Price is : " + price +
                                "</h2>" +
                                "<br>" +
                                "<input type=\"text\" placeholder=\"Please insert The Money\">" +
                                "<input type=\"submit\" value=\"submit\"" +
                            "</body>" +
                        "</html>";
        Document doc = Jsoup.parse(content);
        doc.body().addClass("body-styles-cls");
        doc.body().appendElement("div");
        System.out.println(doc.toString());

        File f = new File("source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(content);

        bw.close();

        Desktop.getDesktop().browse(f.toURI());

    }
}
