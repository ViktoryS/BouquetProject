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


public class RemoveFromBouquetCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {

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
            if (Utils.paramsVerification(rqFlower) && Utils.paramsVerification(rqPlant)) {
                message = "Error parameters!";
                type = "error";
            } else {
                if (!Utils.paramsVerification(rqFlower)) {
                    Long flower = Long.parseLong(rqFlower);
                    ServiceFactory.getInstance().createFlowerService(connection)
                            .deleteFromBouquet(factory.createFlowerDao().findById(flower));
                    message = "Flower removed!";
                    type = "success";
                } else if (!Utils.paramsVerification(rqPlant)) {
                    Long plant = Long.parseLong(rqPlant);
                    ServiceFactory.getInstance().createPlantService(connection)
                            .deleteFromBouquet(factory.createPlantDao().findById(plant));
                    message = "Plant removed!";
                    type = "success";
                }
            }
            bouquet = factory.createBouquetDao().findById(Long.parseLong(rqBouquet));
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
