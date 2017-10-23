package com.zykov.andrii.azseotools.objects;

/**
 * Created by andrii on 10/22/17.
 */

public class HeadTagObject extends SeoObject {
    private String metaTagName;
    private String metaTagValue;

    public HeadTagObject(String metaTagName, String metaTagValue){
        this.metaTagName = metaTagName;
        this.metaTagValue = metaTagValue;
    }

    public String getMetaTagName() {
        return metaTagName;
    }

    public void setMetaTagName(String metaTagName) {
        this.metaTagName = metaTagName;
    }

    public String getMetaTagValue() {
        return metaTagValue;
    }

    public void setMetaTagValue(String metaTagValue) {
        this.metaTagValue = metaTagValue;
    }
}
