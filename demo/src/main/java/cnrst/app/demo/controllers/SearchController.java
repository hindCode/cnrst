package cnrst.app.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cnrst.app.demo.services.SearchService;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        // Effectuer la recherche en fonction de la requête
        List<Object> results = searchService.search(query);

        // Ajouter les résultats au modèle
        model.addAttribute("results", results);
        model.addAttribute("query", query);

        // Retourner la vue des résultats de recherche
        return "search-results";
    }
}
