package com.wolf.fastlibrary;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
  Created by Wolf on 2017/11/9
 */


public class FooterViewHolder extends RecyclerView.ViewHolder {
    View loadDiaolg;
    TextView textDialog;

    public FooterViewHolder(View itemView) {
        super(itemView);
        loadDiaolg = itemView.findViewById(R.id.progressBar);
        textDialog = (TextView) itemView.findViewById(R.id.foot_text_view);
    }
}
