package service;

import io.restassured.RestAssured;
import models.Todos;
import utilities.globalConstants.APIEndPoint;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TodosService {

    public static TodosService init() {
        return new TodosService();
    }

    public Todos[] getTodos() {
        Todos[] todos = RestAssured.given().log().all().when()
                .get(APIEndPoint.BASE_URL+"/todos").as(Todos[].class);
        return todos;
    }

    public Map<Integer, Map<Boolean, Long>> getTasksStatusForEachUsers(Todos[] todos, List<Integer> fancodeCityUsersID) {
        return Arrays.stream(todos)
                .filter(e -> fancodeCityUsersID.contains(e.getUserId()))
                .collect(Collectors.groupingBy(Todos::getUserId,
                        Collectors.groupingBy(Todos::getCompleted, Collectors.counting())));
    }
}
