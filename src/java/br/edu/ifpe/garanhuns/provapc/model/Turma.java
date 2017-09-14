/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Thais
 */
@Entity
@Table
public class Turma {
    
   
    @Column
    private String turma;
    @Id
    @GeneratedValue
    private int id;

    public Turma(int id, String turma) {
        this.id=id;
        this.turma=turma;
    }
    
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Turma(String turma) {
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Turma() {
    }

    @Override
    public String toString() {
        return  turma;
    }

    /*@Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.turma);
        hash = 67 * hash + this.id;
        return hash;
    }*/

    
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
        final Turma other = (Turma) obj;
        return true;
    }
    
    
    
    
}
