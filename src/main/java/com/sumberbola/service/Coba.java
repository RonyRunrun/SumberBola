package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rony R on 3/10/2017.
 */

public class Coba {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("tags")
    @Expose
    private List<Integer> tags = null;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("better_featured_image")
    @Expose
    private BetterFeaturedImage betterFeaturedImage;
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = null;
    @SerializedName("content")
    @Expose
    private Content content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public BetterFeaturedImage getBetterFeaturedImage() {
        return betterFeaturedImage;
    }

    public void setBetterFeaturedImage(BetterFeaturedImage betterFeaturedImage) {
        this.betterFeaturedImage = betterFeaturedImage;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}