package com.bloonsTd.entities;

import java.util.ArrayList;

public abstract class EntitiesManager
{
    private ArrayList<Entity> entities;
    private ArrayList<Entity> activeEntities;
    private EntitiesTypesDictionary typeDict;

    public EntitiesManager()
    {
        this.setEntities(new ArrayList<>());
        this.setActiveEntities(new ArrayList<>());
    }

    public void init()
    {
        for (Entity entity : this.getEntities()) {
            entity.setActive(false);
        }
    }

    public void updateActiveEntities()
    {
        this.getActiveEntities().clear();
        for (Entity entity : this.getEntities()) {
            if (entity.isActive()) {
                this.getActiveEntities().add(entity);
            }
        }
    }

    /**
     * @return - returning a new entity, for example: for BalloonManager it will
     * return new Balloon();
     */
    public abstract Entity createNewEntity();

    /**
     * assigning an inactive balloon if there is one available, if there are none
     * available, creating a new one
     *
     * @return - return the new entity
     */
    public Entity addEntity()
    {
        Entity currentEntity = null;
        boolean foundEntity = false;

        // finding unused bullet
        for (Entity entity : this.getEntities()) {
            if (!entity.isActive()) {
                currentEntity = entity;
                foundEntity = true;
                break;
            }
        }

        // if could not find unused bullet, create a new one
        if (!foundEntity) {
            currentEntity = this.createNewEntity();
            currentEntity.setId(this.getEntities().size());
            this.getEntities().add(currentEntity);
        }

        // currentEntity.init(type, initXPos, initYPos);
        return currentEntity;
    }

    public EntitiesTypesDictionary getTypeDict()
    {
        return typeDict;
    }

    public void setTypeDict(EntitiesTypesDictionary typeDict)
    {
        this.typeDict = typeDict;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
    }

    public ArrayList<Entity> getActiveEntities()
    {
        return activeEntities;
    }

    public void setActiveEntities(ArrayList<Entity> activeEntities)
    {
        this.activeEntities = activeEntities;
    }

}
