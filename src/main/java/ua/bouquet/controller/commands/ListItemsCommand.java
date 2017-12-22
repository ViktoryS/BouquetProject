package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListItemsCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory factory = DaoFactory.getInstance(connection);
            List<Flower> flowers = factory.createFlowerDao().findAll();
            List<Plant> plants = factory.createPlantDao().findAll();
            request.setAttribute("listOfFlowers", flowers);
            request.setAttribute("listOfPlants", plants);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        forward("listflowers");
    }
}
