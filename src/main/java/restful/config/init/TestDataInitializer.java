package restful.config.init;

import org.springframework.beans.factory.annotation.Autowired;
import restful.model.User;
import restful.model.UserRole;
import restful.service.EmployeeService;
import restful.service.RoleService;
import restful.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class TestDataInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    private void init() throws Exception {

        UserRole roleAdmin = new UserRole();
        roleAdmin.setName("ADMIN");
        roleService.save(roleAdmin);

        UserRole roleUser = new UserRole();
        roleUser.setName("USER");
        roleService.save(roleUser);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin");
        Set<UserRole> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        admin.setRoles(adminRoles);

        userService.save(admin);

        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);

        userService.save(user);

    }
}
