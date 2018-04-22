package com.grp12.towerdefense.views.MenyViews;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.Network.NetworkCommunicator;
import com.grp12.towerdefense.Network.NetworkGame;
import com.grp12.towerdefense.states.GameStateManager;
import com.grp12.towerdefense.states.PlayState;
import com.grp12.towerdefense.states.State;
import com.grp12.towerdefense.views.PlayViews.GameButton;
import com.grp12.towerdefense.views.PlayViews.NewGameButton;

import java.util.ArrayList;

public class ActiveGameMenuView extends State{
    private ArrayList<GameButton> gameButtons;
    private NewGameButton nb;

    public ActiveGameMenuView(GameStateManager gsm) {
        super(gsm);
        nb = new NewGameButton(10, 100, 100, 40);
        updateGameList();
    }


    public void updateGameList(){
        gameButtons = new ArrayList<GameButton>();
        int i = 0;
        for (NetworkGame game : NetworkCommunicator.userGames){
            gameButtons.add(new GameButton( 110, 10 + 50 * i, 100, 45, game));
            i++;
        }
    }


    @Override
    protected void handleInput(Vector3 pointer) {
        System.out.println("Handling input");
        for(GameButton b : gameButtons){
            if(b.clicked(pointer)){
                //NetworkCommunicator.setActiveGame(b.getGame());
                //gsm.push(new PlayState(gsm));
                System.out.println(b.getGame().getId());
            }
        }
        if(nb.clicked(pointer)){
            System.out.println("Looking for new game!");
            NetworkCommunicator.newGameRequest();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        nb.draw(sb);
        for (GameButton b : gameButtons){
            b.draw(sb);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public int getViewportWidth() {
        return 0;
    }

    @Override
    public int getViewportHeight() {
        return 0;
    }
}
