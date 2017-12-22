package ua.bouquet.model.entity.proxy;

import ua.bouquet.model.dao.BouquetDao;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Plant;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PlantProxy extends Plant {

    @Override
    public Bouquet getBouquet(){
        if(super.getBouquet() == null) {
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                BouquetDao bouquetDao = DaoFactory.getInstance(connection).createBouquetDao();
                return bouquetDao.findByAbstractPlant(this);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else return super.getBouquet();
    }
}
