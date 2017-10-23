package com.zykov.andrii.azseotools.fragment.seofragment;

import android.os.Handler;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.objects.H_TagObject;
import com.zykov.andrii.azseotools.objects.HeadTagObject;
import com.zykov.andrii.azseotools.objects.SeoObject;
import com.zykov.andrii.azseotools.objects.ContentObject;
import com.zykov.andrii.azseotools.tasks.ParseHtmlTask;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Iterator;

/**
 * Created by andrii on 10/21/17.
 */

public class SeoFragmentPresenter implements SeoFragment.ISeoFragmentPresenter {

    private ISeoFragment fragment;

    private Handler handler = new Handler();

    public SeoFragmentPresenter(SeoFragment seoFragment) {
        this.fragment = seoFragment;
    }

    @Override
    public void getSeoDataAsync(String url) {
        fragment.showProgressBar();
        new ParseHtmlTask(url, new ParseHtmlTask.ParseHTMLTaskListener() {
            @Override
            public void onSuccess(final Document document) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (fragment.getCurrentContext() != null) {
                            fragment.hideProgressBar();
                            fragment.showSeoData(parseHTMLToSeoElements(document));
                        }
                    }
                });
            }

            @Override
            public void onError(String error) {
                fragment.hideProgressBar();
                fragment.handleError(error);
            }
        }).execute();
    }

    private List<SeoObject> parseHTMLToSeoElements(Document document) {
        List<SeoObject> res = new ArrayList<SeoObject>();

        if (document != null) {
            String title = document.title();
            if (title != null && !title.isEmpty())
                res.add(new HeadTagObject(fragment.getCurrentContext().getString(R.string.title), document.title()));

            // meta tags
            Elements metaTags = document.getElementsByTag("meta");
            for (Element metaTag : metaTags) {
                String value = metaTag.attr("content");
                String name = metaTag.attr("name");
                if (name.equals("keywords") || name.equals("description")) {
                    value = prepareValue(value);
                    res.add(new HeadTagObject("meta > " + name, value));
                }
            }
//
            Elements hTags = document.select("h1, h2, h3, h4, h5, h6");
            if (hTags != null && hTags.size() > 0) {
                H_TagObject hTagObj = new H_TagObject();
                hTagObj.setH1Tags(listOfAllValuesByTagName(document, "h1"));
                hTagObj.setH2Tags(listOfAllValuesByTagName(document, "h2"));
                hTagObj.setH3Tags(listOfAllValuesByTagName(document, "h3"));
                hTagObj.setH4Tags(listOfAllValuesByTagName(document, "h4"));
                hTagObj.setH5Tags(listOfAllValuesByTagName(document, "h5"));
                hTagObj.setH6Tags(listOfAllValuesByTagName(document, "h6"));
                res.add(hTagObj);
            }
//
            // if till this moment everything is empty, means web site is bad or broken
            if(!res.isEmpty()) {
                String text = document.body().text();
                if (text != null && !text.isEmpty()) {
                    ContentObject contentObj = new ContentObject();
                    contentObj.setTotalSymbolCount(text.length());
                    contentObj.setWordsHashMap(sortHashMapByValues(inspectText(text)));
                    res.add(contentObj);
                }
            }

        }
        return res;
    }

    private HashMap<String, Integer> inspectText(String text) {
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        String tempText = text.replaceAll("[-+.^:,()]", "");
        for (String word : tempText.split(" ")) {
            if (res.containsKey(word)) {
                res.put(word, res.get(word) + 1);
            } else {
                res.put(word, 1);
            }
        }
        return res;
    }

    private String prepareValue(String value) {
        return value.trim().replaceAll(" +", " ");
    }

    // Static Classes
    private List<String> listOfAllValuesByTagName(Document doc, String tagName) {
        List<String> res = new ArrayList<>();
        Elements h1Tags = doc.getElementsByTag(tagName);
        for (Element tag : h1Tags) {
            for (Node childElement : tag.childNodes()) {
                if (childElement instanceof TextNode) {
                    String text = ((TextNode) childElement).text();
                    if(text != null && !text.trim().isEmpty())
                        res.add(text);
                }
            }
        }
        return res;
    }

    public LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp2 = passedMap.get(key);
                Integer comp1 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

}
