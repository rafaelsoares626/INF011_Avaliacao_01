package Model;

import java.util.List;

public class Curso extends Produto implements PrototipavelIF{
	
	private List<Disciplina> disciplinas;
	private List<Livro> livros;
	
	public Curso(List<Disciplina> disciplinas, List<Livro> livros, String nome, String codigo) {
		super(nome, codigo);
		this.disciplinas = disciplinas;
		this.livros = livros;
	}
	
	public Curso(Curso curso) {
		super(curso.getNome(), curso.getCodigo());
		this.setDisciplinas(curso.getDisciplinas());
		this.setLivros(curso.getLivros());
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	public double getChTotal() {
		double chTotalCurso = 0;
		for(Disciplina d : this.disciplinas)
			chTotalCurso += d.getChTotal();
		return chTotalCurso;
	}
	
	public double getPctCumprido() {
		double pctCumpridoCurso = 0;		
		for(Disciplina d : this.disciplinas) {			
			pctCumpridoCurso = pctCumpridoCurso + d.getChTotal()*d.getPctCumprido();
		}
		pctCumpridoCurso = pctCumpridoCurso/this.getChTotal();
		return pctCumpridoCurso;
	}
	
	@Override
	public double getPreco() {
		double precoCurso = 0;
		for(Produto d : this.disciplinas)
			precoCurso += d.getPreco();
		for(Produto l : this.livros)
			precoCurso += l.getPreco();
		return precoCurso;
	}

	@Override
	public String toString() {
		return "Nome do curso => " + nome
				+ "\nCodigo do Curso => " + codigo 
				+ "\nCarga horaria do Curso => " + getChTotal() 
				+ "\nPercentual da carga horaria cumprida do Curso => " + getPctCumprido() * 100 + "%"
				+ "\nPreco do Curso => " + getPreco()
				+ "\n\nDisciplinas do Curso => " + disciplinas 
				+ "\n\nLivros utilizados no Curso => " + livros;
	}

	@Override
	public PrototipavelIF prototipar() {
		return new Curso(this);		
	}
	
	public Ementa getEmenta() {
		EmentaBuilder informacoes = EmentaBuilder.reset();				
		informacoes.addNomeCurso(this.nome);
		informacoes.addCodigoCurso(this.codigo);
		for(Disciplina disciplina : this.disciplinas) {
			informacoes.addDisciplina(disciplina);
		}
		for(Livro livro : this.livros) {
			informacoes.addLivro(livro);
		}
		Ementa ementa = informacoes.build();
		return ementa;
	}
}
