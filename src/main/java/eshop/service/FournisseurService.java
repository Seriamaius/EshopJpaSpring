package eshop.service;

import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.exception.IdException;
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
        //prout
    }

    private void checkFournisseurIsNotNull(Fournisseur fournisseur) {
        if (fournisseur == null) {
            throw new FournisseurException("fournisseur null");
        }
    }

    public Fournisseur getById(Long id) {
        if (id == null) {
            throw new IdException();
        }
        return fournisseurRepository.findByFetchproduitsCommeReferent(id).orElseThrow(() -> {
            throw new FournisseurException("Fourni unknown");
        });
    }

    public void delete(Fournisseur fournisseur) {
        checkFournisseurIsNotNull(fournisseur);
        deleteById(fournisseur.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    private void deleteById(Long id) {
        Fournisseur fournisseur = getById(id);
        fournisseurRepository.updateByReferent
    }
}
