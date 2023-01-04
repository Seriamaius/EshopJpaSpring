package eshop;

import eshop.config.JpaConfig;
import eshop.entity.Adresse;
import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.exception.IdException;
import eshop.service.FournisseurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JpaConfig.class)
public class FournisseurServiceTest {

    @Autowired
    private FournisseurService fournisseurService;

    @Test
    void test() {
        assertNotNull(fournisseurService);
    }

    @Test
    public void insert() {
        Fournisseur f = new Fournisseur("a", "test2",new Adresse("ah","b","c","d"), "test@test.fr");
        fournisseurService.create(f);
        assertNotNull(fournisseurService.getById(f.getId()));
    }

    @Test
    void insertError() {
        Fournisseur f = new Fournisseur(null, "test2",new Adresse("ah","b","c","d"), "test@test.fr");
        assertThrows(FournisseurException.class, () -> {
           fournisseurService.create(f);
        });

        assertThrows(FournisseurException.class, () -> {
            fournisseurService.create(
                    new Fournisseur("", "test2",new Adresse("ah","b","c","d"), "test@test.fr"));
        });
    }

    @Test
    void findById() {
        assertThrows(IdException.class, () -> {
           fournisseurService.getById(null);
        });
        assertThrows(FournisseurException.class, () -> {
            fournisseurService.getById(100L);
        });
    }

    @Test
    void findByContact() {

    }

}
