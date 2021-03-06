/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpe.garanhuns.provapc.model.Questao;
import br.edu.ifpe.garanhuns.provapc.model.dao.FabricaRepositorios;
import br.edu.ifpe.garanhuns.provapc.model.dao.RepositorioGenerico;

/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@RequestScoped
public class ControladorQuestao {

    private Questao alterando = null;
    private Questao selected = null;

    RepositorioGenerico<Questao, Integer> repositorio = null;

    public ControladorQuestao() {
        this.repositorio = FabricaRepositorios.fabricarRepositorio(FabricaRepositorios.questao, FabricaRepositorios.BD);
    }

    public void remover() {
        remover(selected);
    }

    public String adicionar(Questao q) {
        repositorio.inserir(q);
        return "ApresentarQuestao.xhtml";
    }

    public void remover(Questao q) {
        repositorio.excluir(q);
    }

    public String alterar(Questao q) {
        repositorio.alterar(q);
        return "ApresentarQuestao.xhtml";
    }

    //public String alterar() {
    //    this.alterando = selected;
    //    return "CadastrarQuestao.xhtml";
    //}

    public Questao recupearar(int id) {
        return repositorio.recuperar(id);
    }

    public List<Questao> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

    public Questao getSelected() {
        return selected;
    }

    public void setSelected(Questao selected) {
        this.selected = selected;
    }

    public Questao getAlterando() {
        return alterando;
    }

    public void setAlterando(Questao alterando) {
        this.alterando = alterando;
    }
}
