package ua.bouquet.service.impl;

import ua.bouquet.service.FlowerService;
import ua.bouquet.service.PlantService;
import ua.bouquet.service.ServiceFactory;

import java.sql.Connection;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public PlantService createPlantService(Connection connection) {
        return new PlantServiceImpl(connection);
    }

    @Override
    public FlowerService createFlowerService(Connection connection) {
        return new FlowerServiceImpl(connection);
    }
}
