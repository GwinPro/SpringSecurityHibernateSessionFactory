package net.jmsolotask.springMVC.controller.adminControllers;
import net.jmsolotask.springMVC.model.Role;
import net.jmsolotask.springMVC.model.User;
import net.jmsolotask.springMVC.service.RoleService;
import net.jmsolotask.springMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class DeleteAdminController {
    private final UserService userService;

    @Autowired
    public DeleteAdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "admin/delete", method = RequestMethod.GET)
    public String delete(User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }

}
