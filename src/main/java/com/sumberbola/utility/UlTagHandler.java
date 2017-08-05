package com.sumberbola.utility;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

/**
 * Created by Rony R on 3/22/2017.
 */

public class UlTagHandler implements Html.TagHandler{
    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        if(tag.equals("ul") && !opening) output.append("");
        //if(tag.equals("ul") && !opening) output.append("\n-------------------------");
        if(tag.equals("li") && opening) output.append("\n•");
    }
}