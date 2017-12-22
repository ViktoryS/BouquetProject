package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;
import ua.bouquet.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddInBouquetCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        if (request.getMethod().equals("POST")) forward("bouquet");

        String message = null;
        String type = null;
        String rqBouquet = request.getParameter("bouquet_id");
        String rqFlower = request.getParameter("flower_id");
        String rqPlant = request.getParameter("plant_id");

        Bouquet bouquet = null;
        List<Flower> flowerList = null;
        List<Plant> plantList = null;

        DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory factory = DaoFactory.getInstance(connection);
            if (Utils.paramsVerification(rqBouquet)) {
                message = "Error parameters!";
                type = "error";
            } else {
                bouquet = factory.createBouquetDao().findById(Long.parseLong(rqBouquet));
                if (!Utils.paramsVerification(rqFlower)) {
                    Long flower = Long.parseLong(rqFlower);
                    ServiceFactory.getInstance().createFlowerService(connection)
                            .addToBouquet(factory.createFlowerDao().findById(flower),bouquet);
                    message = "Flower added!";
                    type = "success";
                } else if (!Utils.paramsVerification(rqPlant)) {
                    Long plant = Long.parseLong(rqPlant);
                    ServiceFactory.getInstance().createPlantService(connection)
                            .addToBouquet(factory.createPlantDao().findById(plant),bouquet);
                    message = "Plant added!";
                    type = "success";
                }else {
                    message = "Error parameters!";
                    type = "error";
                }
            }
            flowerList = factory.createFlowerDao().findByBouquetId(null);
            plantList = factory.createPlantDao().findByBouquetId(null);
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
        request.setAttribute("bouquet", bouquet);
        forward("bouquet");
    }
}
