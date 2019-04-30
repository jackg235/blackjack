package Strategy;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BasicStrategy {

    private Document doc;
    private Elements table;
    private Element strategy;
    private String strategyURL;
    private int numDecks;
    private int standValue;

    public static void main(String[] args) {
        BasicStrategy b = new BasicStrategy(1, 17);
        b.getStrategyTable();
    }

    public BasicStrategy(int numDecks, int standValue) {

        this.numDecks = numDecks;
        this.standValue = standValue;
        try {
            strategyURL = "https://wizardofodds.com/games/blackjack/strategy/calculator/";
            doc = Jsoup.connect(strategyURL).get();
            table = doc.getElementsByTag("script");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String[][] getStrategyTable() {
        String[][] strategies = new String[36][10];
        String charts = table.get(6).toString();
        charts = charts.replaceAll(",", " ");
        charts = charts.replaceAll("\"", " ");
        charts = charts.replaceAll("RH", "H");
        charts = charts.replaceAll("RS", "S");
        charts = charts.replaceAll("QH", "H");
        charts = charts.replaceAll("QS", "S");
        charts = charts.replaceAll("QD", "DH");

        int counter = 0;
        String[] flat = new String[12 * 36];
        String[] split = charts.split("// 2     3     4     5     6     7     8     9     T     A    ET    EA");

        String splitSection = "";

        if (numDecks == 1) {
            if (standValue <= 17) {
                splitSection = split[1];
            } else {
                splitSection = split[2];
            }
        } else if (numDecks == 2) {
            if (standValue <= 17) {
                splitSection = split[3];
            } else {
                splitSection = split[4];
            }
        } else {
            if (standValue <= 17) {
                splitSection = split[5];
            } else {
                splitSection = split[6];
                splitSection = splitSection.split("var")[0];
            }
        }

        splitSection = splitSection.trim();
        String[] splitRow = splitSection.split("\\[|\\]");
        for (int i = 1; i < splitRow.length - 1; i = i + 2) {
            String[] arrayVals = splitRow[i].split("    ");
            for (int j = 0; j < arrayVals.length; j++) {
                if (j < 10) {
                    flat[counter] = arrayVals[j].trim();
                    counter++;
                }
            }
        }

        counter = 0;
        for (int i = 0; i < strategies.length; i++) {
            for (int j = 0; j < strategies[0].length; j++) {
                strategies[i][j] = flat[counter];
                counter++;
            }
        }
        return strategies;
    }
}
