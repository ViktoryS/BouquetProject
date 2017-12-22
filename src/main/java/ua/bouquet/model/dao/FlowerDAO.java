package ua.bouquet.model.dao;

import ua.bouquet.model.entity.Flower;

import java.util.List;

public interface FlowerDAO extends GenericDao<Flower>{
    List<Flower> findByBouquetId(Long id);
}
