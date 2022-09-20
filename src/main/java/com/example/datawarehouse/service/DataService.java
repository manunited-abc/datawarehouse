package com.example.datawarehouse.service;

import com.example.datawarehouse.IO.WriteFile;
import com.example.datawarehouse.model.Lottery;
import com.example.datawarehouse.untils.Domain;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    //Phan tich cu phap trang html
    public static List<Lottery> getLotteriesFromPageHTML(String url, LocalDateTime issueDate) {
        try {
            int year = issueDate.getYear();
            int month = issueDate.getMonthValue();
            int day = issueDate.getDayOfMonth();
            if (issueDate.isBefore(LocalDateTime.of(year, month, day, 17, 10, 00, 00))) {
                System.out.println("chua so");
                issueDate = issueDate.minusDays(1);
            }
            String dateString = convertDateToString(issueDate);
            System.out.println(dateString);
            String fullUrl = url + dateString;
            Document document = Jsoup.connect(fullUrl).get();
            Element tableParent = document.getElementsByClass("table-tructiep").first();
            Element header = tableParent.getElementsByClass("result-header").first();

            String company = header.getElementsByTag("a").first().text();
            Element tableResult = tableParent.getElementsByClass("table").first();
            Element thead = tableResult.getElementsByTag("thead").first();
            Element tbody = tableResult.getElementsByTag("tbody").first();

            Elements provinces = thead.getElementsByTag("th");
            int size = provinces.size();
            List<Lottery> lotteries = new ArrayList<>();
            for (int i = 1; i < size; i++) {
                Lottery lottery = new Lottery();
                lottery.setCompany(company);
                lottery.setProvince(provinces.get(i).text());
                lottery.setIssueDate(issueDate.toString());
                // Get value pize 8
                lottery.setPrize8(getSerial(i, tbody, 0));
                // Get value pize 7
                lottery.setPrize7(getSerial(i, tbody, 1));
                // Get value pize 6
                lottery.setPrize6(getSerial(i, tbody, 2));
                // Get value pize 5
                lottery.setPrize5(getSerial(i, tbody, 3));
                // Get value pize 4
                lottery.setPrize4(getSerial(i, tbody, 4));
                // Get value pize 3
                lottery.setPrize3(getSerial(i, tbody, 5));
                // Get value pize 2
                lottery.setPrize2(getSerial(i, tbody, 6));
                // Get value pize 1
                lottery.setPrize1(getSerial(i, tbody, 7));
                // Get value pize 0
                lottery.setPrize0(getSerial(i, tbody, 8));

                lotteries.add(lottery);
            }
            return lotteries;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Noi cac day so ngan cac boi dau "-"
    public static String getSerial(int index, Element elementParent, int i) {
        Element trowElements = elementParent.getElementsByTag("tr").get(i);
        Elements serialsElementsPrize = trowElements.getElementsByTag("td").get(index).children();
        ArrayList<String> serialListPrize = new ArrayList<>();
        for (Element serialElement : serialsElementsPrize) {
            serialListPrize.add(serialElement.text());
        }
        String serialPrize = String.join("-", serialListPrize);
        return serialPrize;
    }
    //Format thoi gian thanh dang dd-mm-yyyy
    public static String convertDateToString(LocalDateTime date) {
        DateTimeFormatter fmDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(fmDateTimeFormatter);
    }
    //Ghi lottery vao file csv
    public void writeToCSV(LocalDateTime issueDate){
            List<Lottery> lotteries = getLotteriesFromPageHTML(Domain.DOMAIN1, issueDate);
            String now = convertDateToString(issueDate);
            String fileName = "data_lotteries." + now + "_datawarehouse-locahost.csv";
            WriteFile.write(fileName, lotteries);

    }
}
