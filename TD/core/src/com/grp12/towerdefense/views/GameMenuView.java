package com.grp12.towerdefense.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameMenuView extends View {

    private ArrayList<MenuElement> elements;
    private boolean showElements;
    private Vector2 bottomleftCorner;
    private MenuElement selected;
    private Sprite openMenu;
    private Sprite closeMenu;
    private Sprite backGroundTile;
    private int scale = 200;

    //TODO: For now it will just draw out of the screen, either limit the amount of elements, or let expand in the x-axis

    public GameMenuView(Vector2 bottomleftCorner) {
        this.bottomleftCorner = bottomleftCorner;
        elements = new ArrayList<MenuElement>();
        showElements = false;
        selected = null;
        openMenu = new Sprite(new Texture("towerDefense_tile08444.png"));
        closeMenu = new Sprite(new Texture("towerDefense_tile086.png"));
        backGroundTile = new Sprite(new Texture("towerDefense_tile084.png"));
        //TODO:Remove this
        elements.add(new MenuElement(22, new Texture("towerDefense_tile226.png")));
        elements.add(new MenuElement(22, new Texture("towerDefense_tile205.png")));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (showElements) {
            spriteBatch.draw(closeMenu, 0,0, scale, scale);
            for (int i = 0; i < elements.size(); i++) {
                Sprite element = elements.get(i).getThumbnail();
                spriteBatch.draw(backGroundTile, 0, 3*(i+1)*element.getHeight(), scale, scale);
                spriteBatch.draw(element, 0, 3*(i+1)*element.getHeight(), scale, scale);
            }
        } else {
            spriteBatch.draw(openMenu, 0,0,scale,scale);
        }
    }

    public void addMenuElement(MenuElement menuElement) {
        if (elements.indexOf(menuElement) == -1)
            elements.add(menuElement);
    }

    public void removeMenuElement(MenuElement menuElement) {
        elements.remove(menuElement);
    }

    // returns some sort of value for the menu element drawn at position
    public MenuElement getElementAtPosition(Vector2 position) {
        //TODO: #30: Implement this: Some sort of type or enum should be returned instead

        return null;

    }

    public void open() {
        showElements = true;
    }

    public void close() {
        showElements = false;
    }

    @Override
    public void dispose() {
        //TODO: #4: Implement this
    }
}
