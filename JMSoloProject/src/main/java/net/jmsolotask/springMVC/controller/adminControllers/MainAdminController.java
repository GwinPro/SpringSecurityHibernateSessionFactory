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
public class MainAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String MainAdminPageGet() {
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView mainAdminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("listUser", this.userService.getAllClient());
        modelAndView.addObject("rolesName", roleService.getRoles());
        modelAndView.setViewName("admin-jsp");
        return modelAndView;
    }



/*

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(HttpServletRequest request, Model model) {
        final HttpSession session = request.getSession();
        User user = (User) session.getAttribute("getUser");
        model.addAttribute("userName", user.getName());
        return "user-jsp";
    }

    @RequestMapping(value = "admin/update", method = RequestMethod.GET)
    public String updateGet(User user, Model model) {
        User userUpd = userService.getUserById(user.getId());
        model.addAttribute("user", userUpd);
        return "forward:/admin";
    }

    @RequestMapping(value = "admin/update", method = RequestMethod.POST)
    public String updateGet(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("UpdateResult", "User update successfully");
        return "redirect:/admin";
    }
*/

}
