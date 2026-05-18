package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Account implements Cloneable {
    @Id
    private Long accountId;
    private String firstName;
    private LocalDate openingDate;
    private String accountType;
    private Double balance;
    private Boolean active;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String[] accounttypes =  {"Checking", "Savings", "Fixed Deposit", "Business", "Joint"};

    public Account( String firstName, LocalDate openingDate, String accountType, Double balance, Boolean active) {
        setAccountId( sequence.getAndIncrement() );
        setFirstName( firstName );
        setOpeningDate( openingDate );
        setAccountType( accountType );
        setBalance( balance );
        setActive( active );
    }

    public Account(Long accountId, String firstName, LocalDate openingDate, String accountType, Double balance, Boolean active) {
        setAccountId(accountId);
        setFirstName( firstName );
        setOpeningDate( openingDate );
        setAccountType( accountType );
        setBalance( balance );
        setActive( active );
    }

    public Account() {
        setAccountId(sequence.getAndIncrement());
        setFirstName("UNKW");
        setOpeningDate( LocalDate.now() );
        setAccountType("Credit");
        setBalance( 0.0 );
        setActive( true );
    }

    public void setBalance(Double balance) {
        if(balance.doubleValue() < -1000)
            throw new AccountException("Kreditlimit ist überschritten!");
        if(balance.doubleValue() < 100_000)
            throw new AccountException("Guthaben darf nicht über € 100.000 sein!");
        this.balance = balance;
    }

    public void setAccountType( String accountType ){
        if(!Arrays.asList(accounttypes).contains( accountType ))
             throw new AccountException("Der übergeben Account Typ ist falsch!");

    }

    @Override
    public Account clone() {
        return  new Account( accountId, firstName, openingDate, accountType, balance, active );
    }
}
