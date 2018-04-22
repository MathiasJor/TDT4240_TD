package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.Network.NetworkGame;

/**
 * Created by Kristian on 4/22/2018.
 */

public class NewGameButton extends View {
    private int x, y, width, height;
    private BitmapFont bmf;

    private ShapeRenderer renderer;

    public NewGameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.renderer = new ShapeRenderer();
        this.bmf = new BitmapFont();
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(x, y, width, height);
        renderer.end();

        bmf.getData().setScale(6);
        bmf.setColor(Color.WHITE);
        bmf.draw(spriteBatch, "New Game", x + 5, y + 5);
    }
    public boolean clicked(Vector3 pointer) {
        System.out.println("Checking if clicked :)");
        System.out.println(String.format("X: %d, Y: %d, Pointer X: %f, Pointer Y: %f", x, y, pointer.x, pointer.y));
        if (pointer.x >= (float)this.x && pointer.x <= (float)(this.x + this.width) ) {
            if (pointer.y >= (float)this.y && pointer.y <= (float)this.y + (float)this.height) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void dispose() {

    }
}
