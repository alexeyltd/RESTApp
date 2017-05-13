package restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import restful.model.User;
import restful.model.UserRole;
import restful.service.RoleService;
import restful.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminPAge(){

        return "ajax";

    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String getAdminPage(@RequestParam("login") String login, @RequestParam("password") String password,
                               @RequestParam("role") String role, ModelMap modelMap) {


        if ("ADMIN".equalsIgnoreCase(role)) {
            User admin = new User();
            admin.setLogin(login);
            admin.setPassword(password);
            Set<UserRole> adminRoles = new HashSet<>(roleService.getAll());
            admin.setRoles(adminRoles);

            userService.save(admin);


        }
        else if ("USER".equalsIgnoreCase(role)) {

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(roleService.findRoleByUserName(role));
            user.setRoles(userRoles);

            userService.save(user);

        }

        return "delete";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String getAdminPage(@RequestParam("id") long id, ModelMap modelMap) {

        userService.delete(id);

        return "signin";
    }




}
