/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.ProfDao;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioProfessor;

/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@SessionScoped
public class ControladorProfessor {

    private Professor alterando = null;
    private Professor selected = null;
    RepositorioGenerico<Professor, Integer> repositorio = null;
    
   

    public ControladorProfessor() {
        this.repositorio = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.professor, FabricaRepositorios.BD);
    }

    public void remover() {
        remover(selected);
    }

    public String adicionar(Professor p) {
        if(DaoManagerHiber.getInstance().recover("from Professor where login='" + p.getLogin()+"'").size()==0){
            repositorio.inserir(p);
            return "Inicio.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Login já existe. Tente outro."));
            return "CadastrarProfessor.xhtml";
        }
        
        
    }
	DaoManagerHiber dao = DaoManagerHiber.getInstance();

    public String realizarLogin(String login, String senha){
    	if(dao.recuperarProfessorLogin(login, senha)){
    		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("professorLogado", recuperarPeloLogin(login));	
    		return "ApresentarTurma.xhtml";
    	}else{
    		FacesContext.getCurrentInstance().addMessage(null,
    				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dados incorretos ou professor ainda não cadastrado!", ""));
    		return "LogarProfessor.xhtml";
    	}
    }
    public void remover(Professor p) {
        repositorio.excluir(p);
    }

    public String alterar(Professor p) {
        repositorio.alterar(p);
        return "ApresentarProfessor.xhtml";
    }

    public List<Professor> recuperar() {
    	Professor professor = (Professor)FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("professorLogado");
    	List <Professor> listaProf= new ArrayList<Professor>();
    	listaProf.add(professor);
    	return listaProf;
    }
    public Professor recuperarPeloLogin(String login){
    	List<Professor> listaDeProfessores = recuperarTodos();
    	Professor prof = null;
    	for(int i = 0; i<listaDeProfessores.size();i++){
    		if(listaDeProfessores.get(i).getLogin().equals(login)){
    			prof=listaDeProfessores.get(i);
    		}
    	}return prof;
    }
    public List<Professor> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    public boolean autenticarLoginProfessor(){
		Professor professor = (Professor)FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("professorLogado");
		if(professor!=null){
			return true;
		}else{
			return false;
		}
	}
    public String sairDoSistema(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("professorLogado");
		return "Inicio.xhtml";
	}
    public Professor getSelected() {
        return selected;
    }

    public void setSelected(Professor selected) {
        this.selected = selected;
    }

    public Professor getAlterando() {
        return alterando;
    }

    public void setAlterando(Professor alterando) {
        this.alterando = alterando;
    }
}
