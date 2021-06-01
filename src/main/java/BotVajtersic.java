import com.vdurmont.emoji.EmojiParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class BotVajtersic {

    public static String die = " " + EmojiParser.parseToUnicode(":skull:");


    public static String getPhrases(String s) {


        String[] output = {"'Пошел ты нах' - Г.Александр",
                "'Они не эквивалентны' - Н.Семкин",
                "'Это не так работает' - Д.Барков",
                "'Небо голубое, а снег белый если не поссать' - Д.Барков",
                "'Нельзя покакать не пописав' - No name",
                "'Если бы мы знали что это такое, но мы не знаем что это такое'- Г.Александр",
                "'Главное знать, что считать' - Н.Сёмкин"};


        if (s.equals("цитата") || s.equals("Цитата")) {
            int r = (int) Math.floor(Math.random() * 7);
            s = output[r];
        }
        if (s.equals("Flip-Flop")) {
            s = "Write your Word, example : Word/Flip-Flop";
        }
        if (s.matches("[a-zA-Zа-яА-Я0-9\\s]+/Flip-Flop")) {
            s = Rotation(s);
        }
        if (s.equals("/start")) {
            s = "Здравствуй мой юный друг, я твой карманный Кудесник , который умеет выполнять различные задачи. Хочешь посмотреть фильм, но не" +
                    "знаешь какой... Не вопрос! Также доступные и другие полезные опции - тебе всего лишь нужно тыкнуть на кнопки которые вот вот вылезут на дисплее:)";
        }
        if (s.equals("Wheather") || s.equals("Wheather") || s.equals(Bot.weather)) {
            s = "Select your City like : London";
        }
        if (s.equals("Corona") || s.equals("Corona") || s.equals(Bot.corona)) {
            try {
                s = getCorona();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (s.equals("Kurs") || s.equals("Kurs") || s.equals(Bot.dollar)) {
            try {
                s = getValuta();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (s.matches("[0-9]+") || s.contains("-1")
                || s.contains("-2") || s.contains("-3")
                || s.contains("-4") || s.contains("-5")
                || s.contains("-6") || s.contains("-7")
                || s.contains("-8") || s.contains("-9")) {

            s = getBinary(s);
        }
        if (s.equals("New")) {
            try {
                s = Film.getFilm("New");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (s.equals("Comedy")) {
            try {
                s = Film.getFilm("Comedy");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (s.equals("Horror")) {
            try {
                s = Film.getFilm("Horror");
            } catch (IOException e) {
               e.printStackTrace();
            }
        }

        return s;
    }
    //****************************************************************************
    public static String getCorona() throws IOException {
        Document stat =  Jsoup.connect("https://en.wikipedia.org/wiki/Template:COVID-19_pandemic_data").get();

        Elements krank1 = stat.select("#thetable > tbody > tr:nth-child(6) > td:nth-child(3)");  // 4 позиция + 2 3 -первый столб
        Elements deaths = stat.select("#thetable > tbody > tr:nth-child(6) > td:nth-child(4)");
        Elements recovery = stat.select("#thetable > tbody > tr:nth-child(6) > td:nth-child(5)");
        return "Infected: " + krank1.text() + "\nRecovery: " + recovery.text() +
                "\nDeaths: " + deaths.text() + die;
    }

    //****************************************************************************
    public static String getBinary(String s) {
        int bin = Integer.parseInt(s);   
        if (bin < 0) {
            bin = bin * -1; // переводим в положительное
            s = Integer.toBinaryString(bin); // опять стринг
            s = s.replaceAll("0", "2");
            s = s.replaceAll("1", "0");
            s = s.replaceAll("2", "1");
        } else {
            s = Integer.toBinaryString(bin); //|| s.contains("-")
        }

        return s;
    }

    //*******************************************************************************
    public static String getValuta() throws IOException {
        Document doc2 = Jsoup.connect("https://finance.rambler.ru/currencies/EUR/").get();
        Elements euro = doc2.select("body > div.page > div > div > div.finance__main-wrapper > div.finance-top > div.finance-top__block.finance-top__block--money > div > div.finance-exchange-rate__left > div.finance-exchange-rate__controller > label:nth-child(2) > div > a:nth-child(3) > div.finance-exchange-rate__value");
        Elements dollar = doc2.select("body > div.page > div > div > div.finance__main-wrapper > div.finance-top > div.finance-top__block.finance-top__block--money > div > div.finance-exchange-rate__left > div.finance-exchange-rate__controller > label:nth-child(2) > div > a:nth-child(2) > div.finance-exchange-rate__value");
        return "€" + euro.text().substring(0, 5) + " , " + " $" + dollar.text().substring(0, 5);
    }

    //**********************************************************************************
    public static String Rotation(String s) {
        String resultfinal = "";

        char[] result = s.toCharArray();
        char first = result[0];

        for (int i = result.length - 1; 0 < i; i--) {
            resultfinal += result[i];
        }
        resultfinal = resultfinal + first;
        return resultfinal.replace("шытревереП/", "Перевертыш: ");
    }
    //**************************************************************************************

}

