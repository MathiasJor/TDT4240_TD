package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;
import com.grp12.towerdefense.gamelogic.towers.BasicTower;
import com.grp12.towerdefense.gamelogic.towers.RocketTower;
import com.grp12.towerdefense.gamelogic.towers.StunTower;

import java.util.ArrayList;

public class GameMenuView extends View {

    private ArrayList<MenuElement> elements;
    private boolean showElements, showOneTower;
    private Vector2 bottomleftCorner;
    private MenuElement selected;
    private Sprite openMenu;
    private Sprite closeMenu;
    private Sprite backGroundTile;
    private int scale = 200, itemSelected=0, height, width;
    private BitmapFont bitmapFont;
    final boolean[] ready = {false};
    private AbstractTower[] newTowers={new StunTower(), new RocketTower(), new BasicTower()};
    private int[] cost= {newTowers[0].getCost(), newTowers[1].getCost(),newTowers[2].getCost()};

    //TODO: For now it will just draw out of the screen, either limit the amount of elements, or let expand in the x-axis

    public GameMenuView(Vector2 bottomleftCorner) {
        this.bottomleftCorner = bottomleftCorner;
        elements = new ArrayList<MenuElement>();
        showElements = false;
        showOneTower=false;
        selected = null;
        openMenu = new Sprite(new Texture("towerDefense_tile08444.png"));
        closeMenu = new Sprite(new Texture("towerDefense_tile086.png"));
        backGroundTile = new Sprite(new Texture("towerDefense_tile084.png"));
        //TODO:Remove this
        elements.add(new MenuElement(cost[2], new Texture("towerDefense_tile226.png")));
        elements.add(new MenuElement(cost[1], new Texture("towerDefense_tile205.png")));
        elements.add(new MenuElement(cost[0], new Texture("towerDefense_tile250_orginal.png")));
        bitmapFont = new BitmapFont();

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (showElements) {
            spriteBatch.draw(closeMenu, 0,0, scale, scale);
            for (int i = 0; i < elements.size(); i++) {
                Sprite element = elements.get(i).getThumbnail();
                spriteBatch.draw(backGroundTile, 0, 3*(i+1)*element.getHeight(), scale, scale);
                spriteBatch.draw(element, 0, 3*(i+1)*element.getHeight(), scale, scale);
                bitmapFont.getData().setScale(3);
                bitmapFont.draw(spriteBatch,"Cost: "+elements.get(i).getPrice(),1,(3*(i+2)*element.getHeight()));
            }
        }
        else if(showOneTower){
            Sprite towerSprite = elements.get(itemSelected).getThumbnail();
            spriteBatch.draw(closeMenu,width-towerSprite.getWidth()-scale+closeMenu.getWidth(),height-towerSprite.getWidth()*3, scale,scale);
            spriteBatch.draw(backGroundTile, width-towerSprite.getWidth()-scale*2+closeMenu.getWidth(),height-towerSprite.getWidth()*3, scale, scale);
            spriteBatch.draw(towerSprite,width-towerSprite.getWidth()-scale*2+closeMenu.getWidth(),height-towerSprite.getWidth()*3, scale,scale);
            bitmapFont.getData().setScale(3);
            bitmapFont.draw(spriteBatch,"Cost: "+elements.get(itemSelected).getPrice(),width-towerSprite.getWidth()-scale*2+closeMenu.getWidth(),height-towerSprite.getWidth()*2);
        }
        else {
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

    public void isClickedSelectTower(Vector3 pointer){
        //open tower selection memu
        if(!showElements){
            if (pointer.x >= 0 && pointer.x <= scale) {
                if (pointer.y >= 0 && pointer.y <= scale) {
                    showElements = true;
                    //return true;
                }
            }
            //return false;
        }
        else{
            //close tower selection menu
            if (pointer.x >= 0 && pointer.x <= scale) {
                if (pointer.y >= 0 && pointer.y <= scale) {
                    //return true;
                    showElements = false;
                }
            }

        }

    }

    public int towerSelected(Vector3 pointer, int height, int width){
        this.height = height;
        this.width=width;

        for (int i=0; i < elements.size();i++){
            if (pointer.x >= 0 && pointer.x <= scale) {
                Sprite element = elements.get(i).getThumbnail();
                if (pointer.y >= (i+1)*scale && pointer.y <= (i+2)*scale) {
                    //return true;
                    showElements=false;
                    showOneTower=true;
                    itemSelected=i;
                    return i;
                }
            }
        }

        return 1000;
    }

    public boolean finishedWithSelectedTower(Vector3 pointer){
        Sprite towerSprite = elements.get(0).getThumbnail();
            if (pointer.x >= width-towerSprite.getWidth()-scale+closeMenu.getWidth() && pointer.x <= width) {
                if (pointer.y >= height-towerSprite.getWidth()*3 && pointer.y <= height) {
                    showOneTower=false;
                    showElements = true;

                    return true;
                }
            }
        return false;
    }

    public boolean getShowElements(){return showElements;}
    public boolean getShowOneTower(){return showOneTower;}
    @Override
    public void dispose() {
        //TODO: #4: Implement this
    }


}
