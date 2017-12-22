package ua.bouquet.model.dao;



import ua.bouquet.model.dao.utils.QueryBuilder;
import ua.bouquet.model.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<T extends Item> implements GenericDao<T>{
    protected String tableName;
    protected Connection connection;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public void create(T entity) {
        String query = QueryBuilder.addEntity(tableName, getPropertyNames());
        try(PreparedStatement statement = connection.prepareStatement(query)){
            setEntityProperties(entity,statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findById(Long id) {
        String query = QueryBuilder.findById(tableName);
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getEntityFromResultSet(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        String query = QueryBuilder.getAll(tableName);
        List<T> result;
        try(PreparedStatement statement = connection.prepareStatement(query+";")){
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

    @Override
    public void update(T entity) {
        String query = QueryBuilder.update(tableName,getPropertyNames());
        try(PreparedStatement statement = connection.prepareStatement(query)){
            setEntityProperties(entity, statement);
            statement.setLong(getPropertyNames().length+1, entity.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String query = QueryBuilder.delete(tableName);
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    protected abstract String[] getPropertyNames();
    protected abstract void setEntityProperties(T entity, PreparedStatement statement) throws SQLException;
    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
