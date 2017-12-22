package ua.bouquet.model.dao.utils.constants;

public interface FlowerConstants {
    String TABLE = "flower";
    String ID = "id";
    String NAME = "name";
    String LONG_OF_STEM = "long_of_stem";
    String PRICE = "price";
    String COLOR = "color";
    String FRESHNESS = "freshness";
    String COUNT_OF_FLOWERS = "count_of_flowers";
    String BOUQUET_ID = "bouquet_id";
    String DOT = ".";

    static String getFieldWithTable(String FIELD){
        return new StringBuilder().append(TABLE)
                .append(DOT)
                .append(FIELD)
                .toString();
    }
}
