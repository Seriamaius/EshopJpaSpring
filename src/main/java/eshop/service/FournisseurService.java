package eshop.service;

import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.repository.FournisseurRepository;
import eshop.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public void create(Fournisseur fournisseur) {
        checkFournisseurIsNotNull(fournisseur);
        if (fournisseur.getId() == null) {
            throw new FournisseurException("id is null");
        }
        if (fournisseur.getNom() == null || fournisseur.getNom().isEmpty()) {
            throw new FournisseurException("nom is null");
        }
        if (fournisseur.getEmail() == null || fournisseur.getEmail().isEmpty()) {
            throw new FournisseurException("email is null");
        }
        fournisseurRepository.save(fournisseur);
    }

    private void checkFournisseurIsNotNull(Fournisseur fournisseur) {
        if (fournisseur == null) {
            throw new FournisseurException("fournisseur null");
        }
    }


}
