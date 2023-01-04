package eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.entity.Adresse;
import eshop.entity.Civilite;
import eshop.entity.Client;
import eshop.exception.ClientException;
import eshop.exception.IdException;
import eshop.service.ClientService;

@SpringJUnitConfig(JpaConfig.class)
class ClientServiceTest {

	@Autowired
	private ClientService clientServ;
	
	@Test
	public void test() {
		assertNotNull(clientServ);
	}
	
	@Test
	public void create() {
		Client client = new Client(Civilite.M, "perrin", "brandon", "email@email", new Adresse("1", "rue de machin", "45000", "orleans"), null, "111");
		clientServ.create(client);
		assertNotNull(clientServ.getById(client.getId()));
	}

	@Test
	public void createError() {
		Client client = new Client(Civilite.M, null, "brandon", "email@email", new Adresse("1", "rue de machin", "45000", "orleans"), null, "111");
		assertThrows(ClientException.class, () -> {
			clientServ.create(client);
		});
	}
	
	@Test
	public void findByIdError() {
		assertThrows(IdException.class, () -> {
			clientServ.getById(null);
		});
		assertThrows(ClientException.class, () -> {
			clientServ.getById(1L);
		});
	}
	
	@Test
	public void getClientCommandes() {
		Client client = new Client(Civilite.M, "perrin", "brandon", "email@email", new Adresse("1", "rue de machin", "45000", "orleans"), null, "111");
		clientServ.create(client);
		assertNotNull(clientServ.getClientCommandes(client.getId()));
	}

}
