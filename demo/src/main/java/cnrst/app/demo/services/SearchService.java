package cnrst.app.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cnrst.app.demo.repositories.PaysRepository;
import cnrst.app.demo.repositories.SecteurRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SecteurRepository secteurRepository;
    
    @Autowired
    private PaysRepository paysRepository;

    public List<Object> search(String query) {
        List<Object> results = new ArrayList<>();

        // Rechercher dans différentes entités (par exemple, Secteurs et Pays)
        results.addAll(secteurRepository.findByIntituleContainingIgnoreCase(query));
        results.addAll(paysRepository.findByIntituleContainingIgnoreCase(query));

        // Ajouter ici d'autres recherches sur d'autres entités

        return results;
    }
}
