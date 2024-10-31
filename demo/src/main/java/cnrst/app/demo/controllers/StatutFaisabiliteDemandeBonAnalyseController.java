package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.StatutFaisabiliteDemandeBonAnalyse;
import cnrst.app.demo.repositories.StatutFaisabiliteDemandeBonAnalyseRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class StatutFaisabiliteDemandeBonAnalyseController {

    @Autowired
    private StatutFaisabiliteDemandeBonAnalyseRepository statutRepository;

    @GetMapping("/statutfaisabilitedemandebonanalyse/add")
    public String showAddStatutForm(Model model) {
        model.addAttribute("statut", new StatutFaisabiliteDemandeBonAnalyse());
        return "SFDBonAnalyse/add";
    }

    @PostMapping("/statutfaisabilitedemandebonanalyse/save")
    public String saveStatut(StatutFaisabiliteDemandeBonAnalyse statut) {
        statutRepository.save(statut);
        return "redirect:/statutfaisabilitedemandebonanalyse/list";
    }

    @GetMapping("/statutfaisabilitedemandebonanalyse/list")
    public String listStatuts(Model model) {
        List<StatutFaisabiliteDemandeBonAnalyse> statuts = statutRepository.findAll();
        model.addAttribute("statuts", statuts);
        return "SFDBonAnalyse/statutFaisabiliteDemandeBonAnalyse";
    }

    @GetMapping("/statutfaisabilitedemandebonanalyse/edit/{id}")
    public String showEditStatutForm(@PathVariable("id") Integer id, Model model) {
        Optional<StatutFaisabiliteDemandeBonAnalyse> optionalStatut = statutRepository.findById(id);
        if (optionalStatut.isPresent()) {
            model.addAttribute("statut", optionalStatut.get());
            return "SFDBonAnalyse/add";
        } else {
            return "redirect:/statutfaisabilitedemandebonanalyse/list";
        }
    }

    @PostMapping("/statutfaisabilitedemandebonanalyse/update/{id}")
    public String updateStatut(@PathVariable("id") Integer id, StatutFaisabiliteDemandeBonAnalyse statut) {
        if (statutRepository.existsById(id)) {
            statut.setId(id); // Assurez-vous que setId est défini dans StatutFaisabiliteDemandeBonAnalyse
            statutRepository.save(statut);
        }
        return "redirect:/statutfaisabilitedemandebonanalyse/list";
    }

    @GetMapping("/statutfaisabilitedemandebonanalyse/delete/{id}")
    public String deleteStatut(@PathVariable("id") Integer id) {
        if (statutRepository.existsById(id)) {
            statutRepository.deleteById(id);
        }
        return "redirect:/statutfaisabilitedemandebonanalyse/list";
    }
}
