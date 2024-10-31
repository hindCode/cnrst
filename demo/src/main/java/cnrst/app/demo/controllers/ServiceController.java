package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cnrst.app.demo.models.Service;
import cnrst.app.demo.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/service/add")
    public String showAddServiceForm(Model model) {
        model.addAttribute("service", new Service());
        return "service/add";
    }

    @PostMapping("/service/save")
    public String saveService(Service service) {
        serviceRepository.save(service);
        return "redirect:/services";
    }

    @GetMapping("/services")
    public String listServices(Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        return "service/service";
    }

    @GetMapping("/service/edit/{id}")
    public String showEditServiceForm(@PathVariable("id") Long id, Model model) {
        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            model.addAttribute("service", optionalService.get());
            return "service/add";
        } else {
            return "redirect:/services"; // Rediriger vers la liste si le service n'est pas trouvé
        }
    }

    @PostMapping("/service/update/{id}")
    public String updateService(@PathVariable("id") Long id, Service service) {
        if (serviceRepository.existsById(id)) {
            service.setIdService(id); // Assurez-vous que setIdService est défini dans Service
            serviceRepository.save(service);
        }
        return "redirect:/services";
    }

    @GetMapping("/service/delete/{id}")
    public String deleteService(@PathVariable("id") Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
        }
        return "redirect:/services";
    }
}
