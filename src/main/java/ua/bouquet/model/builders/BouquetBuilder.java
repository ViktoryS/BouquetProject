package ua.bouquet.model.builders;

import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;
import ua.bouquet.model.entity.proxy.BouquetProxy;

import java.util.List;

/**
 * Created by Vikki on 11.12.2017.
 */
public class BouquetBuilder {
    Bouquet bouquet;

    public BouquetBuilder(){
        bouquet = new BouquetProxy();
    }

    public BouquetBuilder setId(long id){
        bouquet.setId(id);
        return this;
    }

    public BouquetBuilder setName(String name){
        bouquet.setName(name);
        return this;
    }

    public BouquetBuilder setDiscount(long discount){
        bouquet.setDiscount(discount);
        return this;
    }

    public BouquetBuilder setFlowers(List<Flower> flowers){
        bouquet.setFlowers(flowers);
        return this;
    }

    public BouquetBuilder setPlants(List<Plant> plants){
        bouquet.setPlants(plants);
        return this;
    }

    public Bouquet buildBouquet(){
        return bouquet;
    }
}
