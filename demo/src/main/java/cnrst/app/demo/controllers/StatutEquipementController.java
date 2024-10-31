package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cnrst.app.demo.models.StatutEquipement;
import cnrst.app.demo.repositories.StatutEquipementRepository;
import jakarta.validation.Valid;
import java.util.List;

@Controller
public class StatutEquipementController {

    @Autowired
    private StatutEquipementRepository statutEquipementRepository;

    @GetMapping("/statutEquipement/list")
    public String listStatutEquipement(Model model) {
        List<StatutEquipement> statuts = statutEquipementRepository.findAll();
        model.addAttribute("statuts", statuts);
        return "statutEquipement/statutEquipement";
    }

    @GetMapping("/statutEquipement/add")
    public String showAddForm(Model model) {
        model.addAttribute("statutEquipement", new StatutEquipement());
        return "statutEquipement/add";
    }

    @PostMapping("/statutEquipement/add")
    public String addStatutEquipement(@ModelAttribute("statutEquipement") @Valid StatutEquipement statutEquipement,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "statutEquipement/add";
        }
        statutEquipementRepository.save(statutEquipement);
        return "redirect:/statutEquipement/list";
    }

    @GetMapping("/statutEquipement/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        StatutEquipement statut = statutEquipementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid statutEquipement Id:" + id));
        model.addAttribute("statutEquipement", statut);
        return "statutEquipement/add";
    }

    @PostMapping("/statutEquipement/edit/{id}")
    public String editStatutEquipement(@PathVariable("id") Long id,
                                       @ModelAttribute("statutEquipement") @Valid StatutEquipement statutEquipement,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "statutEquipement/add";
        }
        statutEquipement.setIdStatutEquipement(id);
        statutEquipementRepository.save(statutEquipement);
        return "redirect:/statutEquipement/list";
    }

    @GetMapping("/statutEquipement/delete/{id}")
    public String deleteStatutEquipement(@PathVariable("id") Long id) {
        statutEquipementRepository.deleteById(id);
        return "redirect:/statutEquipement/list";
    }
}
