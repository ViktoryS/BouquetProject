package ua.bouquet.model.dao.factory;

import ua.bouquet.model.dao.BouquetDao;
import ua.bouquet.model.dao.FlowerDAO;
import ua.bouquet.model.dao.PlantDao;

import java.sql.Connection;

public abstract class DaoFactory {

    public abstract FlowerDAO createFlowerDao();
    public abstract PlantDao createPlantDao();
    public abstract BouquetDao createBouquetDao();

    public static DaoFactory getInstance(Connection connection){
        return MySqlDaoFactory.getInstance(connection);
    }
}
