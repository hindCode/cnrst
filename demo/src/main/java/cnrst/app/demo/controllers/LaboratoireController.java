package cnrst.app.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.Laboratoire;
import cnrst.app.demo.repositories.LaboratoireRepository;
import cnrst.app.demo.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class LaboratoireController {

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/laboratoires")
    public String listLaboratoires(Model model) {
        List<Laboratoire> laboratoires = laboratoireRepository.findAll();
        model.addAttribute("laboratoires", laboratoires);
        return "laboratoire/laboratoire";
    }

    @GetMapping("/laboratoire/add")
    public String showAddLaboratoireForm(Model model) {
        model.addAttribute("laboratoire", new Laboratoire());
        // Optionnel : ajouter les services disponibles si nécessaire
        model.addAttribute("services", serviceRepository.findAll());
        return "laboratoire/add";
    }

    @PostMapping("/laboratoire/save")
    public String saveLaboratoire(Laboratoire laboratoire) {
        laboratoireRepository.save(laboratoire);
        return "redirect:/laboratoires";
    }

    @GetMapping("/laboratoire/edit/{id}")
    public String showEditLaboratoireForm(@PathVariable("id") Long id, Model model) {
        Optional<Laboratoire> optionalLaboratoire = laboratoireRepository.findById(id);
        if (optionalLaboratoire.isPresent()) {
            model.addAttribute("laboratoire", optionalLaboratoire.get());
            // Optionnel : ajouter les services disponibles si nécessaire
             model.addAttribute("services", serviceRepository.findAll());
            return "laboratoire/add"; // Utiliser une vue distincte si nécessaire
        } else {
            return "redirect:/laboratoires"; // Rediriger vers la liste si le laboratoire n'est pas trouvé
        }
    }

    @PostMapping("/laboratoire/update/{id}")
    public String updateLaboratoire(@PathVariable("id") Long id, Laboratoire laboratoire) {
        if (laboratoireRepository.existsById(id)) {
            laboratoire.setIdLaboratoire(id); // Assurez-vous que setIdLaboratoire est défini dans Laboratoire
            laboratoireRepository.save(laboratoire);
        }
        return "redirect:/laboratoires";
    }

    @GetMapping("/laboratoire/delete/{id}")
    public String deleteLaboratoire(@PathVariable("id") Long id) {
        if (laboratoireRepository.existsById(id)) {
            laboratoireRepository.deleteById(id);
        }
        return "redirect:/laboratoires";
    }
}
