package ua.bouquet.model.builders;

import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Freshness;
import ua.bouquet.model.entity.Plant;
import ua.bouquet.model.entity.proxy.PlantProxy;

/**
 * Created by Vikki on 11.12.2017.
 */
public class PlantBuilder {
    Plant plant;

    public PlantBuilder(){
        plant = new PlantProxy();
    }

    public PlantBuilder setId(long id){
        plant.setId(id);
        return this;
    }

    public PlantBuilder setName(String name){
        plant.setName(name);
        return this;
    }

    public PlantBuilder setLongOfStem(long longOfStem){
        plant.setLongOfStem(longOfStem);
        return this;
    }

    public PlantBuilder setPrice(long price){
        plant.setPrice(price);
        return this;
    }

    public PlantBuilder setFreshness(Freshness freshness){
        plant.setFreshness(freshness);
        return this;
    }

    public PlantBuilder setCountOfBranches(Integer countOfBranches){
        plant.setCountOfBranches(countOfBranches);
        return this;
    }

    public PlantBuilder setBouquet(Bouquet bouquet){
        plant.setBouquet(bouquet);
        return this;
    }

    public Plant buildPlant(){
        return plant;
    }
}
