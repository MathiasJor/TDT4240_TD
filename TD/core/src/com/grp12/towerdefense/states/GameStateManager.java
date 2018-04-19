package com.grp12.towerdefense.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    public int getViewportWidth() {
        return states.peek().getViewportWidth();
    }

    public int getViewportHeight() {
        return states.peek().getViewportHeight();
    }

    public Stack<State> getStates(){return states;}

    public void handleInput(Vector3 pointer) {
        states.peek().handleInput(pointer);
    }
}
