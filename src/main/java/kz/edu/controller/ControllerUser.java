package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerUser
{
    private final UserDAO userDAO;
    @Autowired
    public ControllerUser(UserDAO userDAO)
    { this.userDAO = userDAO;}
    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder)
    { this.passwordEncoder = passwordEncoder;}
    @RequestMapping(value={"", "/", "home"})
    public String home()
    {
        return "home";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("username") String email, Model model)
    {
        System.out.println("REGISTRATION:"+email);

        if (userDAO.findByUserName(email) != null)
        {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        else
        {
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
            return "redirect:/login";
        }
    }
    @GetMapping("/change")
    public String change()
    {
        return "change";
    }
    @PostMapping("/change")
    public String change(User user, @RequestParam("password") String password, Model model)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.ChangePassword(user);
        return "redirect:/login";
    }
}