package Strategy;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BasicStrategy {
    
    private Document doc;
    private Elements table;
    private Element strategy;
    private Elements movies;
    private String strategyURL;
    
    public static void main(String[] args) {
        BasicStrategy b = new BasicStrategy(4, 17);
        b.getStrategyTable();
    }
    public BasicStrategy(int numDecks, int standValue) {
        try {
            strategyURL = "https://wizardofodds.com/games/blackjack/strategy/calculator/";
            doc = Jsoup.connect(strategyURL).get();
            table = doc.getElementsByClass("has-backgroundgradient");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void getStrategyTable() {
        Elements tr = strategy.select("tr");
        for (Element el : table) {
            System.out.println(el);
            
        }
    }

}
