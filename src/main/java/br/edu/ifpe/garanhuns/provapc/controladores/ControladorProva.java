/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.Prova;
import br.edu.ifpe.garanhuns.provapc.model.RespostaProva;
import br.edu.ifpe.garanhuns.provapc.model.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioRespostaProva;

/**
 *
 * @author Thais
 */
@ManagedBean(eager = true)
@SessionScoped
public class ControladorProva {

	private Prova alterando = null;
	private Prova selected = null;
	private ProvaBuilder builder = new ProvaBuilder();
	private ControladorResposta resposta = new ControladorResposta();

	RepositorioGenerico<Prova, Integer> repositorio = null;

	public ControladorResposta getResposta() {
		return resposta;
	}

	public void setResposta(ControladorResposta resposta) {
		this.resposta = resposta;
	}

	public ControladorProva() {
		this.repositorio = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.prova, FabricaRepositorios.BD);
	}

	public void remover() {
		remover(selected);
	}

	public String adicionar(Prova p) {
		repositorio.inserir(p);
		this.builder = new ProvaBuilder();
		return "ApresentarProva.xhtml";
	}

	public void remover(Prova p) {
		repositorio.excluir(p);
	}

	public String alterar(Prova p) {
		repositorio.alterar(p);
		return "CrudProvaProfessor.xhtml";
	}

	public Prova recupearar(int id) {
		return repositorio.recuperar(id);
	}

	public List<Prova> recuperarTodos() {
		return repositorio.recuperarTodos();
	}

	public List<RespostaProva> recuperarNotas() {
		return selected.getNotas();
	}

	public List<Prova> recuperarProvasParaProfessor() {
		List<Prova> provasDoProfessor = new ArrayList<Prova>();
		List<Prova> listaProvas = recuperarTodos();
		Professor prof = (Professor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("professorLogado");
		for (int i = 0; i < listaProvas.size(); i++) {
			if (listaProvas.get(i).getProfessor().getLogin().equals(prof.getLogin())) {
				provasDoProfessor.add(listaProvas.get(i));

			}
		}
		return provasDoProfessor;
	}

	public List<Prova> recuperarProvasParaAluno() {
		List<Prova> provasDoAluno = new ArrayList<Prova>();
		List<Prova> listaProvas = recuperarTodos();
		Aluno aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("alunoLogado");
		for (int i = 0; i < listaProvas.size(); i++) {
			if (listaProvas.get(i).getTurma().getTurma().equals(aluno.getTurma().getTurma())) {
				provasDoAluno.add(listaProvas.get(i));

			}
		}
		return provasDoAluno;
	}

	public Prova getSelected() {
		return selected;
	}

	public void setSelected(Prova selected) {
		this.selected = selected;
	}

	public Prova getAlterando() {
		return alterando;
	}

	public void setAlterando(Prova alterando) {
		this.alterando = alterando;
	}

	public String getDialogName() {
		if (alterando != null) {
			return "Alterando";
		} else {
			return "Criando";
		}
	}

	public ProvaBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(ProvaBuilder builder) {
		this.builder = builder;
	}

	public void criarBuilder() {
		this.setBuilder(new ProvaBuilder());
	}

	public void alterarBuilder() {
		this.setBuilder(new ProvaBuilder(this.selected));
	}

	public void adicionar() {
		this.adicionar(this.builder.construir());
		criarBuilder();
	}

	public String responder() {
		resposta = new ControladorResposta(selected);
		return "ResponderProva.xhtml";

	}

	public String retornaPag() {
		return "ResponderQuestao.xhtml";
	}

	public RespostaProva recuperarRespostaProva() {
		RespostaProva respostaProva = (RespostaProva) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("respostaProva");
		;
		return respostaProva;
	}

	public String recuperarRespostaProvaPeloLogin(Prova p) {
		Aluno aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("alunoLogado");
		List<RespostaProva> respostasProva = p.getNotas();
		if (isDesabilitado(p)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você ainda não fez essa prova."));
			return "CrudProvaAluno.xhtml";
		} else
			for (RespostaProva respostaProva : respostasProva) {
				if (respostaProva.getLoginAluno().equals(aluno.getLogin())) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("respostaProva",
							respostaProva);
					return "Gabarito.xhtml";
				}
			}
		return null;
	}

	

	public boolean isDesabilitado(Prova p) {
		if (p != null) {
			Aluno aluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("alunoLogado");
			List<RespostaProva> respostasProva = p.getNotas();
			for (RespostaProva respostaProva : respostasProva) {
				if (respostaProva.getLoginAluno().equals(aluno.getLogin())) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
