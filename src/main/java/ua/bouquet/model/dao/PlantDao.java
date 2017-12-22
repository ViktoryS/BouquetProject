package ua.bouquet.model.dao;

import ua.bouquet.model.entity.Plant;

import java.util.List;

public interface PlantDao extends GenericDao<Plant> {
    List<Plant> findByBouquetId(Long id);
}
