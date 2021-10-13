package Stomped.entity;

import java.awt.Graphics2D;
import Canvas.Canvas;
import Canvas.MainGameCanvas;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {

    private static ArrayList<Entity> entities = new ArrayList<>();

    public static void update() {
        if(EntityManager.entities.size() != 0) {
            Iterator<Entity> itr = EntityManager.entities.iterator();
            while (itr.hasNext()) {
                Entity b = itr.next(); // must be called before you can call i.remove()
                if(b.step() == 1)
                    itr.remove();
                else if(b.step() == 2) {

                }
            }
        }
    }

    public static void draw(Graphics2D g, Canvas canvas) {
        MainGameCanvas mainGameCanvas = (MainGameCanvas) canvas;
        if(EntityManager.entities.size() != 0) {
            for(int i = 0; i < EntityManager.entities.size(); i++) {
                EntityManager.entities.get(i).draw(g, mainGameCanvas);
            }
        }
    }

    public static ArrayList<Entity> getEntitiesInRadius(Entity entity, double radius) {

        if(EntityManager.entities.size() != 0) {
            ArrayList<Entity> returnList = new ArrayList<>();
            Iterator<Entity> itr = EntityManager.entities.iterator();
            while(itr.hasNext()) {
                Entity e = itr.next();
                if(e != entity && Math.sqrt((entity.x - e.x)*(entity.x - e.x) + (entity.y - e.y)*(entity.y - e.y)) <= radius) {
                    returnList.add(e);
                }
            }
            return returnList;
        }

        return null;
    }

    public static void addEntity(Entity entity) {
        EntityManager.entities.add(entity);
    }

    public static int count(String type) {
        int countBullets = 0;
        for(int i = 0; i < EntityManager.entities.size(); i++) {
            if(EntityManager.entities.get(i).getClass().getSimpleName().equals(type))
                countBullets += 1;
        }
        return countBullets;
    }

    public static int count() {
        return EntityManager.entities.size();
    }

}
