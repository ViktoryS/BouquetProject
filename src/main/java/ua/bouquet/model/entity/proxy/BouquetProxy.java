package ua.bouquet.model.entity.proxy;

import ua.bouquet.model.dao.PlantDao;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.FlowerDAO;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BouquetProxy extends Bouquet {
    @Override
    public List<Flower> getFlowers() {
        if (super.getFlowers() == null) {
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try(Connection connection = dataSource.getConnection()){
                FlowerDAO flowerDAO = DaoFactory.getInstance(connection).createFlowerDao();
                return flowerDAO.findByBouquetId(super.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else return super.getFlowers();
    }

    @Override
    public List<Plant> getPlants() {
        if (super.getPlants() == null) {
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try(Connection connection = dataSource.getConnection()){
                PlantDao plantDAO = DaoFactory.getInstance(connection).createPlantDao();
                return plantDAO.findByBouquetId(super.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else return super.getPlants();
    }
}
