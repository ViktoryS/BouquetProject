package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Plant;
import ua.bouquet.exceptions.WrongParametersException;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewBouquetCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String message = null;
        String messageFlowers = null;
        String messagePlants = null;

        String rqBouquetId = request.getParameter("bouquetid");
        Long bouquetId;
        Bouquet bouquet = null;
        List<Flower> flowerList = null;
        List<Plant> plantList = null;
        if (Utils.paramsVerification(rqBouquetId)) {
            message = "Wrong parameter, no such bouquet!";
        } else {
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                bouquetId = Long.parseLong(rqBouquetId);
                DaoFactory factory = DaoFactory.getInstance(connection);
                bouquet = factory.createBouquetDao().findById(bouquetId);
                if (bouquet == null) {
                    message = "No bouquet with such parameters!";
                }
                if (bouquet.getFlowers() == null || bouquet.getFlowers().size() == 0) {
                    messageFlowers = "There are no flowers in the bouquet.";
                }
                if (bouquet.getPlants() == null || bouquet.getPlants().size() == 0) {
                    messagePlants = "There are no plants in the bouquet.";
                }
                flowerList = factory.createFlowerDao().findByBouquetId(null);
                plantList = factory.createPlantDao().findByBouquetId(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (WrongParametersException e) {
                message = "Error of parsing parameters!";
            }

        }
        request.setAttribute("bouquet", bouquet);
        request.setAttribute("message", message);
        request.setAttribute("messageFlowers", messageFlowers);
        request.setAttribute("messagePlants", messagePlants);
        request.setAttribute("listOfFlowers", flowerList);
        request.setAttribute("listOfPlants", plantList);
        forward("bouquet");
    }
}
