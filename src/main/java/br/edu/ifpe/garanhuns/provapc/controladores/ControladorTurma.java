/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpe.garanhuns.provapc.model.Turma;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;

/**
 *
 * @author Thais
 */
@ManagedBean(eager = true)
@SessionScoped
public class ControladorTurma {
    private Turma selected =null;
    private Turma alterando=null;
    
    RepositorioGenerico<Turma, Integer> repositorio = null;
    
    public ControladorTurma(){
        this.repositorio= FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.turma, FabricaRepositorios.BD);
    }
    
    public String adicionar(Turma t){
        repositorio.inserir(t);
        return "ApresentarTurma.xhtml";
    }
    public void remover(Turma t){
        repositorio.excluir(t);
    }
    
    public String alterar(Turma t){
        repositorio.alterar(t);
        return "ApresentarTurma.xhtml";
    }
    
    public Turma recuperar(int id){
        return repositorio.recuperar(id);
    }
    
    public List<Turma> recuperarTodos(){
       return  repositorio.recuperarTodos();
    }

    public Turma getSelected() {
        return selected;
    }

    public void setSelected(Turma selected) {
        this.selected = selected;
    }

    public Turma getAlterando() {
        return alterando;
    }

    public void setAlterando(Turma alterando) {
        this.alterando = alterando;
    }
    
    public void remover(){
        remover(selected);
    }
    
    //public String alterar(){
    //    this.alterando=selected;
    //    return "CadastrarTurma.xhtml";
    //}
    
}
