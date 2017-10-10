package br.edu.ifpe.garanhuns.provapc.model.dao;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

import br.edu.ifpe.garanhuns.provapc.model.Alternativa;
import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.Prova;
import br.edu.ifpe.garanhuns.provapc.model.Questao;
import br.edu.ifpe.garanhuns.provapc.model.RespostaProva;
import br.edu.ifpe.garanhuns.provapc.model.Turma;

public class FabricaRepositoriosTest {

	@Test
	public void testeDaFabricaParaRepositorioProva(){
		RepositorioGenerico<Prova, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.prova, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioProva);
	}
	@Test
	public void testeDaFabricaParaRepositorioQuestao(){
		RepositorioGenerico<Questao, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.questao, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioQuestao);
	}
	@Test
	public void testeDaFabricaParaRepositorioAlternativa(){
		RepositorioGenerico<Alternativa, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.alternativa, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioAlternativa);
	}
	@Test
	public void testeDaFabricaParaRepositorioAluno(){
		RepositorioGenerico<Aluno, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.aluno, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioAluno);
	}
	@Test
	public void testeDaFabricaParaRepositorioProfessor(){
		RepositorioGenerico<Professor, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.professor, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioProfessor);
	}
	@Test
	public void testeDaFabricaParaRepositorioRespostaProva(){
		RepositorioGenerico<RespostaProva, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.respostaProva, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioRespostaProva);
	}
	@Test
	public void testeDaFabricaParaRepositorioTurma(){
		RepositorioGenerico<Turma, Integer> repositorioTeste = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.turma, FabricaRepositorios.BD);
		assertTrue(repositorioTeste instanceof RepositorioTurma);
	}
}
