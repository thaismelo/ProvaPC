/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;


import java.awt.List;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.Prova;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;


/**
 *
 * @author Thais
 */
@ManagedBean
@SessionScoped
public class ControladorGeral {
    
    private ProvaBuilder provaBuilder = new ProvaBuilder();
    RepositorioGenerico<Prova, Integer> repositorio = null;
    
    public ControladorGeral(){
        this.repositorio= FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.prova, FabricaRepositorios.BD);
                
    }
    public ProvaBuilder getProvaBuilder() {
        return provaBuilder;
    }

    public void setProvaBuilder(ProvaBuilder provaBuilder) {
        this.provaBuilder = provaBuilder;
    }
        
     public String finalizarProva() {
    	 if(verificarSeProvaJaExiste(provaBuilder)){
    		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Já existe uma prova com esse título! Troque e evite confusões do seus queridos alunos."));
    		 return "ProvaPronta.xhtml";
    	 }else if(provaBuilder.getTurma()==null){
    		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecione uma turma, por favor."));
    		 return "ProvaPronta.xhtml";
    	 }else if(provaBuilder.getTitulo()==null || provaBuilder.getTitulo().equals("")){
    		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adicione um título, por favor."));
    		 return "ProvaPronta.xhtml";
    	 }
    	 else{
	         Prova p = provaBuilder.construir();
	         repositorio.inserir(p);
	         return "CrudProvaProfessor.xhtml";
     	}
    }
    
    public boolean verificarSeProvaJaExiste(ProvaBuilder provaBuilder){
    	ArrayList<Prova> provas = (ArrayList<Prova>) repositorio.recuperarTodos();
    	boolean verifica=false;
    	for(Prova prova:provas){
    		if(prova.getTitulo().equals(provaBuilder.getTitulo())){
    			verifica=true;
    			return verifica;
    		}else{
    			verifica=false;
    		}
    				
    	}return verifica;
    	
    }
    public void adicionarQuestao(){
         provaBuilder.addQuestao(new QuestaoBuilder());
         
    }
     
    public void adicionarAlternativa(QuestaoBuilder qb){
         qb.addAlternativa(new AlternativaBuilder());
    }
    
}
