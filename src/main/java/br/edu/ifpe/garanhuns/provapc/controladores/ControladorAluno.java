/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;

/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@SessionScoped
public class ControladorAluno {

	private Aluno alterando = null;
	private Aluno selected = null;

	RepositorioGenerico<Aluno, Integer> repositorio = null;

	public ControladorAluno() {
		this.repositorio = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.aluno, FabricaRepositorios.BD);
	}

	public void remover() {
		remover(selected);
	}

	public String adicionar(Aluno a) {
		if (DaoManagerHiber.getInstance().recover("from Aluno where login='" + a.getLogin() + "'").size() == 0) {
			repositorio.inserir(a);
			return "Inicio.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Login já existe. Tente outro."));
			return "CadastrarAluno.xhtml";
		}

	}

	public void remover(Aluno a) {
		repositorio.excluir(a);
	}

	public String alterar(Aluno a) {
		repositorio.alterar(a);
		return "ApresentarAluno.xhtml";
	}

	DaoManagerHiber dao = DaoManagerHiber.getInstance();

	public String realizarLogin(String login, String senha) {
		if (dao.recuperarAlunoLogin(login, senha)) {

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alunoLogado",
					recuperarPeloLogin(login));

			return "CrudProvaAluno.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Dados incorretos ou aluno ainda não cadastrado!", ""));
			return "LogarAluno.xhtml";
		}
	}

	public Aluno recuperarPeloLogin(String login) {
		List<Aluno> listaDeAlunos = recuperarTodos();
		Aluno aluno = null;
		for (int i = 0; i < listaDeAlunos.size(); i++) {
			if (listaDeAlunos.get(i).getLogin().equals(login)) {
				aluno = listaDeAlunos.get(i);
			}
		}
		return aluno;
	}

	public Aluno recupearar(int id) {
		return repositorio.recuperar(id);
	}

	public List<Aluno> recuperarTodos() {
		return repositorio.recuperarTodos();
	}

	public Aluno getSelected() {
		return selected;
	}

	public void setSelected(Aluno selected) {
		this.selected = selected;
	}

	public Aluno getAlterando() {
		return alterando;
	}

	public void setAlterando(Aluno alterando) {
		this.alterando = alterando;
	}
	public List<Aluno> recuperar() {
    	Aluno aluno = (Aluno)FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("alunoLogado");
    	List <Aluno> listaAluno= new ArrayList<Aluno>();
    	listaAluno.add(aluno);
    	return listaAluno;
    }
	public boolean autenticarLoginAluno(){
		Aluno aluno = (Aluno)FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("alunoLogado");
		if(aluno!=null){
			return true;
		}else{
			return false;
		}
	}
	public String sairDoSistema(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("alunoLogado");
		return "Inicio.xhtml";
	}
}
