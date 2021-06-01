import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Film {




  public static String getFilm (String s) throws IOException {

            String result = "";
//  #jr-pagenav-ajax > div.jrResults.fwd-py-4 > div > div:nth-child(1) > div > div.jrCardContent.fwd-flex.fwd-flex-col.fwd-w-full.fwd-h-full.fwd-min-w-0 > div > div.jrCardTitle.fwd-pt-4.fwd-pb-2.fwd-px-3.fwd-font-bold.sm\:fwd-px-4.sm\:fwd-pt-1 > div > a
        Document news = Jsoup.connect("https://www.megacritic.ru/novye/filmy/2019-2020").get();
        Elements newelements = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults.fwd-py-4 > div:nth-child(1) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements1 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(2) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements2 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(3) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements3 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(4) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements4 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(5) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements5 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(6) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements6 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(7) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements7 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(8) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements8 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(9) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements newelements9 = news.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(10) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");

        Document comedie = Jsoup.connect("https://www.megacritic.ru/navigator/search-results?order=reviews&dir=1&criteria=5&cat=33&query=all&jr_genre=komedii&jr_year=2020").get();
        Elements comelement1 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(1) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement2 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(2) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement3 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(3) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement4 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(4) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement5 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(5) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement6 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(6) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement7 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(7) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement8 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(8) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement9 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(9) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements comelement10 = comedie.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(10) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");

        Document horror = Jsoup.connect("https://www.megacritic.ru/navigator/search-results?order=reviews&dir=1&criteria=5&cat=33&query=all&jr_genre=uzhasy&jr_year=2020").get();
        Elements horrelement1 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(1) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement2 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(2) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement3 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(3) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement4 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(4) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement5 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(5) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement6 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(6) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement7 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(7) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement8 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(8) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement9 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(9) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");
        Elements horrelement10 = horror.select("#jr-pagenav-ajax > div.jrTableGrid.jrDataList.jrResults > div:nth-child(10) > div.jr-listing-outer.jrCol.jrTableColumnMain > div.jrContentTitle");


    if ( s.equals("Новинки")) {
    result = "Новинки 2019-20 года:" + "\n" + newelements.text() + "\n" + newelements1.text() + "\n" + newelements2.text() + "\n" + newelements3.text()
            + "\n" + newelements4.text() + "\n" + newelements5.text() + "\n" + newelements6.text() + "\n" + newelements7.text()
            + "\n" + newelements8.text() + "\n" + newelements9.text();
            }

    if (s.equals("Комедии")){
        result = "Лучшие комедии 2019-20 года:" + "\n"+  comelement1.text() + "\n"+  comelement2.text()+ "\n"+  comelement3.text()+ "\n"+  comelement4.text()
                + "\n"+  comelement5.text()+ "\n"+  comelement6.text()+ "\n"+  comelement7.text()+ "\n"+  comelement8.text()+ "\n"+  comelement9.text()
                + "\n"+  comelement10.text();}

    if(s.equals("Ужасы")){
        result = "Ужастики:" + "\n" + horrelement1.text()+ "\n" + horrelement2.text()+ "\n" + horrelement3.text()+ "\n" + horrelement4.text()
                + "\n" + horrelement5.text()+ "\n" + horrelement6.text()+ "\n" + horrelement7.text()+ "\n" + horrelement8.text()
                + "\n" + horrelement9.text() + "\n" + horrelement10.text();

    }

        return result;
    }
}