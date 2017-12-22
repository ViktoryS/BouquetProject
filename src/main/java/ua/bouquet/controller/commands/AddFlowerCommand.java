package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.builders.FlowerBuilder;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Freshness;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vikki on 13.12.2017.
 */
public class AddFlowerCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {

        String message = null;
        String type = null;

        String rqName = request.getParameter("flower_name");
        String rqLongOfStem = request.getParameter("flower_longOfStem");
        String rqPrice = request.getParameter("flower_price");
        String rqFreshness = request.getParameter("flower_freshness");
        String rqColor = request.getParameter("flower_color");
        String rqCountOfFlowers = request.getParameter("flower_countOfFlowers");

        if (Utils.paramsVerification(rqName, rqPrice, rqLongOfStem, rqFreshness, rqColor)) {
            type = "error";
            message = "Flower error  " + "&nbsp name = " + rqName +
                    ", long = " + rqLongOfStem + ", price = " + rqPrice+
            "  freshness = "+rqFreshness+"   color = "+ rqColor;
        } else {
            String name = rqName;
            Long longOfStem = null;
            Long price = null;
            Freshness freshness = null;
            Color color = null;
            Integer countOfFlowers = 0;
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try (Connection connection = dataSource.getConnection()){
                DaoFactory factory = DaoFactory.getInstance(connection);
                longOfStem = Long.parseLong(rqLongOfStem);
                price = Long.parseLong(rqPrice);
                freshness = Freshness.valueOf(rqFreshness);
                color = new Color(Integer.parseInt(rqColor));
                if(!Utils.paramsVerification(rqCountOfFlowers))
                    countOfFlowers = Integer.parseInt(rqCountOfFlowers);
                factory.createFlowerDao()
                        .create(new FlowerBuilder()
                                .setName(name)
                                .setLongOfStem(longOfStem)
                                .setPrice(price)
                                .setFreshness(freshness)
                                .setColor(color)
                                .setCountOfFlowers(countOfFlowers)
                                .buildFlower()
                        );
                message = name + " added!";
                type = "success";
                request.setAttribute("bouquets", factory.createBouquetDao().findAll());
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
