package com.alpa.urbanclient.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class SearchForTags {

    private final String query;
    private final int page;
    private int totalPages=1;
    private WordPanel panels[]= new WordPanel[0];

    public String getQuery() {
        return query;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public WordPanel getPanel(int i){
        return panels[i];
    }

    public int getPanelCount(){
        return panels.length;
    }

    public SearchForTags(String s, int page){
        query=s;
        this.page=page;
        String url="https://www.urbandictionary.com/tags.php?tag="+s+"&page="+page;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("def-panel");
            panels = new WordPanel[elements.size()];
            for(int i=0; i<panels.length; i++){
                panels[i]=new WordPanel(elements.get(i));
            }
            elements=doc.getElementsByClass("pagination");
            if(elements.size()>0) {
                Element e1 = elements.first().getElementsByTag("li").last();
                if (e1.hasClass("current")) totalPages = Integer.parseInt(e1.text());
                else {
                    e1 = e1.getElementsByTag("a").first();
                    String t = e1.attr("href");
                    totalPages = Integer.parseInt(t.substring(t.lastIndexOf('=') + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "SearchForTags{" +
                "query='" + query + '\'' +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", panels=" + Arrays.toString(panels) +
                '}';
    }
}
