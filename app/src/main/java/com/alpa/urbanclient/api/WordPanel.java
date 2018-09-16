package com.alpa.urbanclient.api;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Arrays;

public class WordPanel {

    private String date;
    private String word;
    private String meaning;
    private String example;
    private String tags[]= new String[0];
    private String contributor;
    private int like;
    private int dislike;
    /**
     * @param e A div with "def-panel" class taken from the homepage
     * */
    public WordPanel(Element e){
        if(e.getElementsByClass("ribbon").size()>0) {
            date = e.getElementsByClass("ribbon").first().text();
            example = e.getElementsByClass("example").first().text();
            Elements e1 = e.getElementsByClass("tags");
            if (e1.size() > 0) {
                e1 = e1.first().getElementsByTag("a");
                tags = new String[e1.size()];
                for (int i = 0; i < e1.size(); i++) {
                    tags[i] = e1.get(i).text().substring(1);
                }
            } else tags = new String[0];
            contributor = e.getElementsByClass("contributor").first().text();
            e1 = e.getElementsByClass("count");
            like = Integer.parseInt(e1.get(0).text());
            dislike = Integer.parseInt(e1.get(1).text());
        }
        word = e.getElementsByClass("word").first().text();
        meaning = e.getElementsByClass("meaning").first().text();
    }

    public String getDate() {
        return date;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getExample() {
        return example;
    }

    public String getContributor() {
        return contributor;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public int getTagCount(){
        return tags.length;
    }

    public String getTag(int i){
        return tags[i];
    }



    @Override
    public String toString() {
        return "WordPanel{" +
                "date='" + date + '\'' +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", example='" + example + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", contributor='" + contributor + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                '}';
    }
}
