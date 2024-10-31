package cnrst.app.demo.controllers;

import cnrst.app.demo.models.MoyenPaiement;
import cnrst.app.demo.repositories.MoyenPaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/moyenpaiement")
public class MoyenPaiementController {

    @Autowired
    private MoyenPaiementRepository moyenPaiementRepository;

    @GetMapping("/add")
    public String showAddMoyenPaiementForm(Model model) {
        model.addAttribute("moyenPaiement", new MoyenPaiement());
        return "moyenpaiement/add";
    }

    @PostMapping("/save")
    public String saveMoyenPaiement(MoyenPaiement moyenPaiement) {
        moyenPaiementRepository.save(moyenPaiement);
        return "redirect:/moyenpaiement";
    }

    @GetMapping
    public String listMoyenPaiements(Model model) {
        List<MoyenPaiement> moyenPaiements = moyenPaiementRepository.findAll();
        model.addAttribute("moyenPaiements", moyenPaiements);
        return "moyenpaiement/moyenpaiements";
    }

    @GetMapping("/edit/{id}")
    public String showEditMoyenPaiementForm(@PathVariable("id") Long id, Model model) {
        Optional<MoyenPaiement> optionalMoyenPaiement = moyenPaiementRepository.findById(id);
        if (optionalMoyenPaiement.isPresent()) {
            model.addAttribute("moyenPaiement", optionalMoyenPaiement.get());
            return "moyenpaiement/edit";
        } else {
            return "redirect:/moyenpaiements";
        }
    }

    @PostMapping("/update/{id}")
    public String updateMoyenPaiement(@PathVariable("id") Long id, MoyenPaiement moyenPaiement) {
        if (moyenPaiementRepository.existsById(id)) {
            moyenPaiement.setIdMoyenPaiement(id);
            moyenPaiementRepository.save(moyenPaiement);
        }
        return "redirect:/moyenpaiements";
    }

    @GetMapping("/delete/{id}")
    public String deleteMoyenPaiement(@PathVariable("id") Long id) {
        if (moyenPaiementRepository.existsById(id)) {
            moyenPaiementRepository.deleteById(id);
        }
        return "redirect:/moyenpaiements";
    }
}
