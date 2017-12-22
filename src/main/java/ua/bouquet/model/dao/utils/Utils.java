package ua.bouquet.model.dao.utils;

/**
 * Created by Vikki on 11.12.2017.
 */
public class Utils {
    private Utils(){}

    public static boolean paramsVerification(String... args){
        for(String arg:args){
            if(arg == null || arg.equals("")){
                return true;
            }
        }
        return false;
    }
}
