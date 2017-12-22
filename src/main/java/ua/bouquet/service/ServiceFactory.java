package ua.bouquet.service;


import ua.bouquet.service.impl.ServiceFactoryImpl;

import java.sql.Connection;

public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public abstract PlantService createPlantService(Connection connection);
    public abstract FlowerService createFlowerService(Connection connection);

    public static ServiceFactory getInstance(){
        if(serviceFactory == null)
            serviceFactory = new ServiceFactoryImpl();
        return serviceFactory;
    }
}
