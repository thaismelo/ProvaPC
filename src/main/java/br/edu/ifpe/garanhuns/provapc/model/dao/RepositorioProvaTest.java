package br.edu.ifpe.garanhuns.provapc.model.dao;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpe.garanhuns.provapc.model.Prova;

public class RepositorioProvaTest {

	private RepositorioGenerico<Prova, Integer> repositorioProva;
	private Prova prova;

	@Before
	public void setUp() {
		this.repositorioProva = new RepositorioProva();
		this.prova = new Prova();
	}

	@Test
	public void testaSeProvaFoiAdicionadaAoBanco() {
		int quantidadeDeItens = repositorioProva.recuperarTodos().size();
		assertNotNull(prova);
		repositorioProva.inserir(prova);
		assertEquals(quantidadeDeItens + 1, repositorioProva.recuperarTodos().size());
	}

	@Test
	public void testaSeProvaFoiRecuperadaPeloId() {
		repositorioProva.inserir(prova);
		assertEquals(prova, repositorioProva.recuperar((int) prova.getId()));
	}

	@Test
	public void testarSeProvaFoiAlterada() {
		prova.setTitulo("2A UNIDADE");
		Prova copia = prova;
		prova.setTitulo("3A UNIDADE");
		assertFalse(prova.equals(copia));
	}

	@Test
	public void testaRecuperarTodos() {
		Prova prova2 = new Prova();
		ArrayList<Prova> teste = new ArrayList<>();
		teste.add(prova);
		teste.add(prova2);
		repositorioProva.inserir(prova);
		repositorioProva.inserir(prova2);
		assertEquals(teste, repositorioProva.recuperarTodos());
	}

	@Test
	public void testaSeAProvaFoiRemovida() {
		repositorioProva.inserir(prova);
		repositorioProva.excluir(prova);
		assertNull(repositorioProva.recuperar((int) prova.getId()));
	}
}
