package ua.bouquet.model.dao.impl;

import ua.bouquet.model.builders.FlowerBuilder;
import ua.bouquet.model.dao.AbstractPlantDao;
import ua.bouquet.model.dao.FlowerDAO;
import ua.bouquet.model.entity.Flower;
import ua.bouquet.model.entity.Freshness;

import java.awt.*;
import java.sql.*;

import static ua.bouquet.model.dao.utils.constants.FlowerConstants.*;


public class MySqlFlowerDao extends AbstractPlantDao<Flower> implements FlowerDAO {

    public MySqlFlowerDao(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{NAME, LONG_OF_STEM, COLOR, FRESHNESS, PRICE, COUNT_OF_FLOWERS, BOUQUET_ID};
    }

    @Override
    protected void setEntityProperties(Flower entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setLong(2, entity.getLongOfStem());
        statement.setInt(3, entity.getColor().getRGB());
        statement.setString(4, entity.getFreshness().name());
        statement.setLong(5, entity.getPrice());
        statement.setInt(6, entity.getCountOfFlowers());
        if(entity.getBouquet() != null) {
            statement.setLong(7, entity.getBouquet().getId());
        }else {
            statement.setNull(7, Types.NULL);
        }
    }

    @Override
    protected Flower getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        FlowerBuilder builder = new FlowerBuilder();
        return builder.setId(resultSet.getLong(getFieldWithTable(ID)))
                .setName(resultSet.getString(getFieldWithTable(NAME)))
                .setLongOfStem(resultSet.getInt(getFieldWithTable(LONG_OF_STEM)))
                .setPrice(resultSet.getLong(getFieldWithTable(PRICE)))
                .setColor(new Color(resultSet.getInt(getFieldWithTable(COLOR))))
                .setFreshness(Freshness.valueOf(resultSet.getString(getFieldWithTable(FRESHNESS))))
                .setCountOfFlowers(resultSet.getInt(getFieldWithTable(COUNT_OF_FLOWERS)))
                .buildFlower();
    }
}
