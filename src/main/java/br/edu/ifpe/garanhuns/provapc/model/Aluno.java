package br.edu.ifpe.garanhuns.provapc.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thais, Lucinaldo Melquíades Jr.
 */
@Entity
@Table
public class Aluno {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique=true) 
    private String login;
    @Column 
    private String nome;
    @Column
    private String senha;
    @ManyToOne
    private Turma turma;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
    

    public Aluno(long id, String login, String nome, String senha, Turma turma) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.turma=turma;
    }

    public Aluno(String login, String nome, String senha, Turma turma) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.turma=turma;
    }

    public Aluno() {
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.login);
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.senha);
        hash = 37 * hash + Objects.hashCode(this.turma);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.turma, other.turma)) {
            return false;
        }
        return true;
    }

   

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", login=" + login + ", nome=" + nome + ", senha=" + senha + ", turma=" + turma + '}';
    }

   

    public void alterar(Aluno t) {
        this.setNome(t.getNome());
        this.setLogin(t.getLogin());
        this.setSenha(t.getSenha());
        this.setTurma(t.getTurma());
    }

    public Aluno copiar() {
        return new Aluno(login, nome, senha, turma);
    }

}
