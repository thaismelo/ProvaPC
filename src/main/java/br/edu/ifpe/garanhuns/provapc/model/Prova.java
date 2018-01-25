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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 *
 * @author Gabriel
 */
@Entity
@Table 
public class Prova {
    

	@Id
    @GeneratedValue
    long id;
    @Column
    private String titulo;
    @OneToOne
    private Professor professor;
    @OneToOne
    private Turma turma;
    
    @OneToMany (mappedBy = "prova", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy
    private List<Questao> questoes = new ArrayList<Questao>();
    @OneToMany (mappedBy = "prova", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy
    private List<RespostaProva> notas = new ArrayList<RespostaProva>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Prova() {
    }

    public Prova(String titulo) {
        this.titulo = titulo;
    }
    
    public Prova(long id, String titulo, Professor professor, Turma turma,List<RespostaProva> notas) {
        this.id = id;
        this.titulo = titulo;
        this.professor=professor;
        this.turma=turma;
        this.notas=notas;
    }
    
    public void adicionarQuestao(Questao q){
        questoes.add(q);
    }
    
    public void removerQuestao(Questao q){
        questoes.remove(q);
    }
    public void adicionarNota(RespostaProva r){
        notas.add(r);
    }
    
    public void removerNota(RespostaProva r){
        notas.remove(r);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.titulo);
        hash = 71 * hash + Objects.hashCode(this.questoes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prova other = (Prova) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.questoes, other.questoes)) {
            return false;
        }if (this == obj) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prova{" + "id=" + id + ", titulo=" + titulo + ", Questao=" + questoes + '}';
    }

    
    public void alterar(Prova t) {
        this.setTitulo(t.getTitulo());
    }

    public Prova copiar() { //cria uma nova prova com uma questao ///  copia e ele cria uma nova prova// compara as duas provas para ver se elas sao iguais
        Prova p = new Prova(id, titulo,professor,turma,notas);
        for(Questao q : questoes) {
            p.adicionarQuestao(q.copiar());
        }
        return p;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

	public List<RespostaProva> getNotas() {
		return notas;
	}

	public void setNotas(List<RespostaProva> notas) {
		this.notas = notas;
	}
	public RespostaProva recuperarRespostaProva(Prova p){
		RespostaProva respostaProva = new RespostaProva();
		for(int i = 0;i<notas.size();i++){
			if(notas.get(i).getProva()==p){
				respostaProva=notas.get(i);
			}
		}
		return respostaProva;
	}
    
    
}
