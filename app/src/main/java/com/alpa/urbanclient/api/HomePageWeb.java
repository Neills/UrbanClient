package com.alpa.urbanclient.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class HomePageWeb {
    private int page,totalPages=1;
    private WordPanel[]panels;

    public HomePageWeb(int page) {
        this.page = page;
        String url="https://www.urbandictionary.com/?page="+page;
        try{
            Document doc= Jsoup.connect(url).get();
            Elements x= doc.getElementsByClass("def-panel");
            panels=new WordPanel[x.size()];
            int i=0;
            for(Element e:x) panels[i++]=new WordPanel(e);
            Element e1 = doc.getElementsByClass("pagination").first().getElementsByTag("li").last();
            if(e1.hasClass("current")) totalPages=Integer.parseInt(e1.text());
            else{
                e1=e1.getElementsByTag("a").first();
                String t = e1.attr("href");
                totalPages=Integer.parseInt(t.substring(t.lastIndexOf('=')+1));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public WordPanel getPanel(int i){
        return panels[i];
    }

    public int getPanelCount(){
        return panels.length;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "HomePageWeb{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", panels=" + Arrays.toString(panels) +
                '}';
    }
}
