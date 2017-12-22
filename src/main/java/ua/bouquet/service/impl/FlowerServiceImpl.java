package ua.bouquet.service.impl;

import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.service.FlowerService;

import java.sql.Connection;
import java.util.List;


public class FlowerServiceImpl implements FlowerService{
    private Connection connection;

    public FlowerServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addToBouquet(Flower entity, Bouquet bouquet) {
        entity.setBouquet(bouquet);
        DaoFactory.getInstance(connection).createFlowerDao().update(entity);
    }

    @Override
    public void deleteFromBouquet(Flower entity) {
        Flower newFlower = new Flower();
        newFlower.setId(entity.getId());
        newFlower.setName(entity.getName());
        newFlower.setColor(entity.getColor());
        newFlower.setCountOfFlowers(entity.getCountOfFlowers());
        newFlower.setFreshness(entity.getFreshness());
        newFlower.setLongOfStem(entity.getLongOfStem());
        newFlower.setPrice(entity.getPrice());
        newFlower.setBouquet(null);
        DaoFactory.getInstance(connection).createFlowerDao().update(newFlower);
    }

    @Override
    public List<Flower> findFreePlants() {
        return DaoFactory.getInstance(connection).createFlowerDao().findByBouquetId(null);
    }
}
