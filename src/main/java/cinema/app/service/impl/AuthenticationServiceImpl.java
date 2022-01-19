package cinema.app.service.impl;

import cinema.app.model.Role;
import cinema.app.model.User;
import cinema.app.service.AuthenticationService;
import cinema.app.service.RoleService;
import cinema.app.service.ShoppingCartService;
import cinema.app.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName(Role.RoleName.USER.name()));
        user.setRoles(roles);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
