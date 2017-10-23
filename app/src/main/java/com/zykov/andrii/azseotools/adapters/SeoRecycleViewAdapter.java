package com.zykov.andrii.azseotools.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.adapters.cards.ContentCard;
import com.zykov.andrii.azseotools.adapters.cards.H_TagCard;
import com.zykov.andrii.azseotools.adapters.cards.HeadTagCard;
import com.zykov.andrii.azseotools.objects.ContentObject;
import com.zykov.andrii.azseotools.objects.H_TagObject;
import com.zykov.andrii.azseotools.objects.HeadTagObject;
import com.zykov.andrii.azseotools.objects.SeoObject;

import java.util.List;

/**
 * Created by andrii on 10/21/17.
 */

public class SeoRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int META_TAG_TYPE = 1;
    private static final int HTML_TAG_TYPE = 2;
    private static final int H_TAG_TYPE = 3;
    private static final int CONTENT_TYPE = 4;
    private List<SeoObject> data;

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof HeadTagObject) {
            return META_TAG_TYPE;
        } else if (data.get(position) instanceof H_TagObject) {
            return H_TAG_TYPE;
        } else if (data.get(position) instanceof ContentObject){
            return CONTENT_TYPE;
        }
        return -1;
    }

    public SeoRecycleViewAdapter(List<SeoObject> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        switch (viewType) {
            case META_TAG_TYPE:
                View metaRootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_tag_card_layout, parent, false);
                vh = new HeadTagCard(metaRootView);
                break;
            case HTML_TAG_TYPE:
                RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.html_tag_card_layout, parent, false);
                // set the view's size, margins, paddings and layout parameters
                vh = new HtmlTagCard(v);
                break;
            case H_TAG_TYPE:
                View hTagRootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_tag_card_layout, parent, false);
                vh = new H_TagCard(hTagRootView);
                break;
            case CONTENT_TYPE:
                View contentRootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_card_layout, parent, false);
                vh = new ContentCard(contentRootView);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadTagCard && data.get(position) instanceof HeadTagObject) {
            HeadTagCard card = (HeadTagCard) holder;
            card.bind((HeadTagObject)data.get(position));
        } else if (holder instanceof HtmlTagCard) {
//            ((TextView) ((HtmlTagCard) holder).layout.findViewById(R.id.text1)).setText(((HTMLTag) data.get(position)).getTagName());
        } else if (holder instanceof H_TagCard){
            ((H_TagCard) holder).bind((H_TagObject) data.get(position));
        } else if (holder instanceof ContentCard){
            ((ContentCard) holder).bind((ContentObject) data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HtmlTagCard extends RecyclerView.ViewHolder {
        public RelativeLayout layout;

        public HtmlTagCard(RelativeLayout v) {
            super(v);
            layout = v;
        }
    }
}
