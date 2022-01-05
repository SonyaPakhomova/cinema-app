package cinema.app;

import cinema.app.exception.AuthenticationException;
import cinema.app.lib.Injector;
import cinema.app.model.CinemaHall;
import cinema.app.model.Movie;
import cinema.app.model.MovieSession;
import cinema.app.model.Order;
import cinema.app.model.User;
import cinema.app.security.AuthenticationService;
import cinema.app.service.CinemaHallService;
import cinema.app.service.MovieService;
import cinema.app.service.MovieSessionService;
import cinema.app.service.OrderService;
import cinema.app.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema.app");

    public static void main(String[] args) {
        MovieService movieService = (MovieService)
                injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious",
                "An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");
        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");
        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(secondCinemaHall);
        cinemaHallService.add(firstCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(fastAndFurious.getId(),
                LocalDate.now()));

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        User user;
        try {
            user = authenticationService.register("mateacademy@gmail.com", "qwertyu");
            user = authenticationService.login("mateacademy@gmail.com", "qwertyu");
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(tomorrowMovieSession, user);
        shoppingCartService.addSession(yesterdayMovieSession, user);

        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCartService.getByUser(user));
        System.out.println(order);

        List<Order> orderHistory = orderService.getOrdersHistory(user);
        System.out.println(orderHistory);
    }
}
