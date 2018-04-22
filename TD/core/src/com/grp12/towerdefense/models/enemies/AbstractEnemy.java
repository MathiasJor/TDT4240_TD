package com.grp12.towerdefense.models.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grp12.towerdefense.models.statuseffects.AbstractStatusEffect;
import com.grp12.towerdefense.models.Node;

import java.util.ArrayList;

public abstract class AbstractEnemy extends Actor {

    private int health, slowDown=10;
    private int maxHealth;
    protected float speed, distanceX, distanceY;
    private Vector2 position = new Vector2();
    private int cost;
    private int bounty;
    private HealthBar healthBar;

    public enum EnemyType {BASIC, FAST}
    protected EnemyType type;

    //Will be used to find direction we need to move to get to the next waypoint
    Vector2 direction = new Vector2();

    private boolean isFinished;

    private Node currentWaypoint = null;
    private int waypointIndex;
    private ArrayList<Node> waypoints;
    private float eRotation=0;
    ArrayList<AbstractStatusEffect> statusEffects;

    public AbstractEnemy(ArrayList<Node> waypoints, float speed, int health, int cost, int bounty) {
        this.waypoints = waypoints;
        this.speed = speed;
        this.health = health;
        this.maxHealth = health;
        this.cost = cost;
        this.bounty = bounty;

        waypointIndex = 0;
        isFinished = false;

        position.x = waypoints.get(0).getX();
        position.y = waypoints.get(0).getY();

        healthBar = new HealthBar(70, 10);
    }

    public void setNextWaypoint(Node waypoint) {
        currentWaypoint = waypoint;
    }

    public void takeDamage ( int damage){
        health -= damage;
        if (health < 0)
            health = 0;
    }

    public int getHealth () {
        return health;
    }

    public void setHealth ( int health){
        this.health = health;
    }

    public Vector2 getPosition () {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSpeed (float speed){
        this.speed = speed;
    }
    public float getSpeed () {
        return speed;
    }

    public float getX () {
        return position.x;
    }

    public float getY () {
        return position.y;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getBounty(){
        return bounty;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public EnemyType getType() {
        return type;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Node getCurrentWaypoint() {
        return currentWaypoint;
    }

    public int getWaypointIndex() {
        return waypointIndex;
    }

    public void setWaypointIndex(int waypointIndex) {
        this.waypointIndex = waypointIndex;
    }

    public float getEnemyRotation(){return eRotation;}

    public void seteRotation(float eRotation) {
        this.eRotation = eRotation;
    }

    public ArrayList<Node> getWaypoints () {
        return (ArrayList) waypoints.clone();
    }

    //Should return a deep copy of this object
    public abstract AbstractEnemy clone();


}

