package utilities.common;

import models.Users;

public class AppUtils {

    public static boolean validGeo(Users users){
        if (users.getAddress().getGeo().getLat() >= -40 && users.getAddress().getGeo().getLat() <= 5) {
            if (users.getAddress().getGeo().getLng() >= 5 && users.getAddress().getGeo().getLng() <= 100) {
                return true;
            }
        }
        return false;
    }
}
