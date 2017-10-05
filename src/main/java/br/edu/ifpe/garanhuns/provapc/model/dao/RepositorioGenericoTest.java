package br.edu.ifpe.garanhuns.provapc.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class RepositorioGenericoTest<T, G> {

	public RepositorioGenerico<T, Integer> repositorio;
	public T tipo = null;
	public Integer tipoDoID;

	// Exige uma nova prova.
	public void testAdicionar() {
		repositorio.inserir(tipo);
		//preciso ver se ele ta ali
	}

	// Recuperar Todos depois de Adicionar
	public void testPosAdicionarRecuperarTodos() {
		assertNotNull(tipo); // ver se o tipo é nulo ou não
		List<T> recuperar = repositorio.recuperarTodos();
		for (T t2 : recuperar) {
			if (tipo.equals(t2)) {
				assertEquals(t2, tipo);
			}
		}
		
		
		
	
	}

	// Tenta recuperar por id o objeto adicionado
	public void testPosAdicionarRecuperarId() {
        assertNotNull(tipo); // ver se ele é nula ou nao
        int id = 67;
        T t2 = repositorio.recuperar(id);
        assertNotNull(t2);
        assertEquals(t2, tipo);
        //preciso ver se os ids sao iguais 
    }

	/**
	 * Exige alteração
	 */
	public void testFAlterar() {
		repositorio.alterar(tipo);
	}

	/**
	 * Tenta recuperar o que foi alterado pelo id
	 */
	public void testPosAlterarRecuperarId() {
		assertNotNull(tipo);
		int id = 67;
		T t2 = repositorio.recuperar(id);
		assertNotNull(t2);
		assertEquals(t2, tipo);

	}

	/**
	 * Agora remove
	 */
	public void testRemover() {
		assertNotNull(tipo);
		repositorio.excluir(tipo);
		List<T> recuperar = repositorio.recuperarTodos();
		for (T t2 : recuperar) {
			if (tipo.equals(t2)) {
				assertEquals(t2, tipo);
				tipo = null;
				fail("Not found");
				// nao pode encontrar
			}
		}

	}

	/**
	 * Adiciono outro Exige outra prova
	 */
	public void testAdicionarOutro() {
		repositorio.inserir(tipo);
	}

	/**
	 * Recuperar Todos depois de Adicionar de novo
	 */
	public void testPosAdicionarOutroRecuperarTodos() {
		assertNotNull(tipo);
		List<T> recuperar = repositorio.recuperarTodos();
		for (T t2 : recuperar) {
			if (t2.equals(tipo)) {
				assertEquals(t2, tipo);
				tipo = t2; // será usado no próximo método
				return;
			}
		}
		tipo = null;
		fail("Not found");
	}
}
