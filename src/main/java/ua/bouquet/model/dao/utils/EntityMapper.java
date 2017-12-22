package ua.bouquet.model.dao.utils;

import ua.bouquet.model.entity.AbstractPlant;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;

import java.util.Map;

public class EntityMapper<T extends AbstractPlant> {
    public void makeUniguePlant(Map<String,T> plantMap, Bouquet result,  T abstractPlant){
        plantMap.putIfAbsent(abstractPlant.getName(), abstractPlant);
        abstractPlant = plantMap.get(abstractPlant.getName());
        addAbstractPlantToBouquet(abstractPlant, result);
        abstractPlant.setBouquet(result);
    }

    public void makeUniqueBouquet(Map<String, Bouquet> bouquetMap, T result, Bouquet bouquet){
        bouquetMap.putIfAbsent(bouquet.getName(), bouquet);
        bouquet = bouquetMap.get(bouquet.getName());
        result.setBouquet(bouquet);
        addAbstractPlantToBouquet(result, bouquet);
    }

    private void addAbstractPlantToBouquet(T abstractPlant, Bouquet bouquet){
        if(abstractPlant instanceof Flower){
            bouquet.getFlowers().add((Flower) abstractPlant);
        }else if(abstractPlant instanceof Plant){
            bouquet.getPlants().add((Plant) abstractPlant);
        }
    }
}
