package at.spengergasse.views.transaction;

import at.spengergasse.domain.Account;
import at.spengergasse.domain.AccountException;
import at.spengergasse.service.BankService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDate;

@PageTitle("Transaction")
@Route("empty2")
@Menu(order = 2, icon = LineAwesomeIconUrl.PAYPAL)
public class TransactionView extends VerticalLayout {
    private final Button removeAll = new Button("Remove all");
    private final Button add10Accounts = new Button("Add 10 accounts");
    private final Button addWrongAccount = new Button("Add wrong account");
    private final Button raiseBalance = new Button("Raise balance");

    private final Grid<Account> grid = new Grid<>(Account.class, false);
    private final BankService bankService;

    public TransactionView(@Autowired BankService bankService) {
        this.bankService = bankService;
        setSpacing(true);

        grid.addColumn(a -> a.getAccountId())
                .setHeader("Account ID")
                .setSortable(true);
        grid.addColumn(a -> a.getFirstName())
                .setHeader("First Name")
                .setSortable(true);
        Image imgCalender = new Image("icons/kalender.png", "Calender");
        imgCalender.setWidth("25px");
        grid.addColumn(a -> a.getOpeningDate())
                .setHeader(new HorizontalLayout(imgCalender, new Span("Date")))
                .setSortable(true);
        grid.addColumn(a -> a.getAccountType())
                .setHeader("Account Type")
                .setSortable(true);
        Image imgEuro = new Image("icons/euro.png", "Euro");
        imgEuro.setWidth("25px");
        grid.addColumn(a -> String.format("%.2f", a.getBalance()))
                .setHeader(new HorizontalLayout(imgEuro, new Span("Balance")))
                .setSortable(true);
//        grid.addColumn(a -> a.getActive())
//                .setHeader("Active")
//                .setSortable(true);
//        grid.addColumn(a -> a.getActive() ? "Y" : "N")
//                .setHeader("Active")
//                .setSortable(true);
        grid.addComponentColumn(a -> {

            Checkbox cb = new Checkbox(a.getActive());
            cb.setReadOnly(true);
            return cb;
        })
                .setHeader("Active")
                .setSortable(true);

        setSizeFull();
        grid.setSizeFull();
        removeAll.addClickListener(e -> removeAllAccounts());
        add10Accounts.addClickListener(e -> add10Accounts());
        addWrongAccount.addClickListener(e -> addWrongAccount());
        raiseBalance.addClickListener(e -> raiseBalance());
        HorizontalLayout buttons = new HorizontalLayout(removeAll, add10Accounts, addWrongAccount, raiseBalance);
        add(buttons, grid);
        reload();

    }

    private void addWrongAccount() {
        try{
            Account a = new Account("Fritz", LocalDate.now(), "Savings" , -2000.0, true);
            bankService.addAccount(a);
            reload();
        }catch (AccountException e){
            Notification.show(e.getMessage());
        }
    }

    private void raiseBalance() {
        try{
            bankService.raiseBalance10();
            raiseBalance.setEnabled(false);
            reload();
        }catch(AccountException e){
            Notification.show(e.getMessage());
        }
    }

    private void add10Accounts() {
        try{
            bankService.fillTestData(10);
            removeAll.setEnabled(true);
            raiseBalance.setEnabled(true);
            reload();

        }catch (AccountException e){
            Notification.show(e.getMessage());
        }
    }

    private void removeAllAccounts() {
        try{
            bankService.removeAllAccounts();
            removeAll.setEnabled(false);
            reload();

        }catch (AccountException e){
            Notification.show(e.getMessage());
        }
    }

    private void reload() {
        grid.setItems(bankService.findAll());
    }

}
