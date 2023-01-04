package eshop.service;

import eshop.entity.Adresse;
import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.exception.IdException;
import eshop.repository.FournisseurRepository;
import eshop.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public void create(Fournisseur fournisseur) {
        checkFournisseurIsNotNull(fournisseur);
/*        if (fournisseur.getId() == null) {
            throw new FournisseurException("id is null"); POURQUOI IL EST LA BORDEL
        }*/
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

    public Fournisseur getById(Long id) {
        if (id == null) {
            throw new IdException();
        }
        return fournisseurRepository.findById(id).orElseThrow(() -> {
            throw new FournisseurException("Fournisseur unknown");
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
        produitRepository.updateByfournisseurSetfournisseurToNull(fournisseur);
        fournisseurRepository.delete(fournisseur);
    }

    public List<Fournisseur> getAll() {
        return fournisseurRepository.findAll();
    }

    public Page<Fournisseur> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new FournisseurException();
        }
        return fournisseurRepository.findAll(pageable);
    }

    public Page<Fournisseur> getNextPage(Page<Fournisseur> page) {
        if (page == null) {
            throw new FournisseurException();
        }
        return fournisseurRepository.findAll(page.nextOrLastPageable());
    }

    public Page<Fournisseur> getPrevious(Page<Fournisseur> page) {
        if (page == null) {
            throw new FournisseurException();
        }
        return fournisseurRepository.findAll(page.previousOrFirstPageable());
    }

    public Fournisseur update(Fournisseur fournisseur) {
        Fournisseur fournisseurEnBase = getById(fournisseur.getId());
        fournisseurEnBase.setNom(fournisseur.getNom() != null ? fournisseur.getNom() : fournisseurEnBase.getNom());
/*        fournisseurEnBase.setNom(formateur.getNom() != null ? formateur.getNom() : fournisseurEnBase.getNom());
        fournisseurEnBase.setEmail(formateur.getEmail());
        fournisseurEnBase.setTelephone(formateur.getTelephone());*/
        if (fournisseur.getAdresse() != null) {
            fournisseurEnBase.setAdresse(
                    new Adresse(
                            fournisseur.getAdresse().getNumero(),
                            fournisseur.getAdresse().getRue(),
                            fournisseur.getAdresse().getCodePostal(),
                            fournisseur.getAdresse().getVille()));
        } else {
            fournisseurEnBase.setAdresse(null);
        }
//        fournisseurEnBase.setCout(formateur.getCout());
        return fournisseurRepository.save(fournisseurEnBase);
    }
}
