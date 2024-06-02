package service;

import io.restassured.RestAssured;
import models.Users;
import utilities.common.AppUtils;
import utilities.globalConstants.APIEndPoint;

import java.util.*;
import java.util.stream.Collectors;

public class UsersService {

    public static UsersService init() {
        return new UsersService();
    }


    public Users[] getAllUsers() {
        return RestAssured.given().log().all().when()
                .get(APIEndPoint.BASE_URL+"/users")
                .as(Users[].class);
    }

    public List<Integer> getFancodeCityUserIDs(Users[] users) {
        return Arrays.stream(users).filter(e -> AppUtils.validGeo(e))
                .map(e -> e.getId())
                .collect(Collectors.toList());
    }


    public List<Users> getUsersWithMoreThanFiftyPercentTasksCompleted(Map<Integer, Map<Boolean, Long>> map) {
        Users[] users = getAllUsers();
        List<Users> result = new ArrayList<>();
        for (Map.Entry<Integer, Map<Boolean, Long>> entry : map.entrySet()) {
            Collection<Long> val = entry.getValue().values();
            Integer sum = val.stream().mapToInt(i -> i.intValue()).sum();
            if (val.stream().findFirst().get().intValue() / sum <= 0.5) {
              result.addAll(Arrays.stream(users).filter(e -> e.getId().equals(entry.getKey())).collect(Collectors.toList()));
            }
        }
        return result;
    }
}

