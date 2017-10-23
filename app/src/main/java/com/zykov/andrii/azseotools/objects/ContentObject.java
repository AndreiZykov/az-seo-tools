package com.zykov.andrii.azseotools.objects;

/**
 * Created by andrii on 10/22/17.
 */

import java.util.LinkedHashMap;
import java.util.Map;
public class ContentObject extends SeoObject {

    private int totalSymbolCount;

    private LinkedHashMap<String, Integer> wordsHashMap;

    public LinkedHashMap<String, Integer> getWordsHashMap() {
        return wordsHashMap;
    }

    public void setWordsHashMap(LinkedHashMap<String, Integer> wordsHashMap) {
        this.wordsHashMap = wordsHashMap;
    }

    public int getTotalSymbolCount() {
        return totalSymbolCount;
    }

    public void setTotalSymbolCount(int totalSymbolCount) {
        this.totalSymbolCount = totalSymbolCount;
    }

    public String getContentReport(){
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("   ");
        if(wordsHashMap != null && wordsHashMap.size() > 0){
            for (Map.Entry<String,Integer> entry : wordsHashMap.entrySet()) {
                sb.append(entry.getKey().toLowerCase() + " (" + entry.getValue() + "), ");
                index++;
                if(index == 22)
                    break;
            }
        }
        return sb.toString();
    }
}
