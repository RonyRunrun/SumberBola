package com.sumberbola;

import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Rony R on 3/15/2017.
 */

public class CustomSpan extends ClickableSpan {

    private String url;
    private OnClickListener mListener;

    public CustomSpan(String url, OnClickListener mListener) {
        this.url = url;
        this.mListener = mListener;
    }



    @Override
    public void onClick(View widget) {
        if (mListener != null) mListener.onClick(url);
    }

    public interface OnClickListener {
        void onClick(String url);
    }
}