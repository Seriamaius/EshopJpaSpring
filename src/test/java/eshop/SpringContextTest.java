package eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;

@SpringJUnitConfig(JpaConfig.class)
class SpringContextTest {

	@Autowired
	EntityManagerFactory emf;

	@Test
	void test() {
		assertNotNull(emf);
	}

}
