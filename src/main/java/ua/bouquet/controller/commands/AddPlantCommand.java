package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.builders.PlantBuilder;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Freshness;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddPlantCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        String message = null;
        String type = null;

        String rqName = request.getParameter("plant_name");
        String rqLongOfStem = request.getParameter("plant_longOfStem");
        String rqPrice = request.getParameter("plant_price");
        String rqFreshness = request.getParameter("plant_freshness");
        String rqCountOfBranches = request.getParameter("plant_countOfBranches");

        if (Utils.paramsVerification(rqName, rqPrice, rqLongOfStem, rqFreshness)) {
            type = "error";
            message = "Flower error  " + "&nbsp name = " + rqName +
                    ", long = " + rqLongOfStem + ", price = " + rqPrice+
                    "  freshness = "+rqFreshness+"   countOfBranches = "+ rqCountOfBranches;
        } else {
            String name = rqName;
            Long longOfStem = null;
            Long price = null;
            Freshness freshness = null;
            Integer countOfBranches = 0;
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try (Connection connection = dataSource.getConnection()){
                longOfStem = Long.parseLong(rqLongOfStem);
                price = Long.parseLong(rqPrice);
                freshness = Freshness.valueOf(rqFreshness);
                if(!Utils.paramsVerification(rqCountOfBranches))
                    countOfBranches = Integer.parseInt(rqCountOfBranches);
                DaoFactory.getInstance(connection).createPlantDao()
                        .create(new PlantBuilder()
                                .setName(name)
                                .setLongOfStem(longOfStem)
                                .setPrice(price)
                                .setFreshness(freshness)
                                .setCountOfBranches(countOfBranches)
                                .buildPlant()
                        );
                message = name + " added!";
                type = "success";
                request.setAttribute("bouquets", DaoFactory.getInstance(connection).createBouquetDao().findAll());
            } catch (NumberFormatException e) {
                message = "Error parameters!";
                type = "error";
            } catch (SQLException e){
                message = "Error connection to database! Try again later.";
                type = "error";
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("type", type);
        request.setAttribute("freshnessValues", Freshness.values());
        forward("additem");
    }
}
