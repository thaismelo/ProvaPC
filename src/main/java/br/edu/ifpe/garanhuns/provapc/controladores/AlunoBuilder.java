/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpe.garanhuns.provapc.model.Aluno;
import br.edu.ifpe.garanhuns.provapc.model.Turma;

/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@RequestScoped
public class AlunoBuilder {

    private long id;
    private String login;
    private String nome;
    private String senha;
    private Turma turma;
    FacesContext faces = FacesContext.getCurrentInstance();
    private ControladorAluno controlador = (ControladorAluno) faces.getApplication().evaluateExpressionGet(faces, "#{controladorAluno}", ControladorAluno.class);
    private boolean alterando = false;

    public AlunoBuilder() {
        Aluno a = controlador.getAlterando();
        if (a != null) {
            this.id = a.getId();
            this.login = a.getLogin();
            this.nome = a.getNome();
            this.senha = a.getSenha();
            this.turma=a.getTurma();
            this.alterando = true;
        }
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluno construir() {
        return new Aluno(id, login, nome, senha,turma);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
