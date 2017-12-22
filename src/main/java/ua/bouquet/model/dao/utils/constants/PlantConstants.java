package ua.bouquet.model.dao.utils.constants;

public interface PlantConstants {
    String TABLE = "plant";
    String ID = "id";
    String NAME = "name";
    String LONG_OF_STEM = "long_of_stem";
    String PRICE = "price";
    String COLOR = "color";
    String FRESHNESS = "freshness";
    String COUNT_OF_BRANCHES = "count_of_branches";
    String BOUQUET_ID = "bouquet_id";
    String DOT = ".";

    static String getFieldWithTable(String FIELD){
        return new StringBuilder().append(TABLE)
                .append(DOT)
                .append(FIELD)
                .toString();
    }

}
