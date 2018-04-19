package com.grp12.towerdefense.gamelogic.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.grp12.towerdefense.gamelogic.statuseffects.AbstractStatusEffect;
import com.grp12.towerdefense.gamelogic.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class AbstractEnemy extends Actor {

    private int health, slowDown=10;
    private float speed, distanceX, distanceY;
    private Vector2 position = new Vector2();

    //Will be used to find direction we need to move to get to the next waypoint
    Vector2 direction = new Vector2();

    private Node currentWaypoint = null;
    private int waypointIndex;
    private ArrayList<Node> waypoints;
    private float eRotation=0;
    ArrayList<AbstractStatusEffect> statusEffects;

    public AbstractEnemy(ArrayList<Node> waypoints, float speed, int health) {
        this.waypoints = waypoints;
        this.speed = speed;
        this.health = health;
        waypointIndex = 0;
        findNextWaypoint();

        position.x = waypoints.get(0).getX();
        position.y = waypoints.get(0).getY();
    }

    public void move(float dt) {
        if (position.dst(currentWaypoint.getPosition()) <= 0.1f) {
            //TODO: #13: Implement a check to see if we have reached the end of the path, also see findNextWaypoint() for this
            findNextWaypoint();
        }

        //Calculate the direction and normalize vector
        direction.setZero();
        direction.x = currentWaypoint.getX() - position.x;
        direction.y = currentWaypoint.getY() - position.y;
        direction = direction.nor();

        //Add direction * speed * dt to current position
        position.x += (direction.x * speed * dt);
        position.y += (direction.y * speed * dt);
    }
        /**else {
            findNextWaypoint();
            eRotation=findEDegree();
            distanceX = Math.abs(position.x - currentWaypoint.getPosition().x);
            distanceY = Math.abs(position.y - currentWaypoint.getPosition().y);
        }**/


        public void setNextWaypoint (Node waypoint){
            currentWaypoint = waypoint;
        }

        public void findNextWaypoint () {
            currentWaypoint = waypoints.get(waypointIndex);
            //waypointIndex++;
            if (waypointIndex < waypoints.size()) {
                currentWaypoint = waypoints.get(waypointIndex);
                waypointIndex++;
            } else {
                System.out.println("End reached by: " + this.toString());
            }
            eRotation = findEDegree();
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

        public void setSpeed ( float speed){
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


        public ArrayList<Node> getWaypoints () {
            return (ArrayList) waypoints.clone();
        }

        public float getEnemyRotation(){return eRotation;}

    public float findEDegree(){
        Vector2 targetCoords = currentWaypoint.getPosition();
        float degree = (float) Math.atan((targetCoords.x-position.x)/(targetCoords.y-position.y));
        degree= (float) Math.toDegrees(degree);
        //System.out.print("enemy= "+ position.x +", "+ position.y +" waypoint= "+ targetCoords.x+ ", "+targetCoords.y+ "deg= "+degree+"+\n");
        if(position.x==targetCoords.x){
            if(targetCoords.y<position.y){
                degree = 0;
                System.out.print("5");
            }
            else{
                degree=180;
                System.out.print("6");
            }
        }
        else if(position.y==targetCoords.y){
            if(targetCoords.x> position.x){
                degree = -90;
                System.out.print("7");
            }
            else{
                degree=90;
                System.out.print("8");
            }
        }
        else{
            if(targetCoords.x>position.x){
                if(targetCoords.y<position.y){
                    degree=degree;
                    System.out.print("1");
                }
                else{
                    degree = 270;
                    System.out.print("2");
                }
            }
            else{
                if(targetCoords.y>position.y){
                    degree=degree;
                    System.out.print("3");
                }
                else{
                    degree=degree;
                    System.out.print("4");

                }
            }
        }
        return degree;
    }

        //Should return a deep copy of this object
        public abstract AbstractEnemy clone();

    }

