package com.grp12.towerdefense.views.PlayViews;

/*
Takes in an object to be drawn in the GameMenuView
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuElement {

    private int price;
    private Sprite thumbnail;
    //Add enum or something for type that can be used to get info

    public MenuElement(int price, Texture thumbnail) {
        this.price = price;
        this.thumbnail = new Sprite(thumbnail);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Sprite getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Texture thumbnail) {
        this.thumbnail = new Sprite(thumbnail);
    }

    public float width() {
        return thumbnail.getWidth();
    }

    public float height() {
        return thumbnail.getHeight();
    }
}
