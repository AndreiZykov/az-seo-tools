package com.zykov.andrii.azseotools.adapters.cards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.objects.ContentObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrii on 10/22/17.
 */

public class ContentCard extends RecyclerView.ViewHolder {

    @BindView(R.id.content_card_report)
    TextView contentReportTextView;

    public ContentCard(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ContentObject obj) {
        if (obj != null) {
            contentReportTextView.setText(obj.getContentReport());
        }
    }
}
