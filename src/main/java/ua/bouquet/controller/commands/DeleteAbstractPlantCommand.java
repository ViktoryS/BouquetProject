package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeleteAbstractPlantCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        String message = null;
        String type = null;

        String rqFlower = request.getParameter("flower_id");
        String rqPlant = request.getParameter("plant_id");

        List<Flower> flowerList = null;
        List<Plant> plantList = null;

        DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory factory = DaoFactory.getInstance(connection);
            if (Utils.paramsVerification(rqFlower) && Utils.paramsVerification(rqPlant)) {
                message = "Error parameters!";
                type = "error";
            } else {
                if (!Utils.paramsVerification(rqFlower)) {
                    factory.createFlowerDao().delete(Long.parseLong(rqFlower));
                    message = "Flower removed!";
                    type = "success";
                } else if (!Utils.paramsVerification(rqPlant)) {
                    factory.createPlantDao().delete(Long.parseLong(rqPlant));
                    message = "Plant removed!";
                    type = "success";
                }
            }
            flowerList = factory.createFlowerDao().findAll();
            plantList = factory.createPlantDao().findAll();
        } catch (SQLException e) {
            message = "Error! Please, try again later!";
            type = "error";
        } catch (NumberFormatException e) {
            message = "Error parameters!";
            type = "error";
        }
        request.setAttribute("message", message);
        request.setAttribute("type", type);
        request.setAttribute("listOfFlowers", flowerList);
        request.setAttribute("listOfPlants", plantList);

        forward("listflowers");
    }
}
