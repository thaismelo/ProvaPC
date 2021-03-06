/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.controladores;

import br.edu.ifpe.garanhuns.provapc.controladores.ControladorQuestao;
import br.edu.ifpe.garanhuns.provapc.model.Alternativa;
import br.edu.ifpe.garanhuns.provapc.model.Questao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author Lucinaldo Melquíades Jr.
 */
@ManagedBean
@RequestScoped
public class QuestaoBuilder {
    
    private long id;
    private String enunciado;
    private double pontuacao;
    FacesContext faces = FacesContext.getCurrentInstance();
    private ControladorQuestao controlador = (ControladorQuestao) faces.getApplication().evaluateExpressionGet(faces, "#{controladorQuestao}", ControladorQuestao.class);
    private boolean alterando = false;
    List<AlternativaBuilder> alternativas = new ArrayList<AlternativaBuilder>();
    
    public QuestaoBuilder() {
        Questao q = controlador.getAlterando();
        if(q!=null) {
            this.id = q.getId();
            this.enunciado = q.getEnunciado();
            this.pontuacao = q.getPontuacao();
            this.alterando = true;
        }
        alternativas.add(new AlternativaBuilder());
        alternativas.add(new AlternativaBuilder());
    }

    public String getEnunciado() {
        return enunciado;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

   
    public Questao construir(){
        Questao novaQuestao = new Questao(id,enunciado,pontuacao);
        for(AlternativaBuilder q : this.alternativas){
            Alternativa a = q.construir();
            a.setQuestao(novaQuestao);
            novaQuestao.adicionarAlternativa(a);
        }
        return novaQuestao;
        
    }
    
    public List<AlternativaBuilder> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<AlternativaBuilder> alternativas) {
        this.alternativas = alternativas;
    }
    
    public boolean addAlternativa(AlternativaBuilder e){
        return alternativas.add(e);
    }
    
    public boolean removeAlternativa(AlternativaBuilder e){
        return alternativas.remove(e);
    }
    
}
