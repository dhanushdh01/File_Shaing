package dev.dhanush.FileSharing.Controller;

import dev.dhanush.FileSharing.Model.User;
import dev.dhanush.FileSharing.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        // Retrieve user from database based on username
        User existingUser = userService.findByUsername(user.getUsername());

        // Check if user exists and password matches
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Successful login
            model.addAttribute("user", existingUser);
            return "redirect:/files/upload"; // Redirect to the upload page
        } else {
            // Failed login
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return to login page with error message
        }
    }
}
