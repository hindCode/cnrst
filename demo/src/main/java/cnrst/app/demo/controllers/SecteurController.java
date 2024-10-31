package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cnrst.app.demo.models.Secteur;
import cnrst.app.demo.repositories.SecteurRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class SecteurController {

    @Autowired
    private SecteurRepository secteurRepository;

    @GetMapping("/secteur/add")
    public String showAddSecteurForm(Model model) {
        model.addAttribute("secteur", new Secteur());
        return "secteur/add";
    }

    @PostMapping("/secteur/save")
    public String saveSecteur(Secteur secteur, RedirectAttributes redirectAttributes) {
        secteur.setDateDerniereModification(new Date());
        if (secteur.getIdSecteur() == null) {
            secteurRepository.save(secteur);
            redirectAttributes.addFlashAttribute("message", "Secteur ajouté avec succès!");
        } else {
            // Update case
            if (secteurRepository.existsById(secteur.getIdSecteur())) {
                secteurRepository.save(secteur);
                redirectAttributes.addFlashAttribute("message", "Secteur mis à jour avec succès!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Secteur non trouvé!");
            }
        }
        return "redirect:/secteurs";
    }

    @GetMapping("/secteurs")
    public String listSecteurs(Model model) {
        List<Secteur> secteurs = secteurRepository.findAll();
        model.addAttribute("secteurs", secteurs);
        return "secteur/secteurs";
    }

    @GetMapping("/secteur/edit/{id}")
    public String showEditSecteurForm(@PathVariable("id") Long id, Model model) {
        Optional<Secteur> optionalSecteur = secteurRepository.findById(id);
        if (optionalSecteur.isPresent()) {
            model.addAttribute("secteur", optionalSecteur.get());
            return "secteur/add";
        } else {
            return "redirect:/secteurs";
        }
    }

    @GetMapping("/secteur/delete/{id}")
    public String deleteSecteur(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (secteurRepository.existsById(id)) {
            secteurRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Secteur supprimé avec succès!");
        }
        return "redirect:/secteurs";
    }
}
