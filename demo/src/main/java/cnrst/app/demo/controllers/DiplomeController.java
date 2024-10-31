package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cnrst.app.demo.models.Diplome;
import cnrst.app.demo.repositories.DiplomeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class DiplomeController {

    @Autowired
    private DiplomeRepository diplomeRepository;

    @GetMapping("/diplome/add")
    public String showAddDiplomeForm(Model model) {
        model.addAttribute("diplome", new Diplome());
        return "diplome/add";
    }

    @PostMapping("/diplome/save")
    public String saveDiplome(@ModelAttribute("diplome") Diplome diplome) {
        // Assurez-vous que la dateDerniereModification est définie
        if (diplome.getDateDerniereModification() == null) {
            diplome.setDateDerniereModification(new Date());
        }
        diplomeRepository.save(diplome);
        return "redirect:/diplomes";
    }


    @GetMapping("/diplomes")
    public String listDiplomes(Model model) {
        List<Diplome> diplomes = diplomeRepository.findAll();
        model.addAttribute("diplomes", diplomes);
        return "diplome/diplome";
    }

    @GetMapping("/diplome/edit/{id}")
    public String showEditDiplomeForm(@PathVariable("id") Long id, Model model) {
        Optional<Diplome> optionalDiplome = diplomeRepository.findById(id);
        if (optionalDiplome.isPresent()) {
            model.addAttribute("diplome", optionalDiplome.get());
            return "diplome/add";
        } else {
            return "redirect:/diplomes"; // Rediriger vers la liste si le diplôme n'est pas trouvé
        }
    }

    @PostMapping("/diplome/update/{id}")
    public String updateDiplome(@PathVariable("id") Long id, @ModelAttribute("diplome") Diplome diplome) {
        if (diplomeRepository.existsById(id)) {
            diplome.setIdDiplome(id); // Assurez-vous que setIdDiplome est défini dans Diplome
            if (diplome.getDateDerniereModification() == null) {
                diplome.setDateDerniereModification(new Date());
            }
            diplomeRepository.save(diplome);
        }
        return "redirect:/diplomes";
    }

    @GetMapping("/diplome/delete/{id}")
    public String deleteDiplome(@PathVariable("id") Long id) {
        if (diplomeRepository.existsById(id)) {
            diplomeRepository.deleteById(id);
        }
        return "redirect:/diplomes";
    }
}
