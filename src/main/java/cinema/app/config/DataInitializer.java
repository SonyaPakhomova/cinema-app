package cinema.app.config;

import cinema.app.model.Role;
import cinema.app.model.User;
import cinema.app.service.RoleService;
import cinema.app.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("bob@gmail.com");
        user.setPassword("1234");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}
