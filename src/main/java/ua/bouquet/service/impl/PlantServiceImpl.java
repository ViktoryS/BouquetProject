package ua.bouquet.service.impl;

import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Plant;
import ua.bouquet.service.PlantService;

import java.sql.Connection;
import java.util.List;

public class PlantServiceImpl implements PlantService {
    private Connection connection;

    public PlantServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addToBouquet(Plant entity, Bouquet bouquet) {
        entity.setBouquet(bouquet);
        DaoFactory.getInstance(connection).createPlantDao().update(entity);
    }

    @Override
    public void deleteFromBouquet(Plant entity) {
        Plant newPlant = new Plant();
        newPlant.setId(entity.getId());
        newPlant.setName(entity.getName());
        newPlant.setCountOfBranches(entity.getCountOfBranches());
        newPlant.setFreshness(entity.getFreshness());
        newPlant.setLongOfStem(entity.getLongOfStem());
        newPlant.setPrice(entity.getPrice());
        newPlant.setBouquet(null);
        DaoFactory.getInstance(connection).createPlantDao().update(newPlant);
    }

    @Override
    public List<Plant> findFreePlants() {
        return DaoFactory.getInstance(connection).createPlantDao().findByBouquetId(null);
    }
}

