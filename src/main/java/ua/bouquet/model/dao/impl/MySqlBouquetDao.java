package ua.bouquet.model.dao.impl;

import ua.bouquet.model.builders.BouquetBuilder;
import ua.bouquet.model.dao.AbstractDao;
import ua.bouquet.model.dao.BouquetDao;
import ua.bouquet.model.dao.utils.QueryBuilder;
import ua.bouquet.model.dao.utils.constants.FlowerConstants;
import ua.bouquet.model.dao.utils.constants.PlantConstants;
import ua.bouquet.model.entity.AbstractPlant;
import ua.bouquet.model.entity.Bouquet;
import ua.bouquet.model.entity.proxy.FlowerProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.bouquet.model.dao.utils.constants.BouquetConstants.*;

public class MySqlBouquetDao extends AbstractDao<Bouquet> implements BouquetDao {

    public MySqlBouquetDao(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public Bouquet findByAbstractPlant(AbstractPlant abstractPlant) {
        if(abstractPlant.getId()==null) return null;

        String query= null;
        if(abstractPlant instanceof FlowerProxy) {
            query = QueryBuilder.findWithConditions(TABLE, FlowerConstants.TABLE);
        }else{
            query = QueryBuilder.findWithConditions(TABLE, PlantConstants.TABLE);
        }
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, abstractPlant.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return getEntityFromResultSet(resultSet);
            }else{
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String[] getPropertyNames() {
        return new String[]{NAME, DISCOUNT};
    }

    @Override
    protected void setEntityProperties(Bouquet entity, PreparedStatement statement) throws SQLException{
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getDiscount());
    }

    @Override
    protected Bouquet getEntityFromResultSet(ResultSet resultSet) throws SQLException{
        BouquetBuilder builder = new BouquetBuilder();
        return builder.setId(resultSet.getLong(getFieldWithTable(ID)))
                .setName(resultSet.getString(getFieldWithTable(NAME)))
                .setDiscount(resultSet.getLong(getFieldWithTable(DISCOUNT)))
                .buildBouquet();
    }
}
