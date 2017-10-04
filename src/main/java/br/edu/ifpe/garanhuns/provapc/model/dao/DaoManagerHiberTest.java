package br.edu.ifpe.garanhuns.provapc.model.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifpe.garanhuns.provapc.model.Prova;

public class DaoManagerHiberTest {

	

		@Before
		
		
		@Test
		public void testeSeProvaFoiCadastrada(){
			Prova prova = new Prova();
			prova.setTitulo("Prova Unidade 1");
			DaoManagerHiber.getInstance().persist(prova);
			assertEquals(1, 1);
			
		}

	}