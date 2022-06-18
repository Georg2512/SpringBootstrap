package ru.springbootbootstrap.util;

import ru.springbootbootstrap.model.Role;
import ru.springbootbootstrap.model.User;
import ru.springbootbootstrap.service.RoleService;
import ru.springbootbootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("Ivan", "Ivanov", 23, "ivan@mail.com", "User",
                "user", userSet);
        User admin = new User("Jhon", "Dou", 30, "jd@gmail.com", "admin",
                "admin", adminSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }
}
