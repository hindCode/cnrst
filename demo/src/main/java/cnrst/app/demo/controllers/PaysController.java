package cnrst.app.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cnrst.app.demo.models.Pays;
import cnrst.app.demo.repositories.PaysRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pays") // Ajout de la base de l'URL pour toutes les méthodes
public class PaysController {

    private final PaysRepository paysRepository;

    public PaysController(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    @GetMapping
    public String getAllPays(Model model) {
        model.addAttribute("listePays", paysRepository.findAll());
        return "pays/pays";
    }

    @GetMapping("/add")
    public String createPaysForm(Model model) {
        model.addAttribute("pays", new Pays());
        return "pays/add";
    }

    @PostMapping("/save")
    public String savePays(@Valid @ModelAttribute("pays") Pays pays, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pays/add";
        }
        paysRepository.save(pays);
        return "redirect:/pays";
    }

    @GetMapping("/edit/{id}")
    public String editPaysForm(@PathVariable Integer id, Model model) {
        Pays pays = paysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Pays ID: " + id));
        model.addAttribute("pays", pays);
        return "pays/add";
    }

    @PostMapping("/update/{id}")
    public String updatePays(@PathVariable Integer id, @Valid @ModelAttribute("pays") Pays pays, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pays/add";
        }
        pays.setId(id); // Assurez-vous que l'ID est mis à jour
        paysRepository.save(pays);
        return "redirect:/pays";
    }

    @GetMapping("/delete/{id}")
    public String deletePays(@PathVariable Integer id) {
        paysRepository.deleteById(id);
        return "redirect:/pays";
    }
}
