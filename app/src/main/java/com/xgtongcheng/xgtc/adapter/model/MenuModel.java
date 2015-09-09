package com.xgtongcheng.xgtc.adapter.model;

/**
 * Created by wwx on 2015/9/9.
 */
public class MenuModel {
    private int imageView;
    private String text;

    public String getFragmentTag() {
        return fragmentTag;
    }

    public void setFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
    }

    private String fragmentTag;

    public MenuModel(int imageView, String text,String fragmentTag) {
        super();
        this.imageView = imageView;
        this.text = text;
        this.fragmentTag = fragmentTag;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
