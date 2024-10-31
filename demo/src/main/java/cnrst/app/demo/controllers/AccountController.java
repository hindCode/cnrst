package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import cnrst.app.demo.models.AppUser;
import cnrst.app.demo.models.RegisterDto;
import cnrst.app.demo.repositories.AppUserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;

import java.util.Date;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @Valid @ModelAttribute RegisterDto registerDto,
                           BindingResult result
    ) {
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto","confirmPassword"
                    , "Password and Confirm Password do not match")
            );
        }

        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if(appUser != null) {
            result.addError(
                    new FieldError("registerDto", "email", "Email address is already used")
            );
        }

        if(result.hasErrors()) {
            return "register";
        }
        try {
            // Create new account
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("admin");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        } catch (Exception ex) {
            result.addError(new FieldError("registerDto","firstName",ex.getMessage())
            );
        }

        return "register";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser appUser = repo.findByEmail(username);

        model.addAttribute("appUser", appUser);

        return "profile";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login"; // Assurez-vous que cette vue existe
    }

    @GetMapping("/logout")
    public String logout() {
        // Spring Security handles the logout automatically
        return "redirect:/login?logout";
    }
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("success", false);
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("message") String message,
                          Model model) {
        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "contact";
        }
        model.addAttribute("success", true);
        return "contact";
    }
}

