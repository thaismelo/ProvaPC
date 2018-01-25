/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpe.garanhuns.provapc.model.Professor;
import br.edu.ifpe.garanhuns.provapc.model.Prova;
import br.edu.ifpe.garanhuns.provapc.model.Questao;
import br.edu.ifpe.garanhuns.provapc.model.RespostaProva;
import br.edu.ifpe.garanhuns.provapc.model.Turma;

/**
 *
 * @author Thais
 */
@ManagedBean
@RequestScoped
public class ProvaBuilder {
    
    public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	private long id;
    private String titulo;
    private Turma turma;
    private Professor professor = (Professor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("professorLogado");;
    private boolean alterando = false;
    List<QuestaoBuilder> questoes = new ArrayList<QuestaoBuilder>();
    List<RespostaProva> notas = new ArrayList<RespostaProva>();
    

    public ProvaBuilder() {
        
    }

    public ProvaBuilder(Prova selected) {
        this.id = selected.getId();
        this.titulo = selected.getTitulo();
        this.professor=selected.getProfessor();
        this.turma=selected.getTurma();
        this.alterando = true;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public boolean isAlterando() {
        return alterando;
    }

    public void setAlterando(boolean alterando) {
        this.alterando = alterando;
    }

    public List<QuestaoBuilder> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoBuilder> questoes) {
        this.questoes = questoes;
    }

    public boolean addQuestao(QuestaoBuilder e) {
        return questoes.add(e);
    }

    public boolean removeQuestao(QuestaoBuilder o) {
        return questoes.remove(o);
    }
    
    public List<RespostaProva> getNotas() {
        return notas;
    }

    public boolean addNota(RespostaProva r) {
        return notas.add(r);
    }

    public boolean removeNota(RespostaProva r) {
        return notas.remove(r);
    }
    public Prova construir(){
        Prova novaProva = new Prova(id,titulo,professor,turma,notas);
        for(QuestaoBuilder q : this.questoes){
            Questao qu = q.construir();
            qu.setProva(novaProva);
            novaProva.adicionarQuestao(qu);
        }
        return novaProva;
        
    }

    
}
