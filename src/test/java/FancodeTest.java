import models.Todos;
import models.Users;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.TodosService;
import service.UsersService;

import java.util.List;

public class FancodeTest {

     UsersService usersService = UsersService.init();
     TodosService todosService = TodosService.init();

    @Test
    public void GetUsersWithMoreThan50PercentTaskCompleteTest(){
        List<Integer> fancodeCityUsers = usersService.getFancodeCityUserIDs(usersService.getAllUsers());
        Todos[] todos = todosService.getTodos();
        List<Users> result = usersService.
                getUsersWithMoreThanFiftyPercentTasksCompleted(todosService.
                        getTasksStatusForEachUsers(todos, fancodeCityUsers));
        System.out.println(result);
        Assert.assertEquals(result.size(),3);
    }

    @Test
    public void GetFanCodeCityUsers(){
        List<Integer> fancodeCityUsers = usersService.getFancodeCityUserIDs(usersService.getAllUsers());
        System.out.println(fancodeCityUsers);
        Assert.assertEquals(fancodeCityUsers.size(),3);
    }

}
