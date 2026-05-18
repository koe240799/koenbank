package at.spengergasse.views.home;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(true);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        Paragraph p1 = new Paragraph("Die Koen Bank ist ein modernes Finanzinstitut, das sich durch Vertrauen, Innovation und Kundennähe auszeichnet. Sie bietet sowohl Privat- als auch Geschäftskunden ein umfassendes Spektrum an Finanzdienstleistungen – von klassischen Konten und Krediten bis hin zu individuellen Anlagelösungen und digitalem Banking.");
        Paragraph p2 = new Paragraph("Im Mittelpunkt der Koen Bank steht der Anspruch, finanzielle Lösungen einfach, transparent und zugänglich zu gestalten. Durch den Einsatz moderner Technologien ermöglicht sie ihren Kundinnen und Kunden, Bankgeschäfte jederzeit und von überall aus sicher zu erledigen. Gleichzeitig legt die Bank großen Wert auf persönliche Beratung, um maßgeschneiderte Strategien für unterschiedliche Lebenssituationen zu entwickeln.");
        Paragraph p3 = new Paragraph("Nachhaltigkeit und Verantwortung spielen ebenfalls eine wichtige Rolle: Die Koen Bank engagiert sich für langfristig orientierte Investitionen und unterstützt Projekte, die sowohl wirtschaftlichen als auch gesellschaftlichen Mehrwert schaffen.");
        Paragraph p4 = new Paragraph("Mit ihrer Kombination aus Tradition und Innovation versteht sich die Koen Bank als verlässlicher Partner für finanzielle Entscheidungen – heute und in der Zukunft.");
        styleParagraph(p1);
        styleParagraph(p2);
        styleParagraph(p3);
        styleParagraph(p4);
        add(getHeader(), p1, p2, p3, p4);


    }

    public static Component getHeader() {
        Image img = new Image("images/logo.png", "logo");
        img.setWidth("200px");

        H2 h2 = new H2("...The best Bank...");

        h2.getStyle()
                .set("font-size", "2.25rem")
                .set("margin", "0")
                .set("text-align", "center");

        VerticalLayout header = new VerticalLayout(img, h2);
        header.setAlignItems(Alignment.CENTER);
        header.setJustifyContentMode(JustifyContentMode.CENTER);
        return header;
    }

    public void styleParagraph(Paragraph p) {
        p.getStyle()
                .setMaxWidth("75%");


    }

}
