/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpe.garanhuns.provapc.model.Turma;

/**
 *
 * @author Thais
 */
@ManagedBean
@RequestScoped
public class TurmaBuilder {
    private String turma;
    private int id;

    public TurmaBuilder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
    
    public Turma construir(){
        return new Turma(id,turma);
    }
    
}
