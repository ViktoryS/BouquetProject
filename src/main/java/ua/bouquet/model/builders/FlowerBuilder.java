package ua.bouquet.model.builders;

import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Freshness;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.proxy.FlowerProxy;

import java.awt.*;

/**
 * Created by Vikki on 08.12.2017.
 */
public class FlowerBuilder {
    private Flower flower;

    public FlowerBuilder() {
        flower = new FlowerProxy();
    }

    public FlowerBuilder setId(long id){
        flower.setId(id);
        return this;
    }

    public FlowerBuilder setName(String name){
        flower.setName(name);
        return this;
    }

    public FlowerBuilder setLongOfStem(long longOfStem){
        flower.setLongOfStem(longOfStem);
        return this;
    }

    public FlowerBuilder setPrice(long price){
        flower.setPrice(price);
        return this;
    }

    public FlowerBuilder setFreshness(Freshness freshness){
        flower.setFreshness(freshness);
        return this;
    }

    public FlowerBuilder setColor(Color color){
        flower.setColor(color);
        return this;
    }

    public FlowerBuilder setCountOfFlowers(Integer countOfFlowers){
        flower.setCountOfFlowers(countOfFlowers);
        return this;
    }

    public FlowerBuilder setBouquet(Bouquet bouquet){
        flower.setBouquet(bouquet);
        return this;
    }

    public Flower buildFlower(){
        return flower;
    }
}
