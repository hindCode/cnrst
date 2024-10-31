package cnrst.app.demo.controllers;

import cnrst.app.demo.models.MoyensContact;
import cnrst.app.demo.repositories.MoyensContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/moyenscontact")
public class MoyensContactController {

    @Autowired
    private MoyensContactRepository moyensContactRepository;

    @GetMapping
    public String listMoyensContact(Model model) {
        List<MoyensContact> moyensContacts = moyensContactRepository.findAll();
        model.addAttribute("moyensContacts", moyensContacts); // Utilisez "moyensContacts" pour correspondre à la vue
        return "moyenscontact/moyenscontacts";
    }

    @GetMapping("/add")
    public String showAddMoyensContactForm(Model model) {
        model.addAttribute("moyensContact", new MoyensContact());
        return "moyenscontact/add";
    }

    @PostMapping("/save")
    public String saveMoyensContact(@ModelAttribute MoyensContact moyensContact) {
        moyensContactRepository.save(moyensContact);
        return "redirect:/moyenscontact";
    }


    @GetMapping("/edit/{id}")
    public String showEditMoyensContactForm(@PathVariable("id") Long id, Model model) {
        Optional<MoyensContact> optionalMoyensContact = moyensContactRepository.findById(id);
        if (optionalMoyensContact.isPresent()) {
            model.addAttribute("moyensContact", optionalMoyensContact.get());
            return "moyenscontact/add";
        } else {
            return "redirect:/moyenscontact";
        }
    }

    @PostMapping("/update/{id}")
    public String updateMoyensContact(@PathVariable("id") Long id, @ModelAttribute MoyensContact moyensContact) {
        if (moyensContactRepository.existsById(id)) {
            moyensContact.setIdMoyensContact(id);
            moyensContactRepository.save(moyensContact);
        }
        return "redirect:/moyenscontact";
    }


    @GetMapping("/delete/{id}")
    public String deleteMoyensContact(@PathVariable("id") Long id) {
        if (moyensContactRepository.existsById(id)) {
            moyensContactRepository.deleteById(id);
        }
        return "redirect:/moyenscontact";
    }
}
