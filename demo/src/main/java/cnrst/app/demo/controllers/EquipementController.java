package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.Equipement;
import cnrst.app.demo.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class EquipementController {

    @Autowired
    private EquipementRepository equipementRepository;

    @GetMapping("/equipement/add")
    public String showAddEquipementForm(Model model) {
        model.addAttribute("equipement", new Equipement());
        return "equipement/add";
    }

    @PostMapping("/equipement/save")
    public String saveEquipement(Equipement equipement) {
        equipementRepository.save(equipement);
        return "redirect:/equipements";
    }

    @GetMapping("/equipements")
    public String listEquipements(Model model) {
        List<Equipement> equipements = equipementRepository.findAll();
        model.addAttribute("equipements", equipements);
        return "equipement/equipement";
    }

    @GetMapping("/equipement/edit/{id}")
    public String showEditEquipementForm(@PathVariable("id") Long id, Model model) {
        Optional<Equipement> optionalEquipement = equipementRepository.findById(id);
        if (optionalEquipement.isPresent()) {
            model.addAttribute("equipement", optionalEquipement.get());
            return "equipement/add";
        } else {
            return "redirect:/equipements"; // Rediriger vers la liste si l'équipement n'est pas trouvé
        }
    }

    @PostMapping("/equipement/update/{id}")
    public String updateEquipement(@PathVariable("id") Long id, Equipement equipement) {
        if (equipementRepository.existsById(id)) {
            equipement.setIdEquipement(id); // Assurez-vous que setIdEquipement est défini dans Equipement
            equipementRepository.save(equipement);
        }
        return "redirect:/equipements";
    }

    @GetMapping("/equipement/delete/{id}")
    public String deleteEquipement(@PathVariable("id") Long id) {
        if (equipementRepository.existsById(id)) {
            equipementRepository.deleteById(id);
        }
        return "redirect:/equipements";
    }
}
