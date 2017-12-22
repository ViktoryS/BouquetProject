package ua.bouquet.model.dao.impl;

import ua.bouquet.model.builders.PlantBuilder;
import ua.bouquet.model.dao.AbstractPlantDao;
import ua.bouquet.model.dao.PlantDao;
import ua.bouquet.model.entity.Freshness;
import ua.bouquet.model.entity.Plant;

import java.sql.*;

import static ua.bouquet.model.dao.utils.constants.PlantConstants.*;

public class MySqlPlantDao extends AbstractPlantDao<Plant> implements PlantDao {

    public MySqlPlantDao(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{NAME, LONG_OF_STEM, FRESHNESS, PRICE, COUNT_OF_BRANCHES, BOUQUET_ID};
    }

    @Override
    protected void setEntityProperties(Plant entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setLong(2, entity.getLongOfStem());
        statement.setString(3, entity.getFreshness().name());
        statement.setLong(4, entity.getPrice());
        statement.setInt(5, entity.getCountOfBranches());
        if(entity.getBouquet() != null) {
            statement.setLong(6, entity.getBouquet().getId());
        }else {
            statement.setNull(6, Types.NULL);
        }
    }

    @Override
    protected Plant getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        PlantBuilder builder = new PlantBuilder();
        return builder.setId(resultSet.getLong(getFieldWithTable(ID)))
                .setName(resultSet.getString(getFieldWithTable(NAME)))
                .setLongOfStem(resultSet.getInt(getFieldWithTable(LONG_OF_STEM)))
                .setPrice(resultSet.getLong(getFieldWithTable(PRICE)))
                .setFreshness(Freshness.valueOf(resultSet.getString(getFieldWithTable(FRESHNESS))))
                .setCountOfBranches(resultSet.getInt(getFieldWithTable(COUNT_OF_BRANCHES)))
                .buildPlant();
    }
}
