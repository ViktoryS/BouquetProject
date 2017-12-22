package ua.bouquet.model.dao.utils.constants;

public interface BouquetConstants {
    String TABLE = "bouquet";
    String NAME = "name";
    String DISCOUNT = "discount";
    String DOT = ".";
    String ID = "id";

    static String getFieldWithTable(String FIELD){
        return new StringBuilder().append(TABLE)
                .append(DOT)
                .append(FIELD)
                .toString();
    }
}
