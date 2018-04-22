package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Kristian on 4/22/2018.
 */

public class NewGameButton extends View {
    private int x, y, width, height;
    private BitmapFont bmf;
    private SpriteBatch sbp;
    private ShapeRenderer renderer;

    public NewGameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.renderer = new ShapeRenderer();
        sbp = new SpriteBatch();
        this.bmf = new BitmapFont();
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {

        this.renderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(x, y, width, height);
        renderer.end();

        sbp.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        sbp.begin();
        bmf.getData().setScale(6);
        bmf.setColor(Color.WHITE);
        bmf.draw(sbp, "New Game", x, y+ height/2);
        sbp.end();
    }
    public boolean clicked(Vector3 pointer) {
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
