package at.spengergasse.service;

import at.spengergasse.domain.Account;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BankService {
    private ArrayList<Account> accounts;

    public BankService(){
        accounts = new ArrayList<>();
    }

    public void fillTestData(int count){
        String[] accounttypes =  {"Checking", "Savings", "Fixed Deposit", "Business", "Joint"};
        Account a;
        Faker faker = new Faker();
        String firstName;
        LocalDate openingDate;
        String accountType;
        Double balance;
        Boolean active;

        for (int i = 0; i < count; i++) {
            firstName = faker.name().firstName();
            openingDate = LocalDate.now().minusDays((int)(Math.random()*3650));
            accountType = accounttypes[(int)(Math.random()*accounttypes.length -1)];
            balance = faker.number().randomDouble(2,0,50_000);
            active = faker.bool().bool();
            a = new Account(firstName, openingDate, accountType, balance, active);
            accounts.add(a);
        }
    }

    public ArrayList<Account> findAll(){
        ArrayList<Account> copy = new ArrayList<>(accounts);
        return copy;
    }

    @Override
    public String toString(){
        return accounts.stream()
                .map(s -> s.toString())
                .collect(Collectors.joining("\n"));
    }
}