package ua.bouquet.controller.commands;

import ua.bouquet.controller.FrontCommand;
import ua.bouquet.model.dao.factory.DaoFactory;
import ua.bouquet.model.dao.factory.DataSourceFactory;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vikki on 14.12.2017.
 */
public class ListBouquetsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        DataSource dataSource = DataSourceFactory.getInstanse().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            request.setAttribute("listOfBouquets", DaoFactory.getInstance(connection).createBouquetDao().findAll());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        forward("listbouquets");
    }
}
