package ua.bouquet.model.dao;

import ua.bouquet.model.dao.utils.QueryBuilder;
import ua.bouquet.model.dao.utils.constants.BouquetConstants;
import ua.bouquet.model.entity.AbstractPlant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.bouquet.model.dao.utils.constants.FlowerConstants.BOUQUET_ID;

public abstract class AbstractPlantDao<T extends AbstractPlant> extends AbstractDao<T> {
    public AbstractPlantDao(String tableName, Connection connection) {
        super(tableName, connection);
    }

    public List<T> findByBouquetId(Long id){
        String query = null;
        if(id != null){
            query = QueryBuilder.findWithCondition(tableName, BouquetConstants.TABLE, BOUQUET_ID);
        }else {
            query = QueryBuilder.findWithNULLCondition(tableName, BouquetConstants.TABLE, BOUQUET_ID);
        }
        List<T> result;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            if (id != null)
                statement.setLong(1, id);
            result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(getEntityFromResultSet(resultSet));
            }
            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
