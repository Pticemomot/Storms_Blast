package com.storms.blast.main;

import com.storms.blast.main.entities.Rocket;
import com.storms.blast.main.entities.Storm;
import com.storms.blast.main.entities.Wall;

public class Intersector {

    public Rocket rocket;
    public Wall wall;
    public Storm storm;

    public Intersector(Rocket rocket, Wall wall, Storm storm){
        this.rocket = rocket;
        this.wall = wall;
    }

    public boolean getIntersection(){
        return rocket.getBoundingBoxRect().intersect(wall.getBoundingBoxRect());
    }

}
