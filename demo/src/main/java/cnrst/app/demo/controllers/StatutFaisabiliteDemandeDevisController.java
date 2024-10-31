package cnrst.app.demo.controllers;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cnrst.app.demo.models.StatutFaisabiliteDemandeDevis;
import cnrst.app.demo.repositories.StatutFaisabiliteDemandeDevisRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/statutfaisabilitedemandedevis")
public class StatutFaisabiliteDemandeDevisController {

    @Autowired
    private StatutFaisabiliteDemandeDevisRepository statutFaisabiliteDemandeDevisRepository;

    @GetMapping("/add")
    public String showAddStatutForm(Model model) {
        model.addAttribute("statut", new StatutFaisabiliteDemandeDevis());
        return "SFDDevis/add";
    }

    @PostMapping("/save")
    public String saveStatut(@ModelAttribute("statut") @Valid StatutFaisabiliteDemandeDevis statut, BindingResult result, Model model) {
       /* if (result.hasErrors()) {
            return "SFDDevis/add";
        }*/
        statut.setDateDerniereModification(LocalDateTime.now()); // Utiliser LocalDateTime pour la date actuelle
        statutFaisabiliteDemandeDevisRepository.save(statut);
        return "redirect:/statutfaisabilitedemandedevis/list";
    }


    @GetMapping("/list")
    public String listStatuts(Model model) {
        List<StatutFaisabiliteDemandeDevis> statuts = statutFaisabiliteDemandeDevisRepository.findAll();
        model.addAttribute("statuts", statuts);
        return "SFDDevis/StatutFaisabiliteDemandeDevis";
    }

    @GetMapping("/edit/{id}")
    public String showEditStatutForm(@PathVariable("id") Integer id, Model model) {
        StatutFaisabiliteDemandeDevis statut = statutFaisabiliteDemandeDevisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid statut ID: " + id));
        model.addAttribute("statut", statut);
        return "SFDDevis/add";
    }

    @PostMapping("/update/{id}")
    public String updateStatut(@PathVariable("id") Integer id, @ModelAttribute("statut") @Valid StatutFaisabiliteDemandeDevis statut, BindingResult result, Model model) {
        if (result.hasErrors()) {
            statut.setId(id);
            return "SFDDevis/add";
        }
        if (statutFaisabiliteDemandeDevisRepository.existsById(id)) {
            statut.setId(id);
            statut.setDateDerniereModification(LocalDateTime.now()); // Utiliser LocalDateTime pour la date actuelle
            statutFaisabiliteDemandeDevisRepository.save(statut);
        }
        return "redirect:/statutfaisabilitedemandedevis/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteStatut(@PathVariable("id") Integer id) {
        if (statutFaisabiliteDemandeDevisRepository.existsById(id)) {
            statutFaisabiliteDemandeDevisRepository.deleteById(id);
        }
        return "redirect:/statutfaisabilitedemandedevis/list";
    }
}
