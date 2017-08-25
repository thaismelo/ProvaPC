/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import br.edu.ifpe.garanhuns.provapc.controladores.ControladorProfessor;
import br.edu.ifpe.garanhuns.provapc.model.Professor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@RequestScoped
public class ProfessorBuilder {
    private long id;
    private String login;
    private String nome;
    private String senha;
    FacesContext faces = FacesContext.getCurrentInstance();
    private ControladorProfessor controlador = (ControladorProfessor) faces.getApplication().evaluateExpressionGet(faces, "#{controladorProfessor}", ControladorProfessor.class);
    private boolean alterando = false;

    public ProfessorBuilder() {
        Professor p = controlador.getAlterando();
        if(p!=null) {
            this.id = p.getId();
            this.login = p.getLogin();
            this.nome = p.getNome();
            this.senha=p.getSenha();
            this.alterando = true;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Professor construir() {
        return new Professor(id,login,nome,senha);
    }
}
