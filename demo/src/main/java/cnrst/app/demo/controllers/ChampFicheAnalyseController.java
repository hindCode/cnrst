package cnrst.app.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.ChampFicheAnalyse;
import cnrst.app.demo.repositories.ChampFicheAnalyseRepository;
import cnrst.app.demo.repositories.LaboratoireRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ChampFicheAnalyseController {

    @Autowired
    private ChampFicheAnalyseRepository champFicheAnalyseRepository;

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @GetMapping("/champsficheanalyse")
    public String listChampFicheAnalyse(Model model) {
        List<ChampFicheAnalyse> champsFicheAnalyse = champFicheAnalyseRepository.findAll();
        model.addAttribute("champsFicheAnalyse", champsFicheAnalyse);
        return "champficheanalyse/champficheanalyse"; // Vue pour la liste des champs de fiche d'analyse
    }

    @GetMapping("/champsficheanalyse/add")
    public String showAddChampFicheAnalyseForm(Model model) {
        model.addAttribute("champFicheAnalyse", new ChampFicheAnalyse());
        model.addAttribute("laboratoires", laboratoireRepository.findAll()); // Liste des laboratoires disponibles
        return "champficheanalyse/add"; // Vue pour ajouter un nouveau champ de fiche d'analyse
    }

    @PostMapping("/champsficheanalyse/save")
    public String saveChampFicheAnalyse(ChampFicheAnalyse champFicheAnalyse) {
        champFicheAnalyseRepository.save(champFicheAnalyse);
        return "redirect:/champsficheanalyse";
    }

    @GetMapping("/champsficheanalyse/edit/{id}")
    public String showEditChampFicheAnalyseForm(@PathVariable("id") Long id, Model model) {
        Optional<ChampFicheAnalyse> optionalChampFicheAnalyse = champFicheAnalyseRepository.findById(id);
        if (optionalChampFicheAnalyse.isPresent()) {
            model.addAttribute("champFicheAnalyse", optionalChampFicheAnalyse.get());
            model.addAttribute("laboratoires", laboratoireRepository.findAll()); // Liste des laboratoires disponibles
            return "champficheanalyse/add"; // Utiliser la même vue que pour l'ajout
        } else {
            return "redirect:/champsficheanalyse"; // Rediriger vers la liste si le champ de fiche d'analyse n'est pas trouvé
        }
    }

    @PostMapping("/champsficheanalyse/update/{id}")
    public String updateChampFicheAnalyse(@PathVariable("id") Long id, ChampFicheAnalyse champFicheAnalyse) {
        if (champFicheAnalyseRepository.existsById(id)) {
            champFicheAnalyse.setId(id); // Assurez-vous que setId est défini dans ChampFicheAnalyse
            champFicheAnalyseRepository.save(champFicheAnalyse);
        }
        return "redirect:/champsficheanalyse";
    }

    @GetMapping("/champsficheanalyse/delete/{id}")
    public String deleteChampFicheAnalyse(@PathVariable("id") Long id) {
        if (champFicheAnalyseRepository.existsById(id)) {
            champFicheAnalyseRepository.deleteById(id);
        }
        return "redirect:/champsficheanalyse";
    }
}
