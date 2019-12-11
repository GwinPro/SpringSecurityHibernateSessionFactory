package net.jmsolotask.springMVC.controller;

import net.jmsolotask.springMVC.model.Role;
import net.jmsolotask.springMVC.model.User;
import net.jmsolotask.springMVC.service.RoleService;
import net.jmsolotask.springMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Set;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RegistrationController (UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(Model model, User user) {
        String result = "User not added";
        if (userService.getUserByUserName(user.getName()) == null) {
            Set<Role> roleSet= Collections.singleton(roleService.getRoleById(2));
            user.setRoles(roleSet);
            userService.addUser(user);
            result = "User added successfully";
        }
        model.addAttribute("addResult", result);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistration() {
        return "registration";
    }
}
