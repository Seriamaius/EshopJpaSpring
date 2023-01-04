package eshop.service;

import eshop.entity.Fournisseur;
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

    }

    private void checkFournisseurIsNotNull(Fournisseur fournisseur) {
        if (fournisseur == null) {

        }
    }

}
