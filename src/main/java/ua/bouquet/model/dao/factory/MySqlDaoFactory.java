package ua.bouquet.model.dao.factory;

import ua.bouquet.model.dao.BouquetDao;
import ua.bouquet.model.dao.FlowerDAO;
import ua.bouquet.model.dao.PlantDao;
import ua.bouquet.model.dao.impl.MySqlBouquetDao;
import ua.bouquet.model.dao.impl.MySqlFlowerDao;
import ua.bouquet.model.dao.impl.MySqlPlantDao;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private Connection connection;

    private MySqlDaoFactory(Connection connection) {
        this.connection = connection;
    }

    public static MySqlDaoFactory getInstance(Connection connection){
        return new MySqlDaoFactory(connection);
    }

    @Override
    public FlowerDAO createFlowerDao() {
        return new MySqlFlowerDao(connection);
    }

    @Override
    public PlantDao createPlantDao() {
        return new MySqlPlantDao(connection);
    }

    @Override
    public BouquetDao createBouquetDao() {
        return new MySqlBouquetDao(connection);
    }
}
