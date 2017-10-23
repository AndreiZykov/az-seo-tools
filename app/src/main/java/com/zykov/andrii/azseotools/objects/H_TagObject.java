package com.zykov.andrii.azseotools.objects;

/**
 * Created by andrii on 10/22/17.
 */

import java.util.List;

public class H_TagObject extends SeoObject {

    private List<String> h1Tags;
    private List<String> h2Tags;
    private List<String> h3Tags;
    private List<String> h4Tags;
    private List<String> h5Tags;
    private List<String> h6Tags;

    public int getH1Count() {
        return h1Tags == null ? 0 : h1Tags.size();
    }

    public int getH2Count() {
        return h2Tags == null ? 0 : h2Tags.size();
    }

    public int getH3Count() {
        return h3Tags == null ? 0 : h3Tags.size();
    }

    public int getH4Count() {
        return h4Tags == null ? 0 : h4Tags.size();
    }

    public int getH5Count() {
        return h5Tags == null ? 0 : h5Tags.size();
    }


    public int getH6Count() {
        return h6Tags == null ? 0 : h6Tags.size();
    }


    public List<String> getH2Tags() {
        return h2Tags;
    }

    public void setH2Tags(List<String> h2Tags) {
        this.h2Tags = h2Tags;
    }

    public List<String> getH1Tags() {
        return h1Tags;
    }

    public void setH1Tags(List<String> h1Tags) {
        this.h1Tags = h1Tags;
    }

    public List<String> getH3Tags() {
        return h3Tags;
    }

    public void setH3Tags(List<String> h3Tags) {
        this.h3Tags = h3Tags;
    }

    public List<String> getH4Tags() {
        return h4Tags;
    }

    public void setH4Tags(List<String> h4Tags) {
        this.h4Tags = h4Tags;
    }

    public List<String> getH5Tags() {
        return h5Tags;
    }

    public void setH5Tags(List<String> h5Tags) {
        this.h5Tags = h5Tags;
    }

    public List<String> getH6Tags() {
        return h6Tags;
    }

    public void setH6Tags(List<String> h6Tags) {
        this.h6Tags = h6Tags;
    }

    public String buildHTagList() {
        StringBuilder sb = new StringBuilder();
        if (getH1Count() > 0) {
            sb.append("H1 Tags: \n");
            for (String tagValue : getH1Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        if (getH2Count() > 0) {
            sb.append("H2 Tags: \n");
            for (String tagValue : getH2Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        if (getH3Count() > 0) {
            sb.append("H3 Tags: \n");
            for (String tagValue : getH3Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        if (getH4Count() > 0) {
            sb.append("H4 Tags: \n");
            for (String tagValue : getH4Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        if (getH5Count() > 0) {
            sb.append("H5 Tags: \n");
            for (String tagValue : getH5Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        if (getH6Count() > 0) {
            sb.append("H6 Tags: \n");
            for (String tagValue : getH6Tags())
                sb.append("  " + tagValue.trim() + "\n");
        }
        return sb.toString();
    }

}
