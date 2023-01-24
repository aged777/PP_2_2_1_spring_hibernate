package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru"));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru"));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru"));



      carService.add(new Car("Lambo1", 1));
      carService.add(new Car("Lambo2", 2));
      carService.add(new Car("Lambo3", 3));
      carService.add(new Car("Lambo4", 4));

      List<User> users = userService.userByCarModelAndSeries("Lambo2", 2);
      users.addAll(userService.userByCarModelAndSeries("Lambo3", 3));

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " +user.getCar().getModel());
         System.out.println("Car series = " +user.getCar().getSeries());

         System.out.println();
      }

      context.close();
   }
}
