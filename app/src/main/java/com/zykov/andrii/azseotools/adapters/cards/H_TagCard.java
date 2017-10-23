package com.zykov.andrii.azseotools.adapters.cards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.objects.H_TagObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 10/22/17.
 */

public class H_TagCard extends RecyclerView.ViewHolder {

    @BindView(R.id.h_card_h1)
    TextView h1TextView;

    @BindView(R.id.h_card_h2)
    TextView h2TextView;

    @BindView(R.id.h_card_h3)
    TextView h3TextView;

    @BindView(R.id.h_card_h4)
    TextView h4TextView;

    @BindView(R.id.h_card_h5)
    TextView h5TextView;

    @BindView(R.id.h_card_h6)
    TextView h6TextView;

    @BindView(R.id.h_tag_list_text_view)
    TextView hTagsListTextView;

    @BindView(R.id.h_card_chow_more_ind_text_view)
    TextView showMoreTextView;

    @OnClick({R.id.h_card_chow_more_ind_text_view})
    public void OnClick(View v){
        if(hTagsListTextView.getVisibility() == View.VISIBLE){
            hTagsListTextView.setVisibility(View.GONE);
            showMoreTextView.setText(R.string.show_more);
        } else {
            hTagsListTextView.setVisibility(View.VISIBLE);
            showMoreTextView.setText(R.string.hide);
        }
    }

    public H_TagCard(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(H_TagObject obj){
        if(obj != null){
            h1TextView.setText(String.valueOf(obj.getH1Count()));
            h2TextView.setText(String.valueOf(obj.getH2Count()));
            h3TextView.setText(String.valueOf(obj.getH3Count()));
            h4TextView.setText(String.valueOf(obj.getH4Count()));
            h5TextView.setText(String.valueOf(obj.getH5Count()));
            h6TextView.setText(String.valueOf(obj.getH6Count()));
            hTagsListTextView.setText(obj.buildHTagList());
        }
    }

}
