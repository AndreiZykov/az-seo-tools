package com.zykov.andrii.azseotools.adapters.cards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.objects.HeadTagObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrii on 10/22/17.
 */

public class HeadTagCard extends RecyclerView.ViewHolder {
    @BindView(R.id.meta_tag_value)
    TextView metaTagValue;

    @BindView(R.id.meta_tag_name)
    TextView metaTagName;

    public HeadTagCard(View layout) {
        super(layout);
        ButterKnife.bind(this, layout);
    }

    public void bind(HeadTagObject obj){
        if(obj != null){
            metaTagName.setText(obj.getMetaTagName());
            metaTagValue.setText(obj.getMetaTagValue());
        }
    }

}
