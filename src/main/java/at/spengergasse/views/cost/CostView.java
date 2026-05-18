package at.spengergasse.views.cost;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.OptionalDouble;

@PageTitle("Cost")
@Route("empty")
@Menu(order = 1, icon = LineAwesomeIconUrl.DOLLAR_SIGN_SOLID)
public class CostView extends VerticalLayout {

    public CostView() {
        setSpacing(true);

        H2 title = new H2("Transfer Kosten");

        Component card0 = getCard("Inland", 0, OptionalDouble.empty());
        Component card1 = getCard("Deutschland", 2, OptionalDouble.of(1000));
        Component card2 = getCard("Europa", 4, OptionalDouble.of(600));
        Component card3 = getCard("USA", 6, OptionalDouble.of(400));
        Component card4 = getCard("Drittländer", 15, OptionalDouble.of(200));

        FlexLayout cards = new FlexLayout(card0, card1, card2, card3, card4);
        cards.setWidthFull();
        cards.setJustifyContentMode(JustifyContentMode.CENTER);
        cards.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        Paragraph info = new Paragraph("Überweisungen werden am nächsten Tag durchgeführt");
        info.setWidth("100%");
        info.getStyle()
                .set("text-align", "center");

        add(HomeView.getHeader(), title, cards, info );


    }

    private VerticalLayout getCard(String country, double cost, OptionalDouble max) {
        H2 zone = new H2(country);
        Paragraph costText = new Paragraph("Kosten" + cost + "Euro");

        Paragraph maxText = max.isPresent()
                ? new Paragraph("maximaler Betrag: " + max.getAsDouble() + "Euro")
                : new Paragraph("maximaler Betrag: unlimitiert");

        VerticalLayout card = new VerticalLayout(zone, costText, maxText);
        card.setWidth("300px");
        card.setPadding(true);
        card.setMargin(false);

        card.getStyle()
                .set("border", "1px solid red")
                .set("border-radius", "10px")
                .set("margin", "10px");

        return card;
    }

}
