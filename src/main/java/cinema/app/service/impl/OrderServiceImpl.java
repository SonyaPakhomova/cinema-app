package cinema.app.service.impl;

import cinema.app.dao.OrderDao;
import cinema.app.model.Order;
import cinema.app.model.ShoppingCart;
import cinema.app.model.User;
import cinema.app.service.OrderService;
import cinema.app.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setTickets(shoppingCart.getTickets());
        order.setUser(shoppingCart.getUser());
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersHistory(user);
    }
}
