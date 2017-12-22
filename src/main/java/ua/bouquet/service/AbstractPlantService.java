package ua.bouquet.service;

import ua.bouquet.model.entity.AbstractPlant;
import ua.bouquet.model.entity.Bouquet;

import java.util.List;

public interface AbstractPlantService<T extends AbstractPlant> {
    void addToBouquet(T entity, Bouquet bouquet);
    void deleteFromBouquet(T entity);
    List<T> findFreePlants();
}
