package cnrst.app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @GetMapping("/maps")
    public String maps() {
        return "maps"; // Le nom de votre fichier Thymeleaf sans l'extension .html
    }
}
