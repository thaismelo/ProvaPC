/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Thais, Lucinaldo Melquíades Jr.
 */
@Entity
@Table
public class Professor {
    
    @Id
    @GeneratedValue
    private long id;
    @Column(unique=true)
    private String login;
    @Column
    private String nome;
    @Column
    private String senha;

    public Professor(long id, String login, String nome, String senha) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.senha=senha;
    }

    public Professor(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha=senha;
    }

    public Professor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.senha);
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
        final Professor other = (Professor) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", login=" + login + ", nome=" + nome + ", senha=" + senha + '}';
    }

    

    public void alterar(Professor t) {
        this.setLogin(t.getLogin());
        this.setNome(t.getNome());
        this.setSenha(t.getSenha());
    }

    public Professor copiar() {
        return new Professor (login,nome,senha);
    }
    
    
    
}
