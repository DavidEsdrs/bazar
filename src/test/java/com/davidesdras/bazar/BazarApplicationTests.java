package com.davidesdras.bazar;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.davidesdras.bazar.model.entities.Lote;
import com.davidesdras.bazar.model.entities.OrgaoDonatario;
import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;
import com.davidesdras.bazar.model.entities.Produto;
import com.davidesdras.bazar.model.repositories.ProdutoRepository;
import com.davidesdras.bazar.model.repositories.RepositoryFacade;


@SpringBootTest
class BazarApplicationTestsTest {

	@Test
	void shouldCreateLote() throws Exception {
		RepositoryFacade facade = RepositoryFacade.getCurrentInstance();

		// Cria e persiste um órgão fiscalizador
		OrgaoFiscalizador fiscalizador = new OrgaoFiscalizador(1, "Fiscalizador A", "Descrição do fiscalizador");
		facade.create(fiscalizador);

		// Cria e persiste um órgão donatário
		OrgaoDonatario donatario = new OrgaoDonatario(1, "Donatario A", "Rua 1", "123456789", "08:00-18:00", "Descrição do donatário");
		facade.create(donatario);

		// Cria produtos
		Produto produto1 = new Produto(1, "Produto 1", "Descrição 1", null);
		Produto produto2 = new Produto(2, "Produto 2", "Descrição 2", null);
		facade.create(produto1);
		facade.create(produto2);

		// Cria lote
		Lote lote = new Lote();
		lote.setId(1);
		lote.setDataEntrega(new java.util.Date());
		lote.setObservacao("Observação do lote");
		lote.setFiscalizador(fiscalizador);
		lote.setDonatario(donatario);
		lote.setDoacoes(java.util.Arrays.asList(produto1, produto2));

		facade.create(lote);

		// Busca o lote e valida
		Lote loteSalvo = facade.readLote(1);
		assertNotNull(loteSalvo);
		assertEquals(1, loteSalvo.getId());
		assertEquals("Observação do lote", loteSalvo.getObservacao());
		assertEquals("Fiscalizador A", loteSalvo.getFiscalizador().getNome());
		assertEquals("Donatario A", loteSalvo.getDonatario().getNome());
	}
}