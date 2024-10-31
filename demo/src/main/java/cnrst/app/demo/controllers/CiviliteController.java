package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.Civilite;
import cnrst.app.demo.repositories.CiviliteRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CiviliteController {

    @Autowired
    private CiviliteRepository civiliteRepository;

    @GetMapping("/civilite/add")
    public String showAddCiviliteForm(Model model) {
        model.addAttribute("civilite", new Civilite());
        return "civilite/add";
    }

    @PostMapping("/civilite/save")
    public String saveCivilite(Civilite civilite) {
        civilite.setDateDerniereModification(new Date()); // Mise à jour de la date
        civiliteRepository.save(civilite);
        return "redirect:/civilites";
    }

    @GetMapping("/civilites")
    public String listCivilites(Model model) {
        List<Civilite> civilites = civiliteRepository.findAll();
        model.addAttribute("civilites", civilites);
        return "civilite/civilites";
    }

    @GetMapping("/civilite/edit/{id}")
    public String showEditCiviliteForm(@PathVariable("id") Long id, Model model) {
        Optional<Civilite> optionalCivilite = civiliteRepository.findById(id);
        if (optionalCivilite.isPresent()) {
            model.addAttribute("civilite", optionalCivilite.get());
            return "civilite/add";
        } else {
            return "redirect:/civilites"; // Rediriger vers la liste si la civilité n'est pas trouvée
        }
    }

    @PostMapping("/civilite/update/{id}")
    public String updateCivilite(@PathVariable("id") Long id, Civilite civilite) {
        if (civiliteRepository.existsById(id)) {
            civilite.setId(id); // Assurez-vous que setId est défini dans Civilite
            civilite.setDateDerniereModification(new Date()); // Mise à jour de la date
            civiliteRepository.save(civilite);
        }
        return "redirect:/civilites";
    }

    @GetMapping("/civilite/delete/{id}")
    public String deleteCivilite(@PathVariable("id") Long id) {
        if (civiliteRepository.existsById(id)) {
            civiliteRepository.deleteById(id);
        }
        return "redirect:/civilites";
    }
}
