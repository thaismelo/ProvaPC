/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.provapc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Thais, Lucinaldo Melquíades Jr.
 */
@Entity
@Table 
public class Questao{
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String enunciado;
    @Column
    private double pontuacao;
    
    
    @OneToMany (mappedBy = "questao" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Alternativa> alternativas= new ArrayList<Alternativa>();
    
    @ManyToOne
    @JoinColumn(name="prova_id", updatable = false)
    private Prova prova;

    public Questao(String enunciado, double pontuacao) {
        this.enunciado = enunciado;
        this.pontuacao = pontuacao;
    }

    public Questao(long id, String enunciado, double pontuacao) {
        this.id = id;
        this.enunciado = enunciado;
        this.pontuacao = pontuacao;
    }
    
    public void adicionarAlternativa(Alternativa a){
        alternativas.add(a);
    }
    public void removerAlternativa(Alternativa a){
        alternativas.remove(a);
    } 
    public long getId() {
        return id;
    }
    

    public void setId(long id) {
        this.id = id;
    }

    public Questao() {
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

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.enunciado);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.pontuacao) ^ (Double.doubleToLongBits(this.pontuacao) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.alternativas);
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
        final Questao other = (Questao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.pontuacao) != Double.doubleToLongBits(other.pontuacao)) {
            return false;
        }
        if (!Objects.equals(this.enunciado, other.enunciado)) {
            return false;
        }
        if (!Objects.equals(this.alternativas, other.alternativas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Questao{" + "id=" + id + ", enunciado=" + enunciado + ", pontuacao=" + pontuacao + ", Alternativa=" + alternativas + '}';
    }

    
    public void alterar(Questao q) {
        this.setEnunciado(q.getEnunciado());
        this.setPontuacao(q.getPontuacao());
    }

    
    public Questao copiar() {
        Questao q = new Questao(enunciado, pontuacao);
        for(Alternativa a : alternativas) {
            q.adicionarAlternativa(a.copiar());
        }
        return q;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }
    public String veracidadeDaQuestao(){
    	String veracidade=null;
    	for(int i=0;i<getAlternativas().size();i++){
    		if(getAlternativas().get(i).isVeracidade()==true){
    			veracidade=getAlternativas().get(i).getTexto();
    		}
    	}return veracidade;
    }
    
}
            
