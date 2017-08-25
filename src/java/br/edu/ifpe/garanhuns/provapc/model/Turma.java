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
    
    @Id
    @GeneratedValue
    long id;
    @Column
    private String turma;

    public Turma(long id, String turma) {
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
    
    
    public Turma() {
    }
    
    
    
    
}
