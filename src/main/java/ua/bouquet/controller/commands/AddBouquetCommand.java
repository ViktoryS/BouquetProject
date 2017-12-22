package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.builders.BouquetBuilder;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;
import ua.bouquet.model.dao.utils.Utils;
import ua.bouquet.model.entity.Freshness;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vikki on 15.12.2017.
 */
public class AddBouquetCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        if(request.getMethod().equals("POST")) forward("addbouquet");

        String message = null;
        String type = null;

        String rqName = request.getParameter("name");
        String rqDiscount = request.getParameter("discount");

        if (Utils.paramsVerification(rqName, rqDiscount)) {
            type = "error";
            message = "Error  " + "&nbsp name = " + rqName +", discount = " + rqDiscount;
        } else {
            String name = rqName;
            Long discount = null;
            DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
            try (Connection connection = dataSource.getConnection()){
                discount = Long.parseLong(rqDiscount);
                DaoFactory.getInstance(connection).createBouquetDao()
                        .create(new BouquetBuilder()
                                .setName(name)
                                .setDiscount(discount)
                                .buildBouquet()
                        );
                message = name + " added!";
                type = "success";
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
        forward("addbouquet");
    }
}
