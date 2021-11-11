package Model;

import java.lang.reflect.InvocationTargetException;

public class Aplicacao {
	
	public static TipoProduto PRODUTO = TipoProduto.DISCIPLINA;
	private ProdutoFactory factory;	
	
	public Aplicacao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {		
		this.factory = (ProdutoFactory) (Class.forName(Aplicacao.PRODUTO.getFactoryName()).getDeclaredConstructor().newInstance());		
	}
	
	//Factory Method
	public void Q1() throws InterruptedException, InstantiationException{
		//Criando livros ou disciplinas
		Produto produtoA = factory.getProduto("Factory Method", "codprod1");
		Produto produtoB = factory.getProduto("Builder", "codprod2");
		Produto produtoC = factory.getProduto("Singleton", "codprod3");
		Produto produtoD = factory.getProduto("Prototype", "codprod4");
		
		System.out.println(produtoA.toString());
		System.out.println(produtoB.toString());
		System.out.println(produtoC.toString());
		System.out.println(produtoD.toString());

	}
	
	//Builder
	public void Q2() throws InterruptedException, InstantiationException{
		//Criando cursos
		Disciplina disciplinaA = (Disciplina)factory.getProduto("Factory Method", "DISFAC");
		Disciplina disciplinaB = (Disciplina)factory.getProduto("Builder", "DISCBUI");
		Disciplina disciplinaC = (Disciplina)factory.getProduto("Singleton", "DISCSIN");
		Disciplina disciplinaD = (Disciplina)factory.getProduto("Prototype", "DISCPRO");
		
		disciplinaA.setPctCumprido(0.2);
		disciplinaB.setPctCumprido(0.3);
		disciplinaC.setPctCumprido(0.4);
		disciplinaD.setPctCumprido(0.5);
		
		Curso curso = CursoBuilder.reset()
				.addNomeCurso("Criacionais")
				.addCodigoCurso("CRI001")
				.addDisciplina(disciplinaA)
				.addDisciplina(disciplinaB)
				.addDisciplina(disciplinaC)
				.addDisciplina(disciplinaD)
				.addLivro(new Livro("Factory Method", "LIVFAC", 80, "ISBNFAC"))
				.addLivro(new Livro("Builder", "LIVBUI", 80, "ISBNBUI"))
				.addLivro(new Livro("Prototype", "LIVPRO", 80, "ISBPRO"))
				.build();

		System.out.println(" ");		
		Ementa ementa = curso.getEmenta();
		ementa.mostrar();
	}	
		
	//Prototype e Singleton
	public void Q3() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
					
		GerentePrototipos catalogo = GerentePrototipos.getInstance();
		
		ProdutoFactory factory = (ProdutoFactory) (Class.forName(Aplicacao.PRODUTO.getFactoryName()).getDeclaredConstructor().newInstance());
		
		//Criacionais
		Disciplina disciplinaA = (Disciplina)factory.getProduto("Factory Method", "DISFAC");
		Disciplina disciplinaB = (Disciplina)factory.getProduto("Builder", "DISCBUI");
		Disciplina disciplinaC = (Disciplina)factory.getProduto("Singleton", "DISCSIN");
		Disciplina disciplinaD = (Disciplina)factory.getProduto("Prototype", "DISCPRO");
		
		Curso cursoCriacionais = CursoBuilder.reset()
				.addNomeCurso("Criacionais")
				.addCodigoCurso("CRI001")
				.addDisciplina(disciplinaA)
				.addDisciplina(disciplinaB)
				.addDisciplina(disciplinaC)
				.addDisciplina(disciplinaD)
				.addLivro(new Livro("LIVFAC", "Factory Method", 20, "ISBNFAC"))
				.addLivro(new Livro("LIVBUI", "Builder", 10, "ISBNBUI"))
				.addLivro(new Livro("LIVPRO", "Prototype", 5, "ISBPRO"))
				.build();	
		catalogo.catalogar(cursoCriacionais.getNome(), cursoCriacionais);
				
		//Comportamentais
		Disciplina disciplinaE = (Disciplina)factory.getProduto("Strategy", "DISCSTR");
		Disciplina disciplinaF = (Disciplina)factory.getProduto("Visitor", "DISCVIS");
		Disciplina disciplinaG = (Disciplina)factory.getProduto("State", "DISCSTA");
		
		Curso cursoComportamentais = CursoBuilder.reset()
				.addNomeCurso("Comportamentais")
				.addCodigoCurso("COM001")
				.addDisciplina(disciplinaE)
				.addDisciplina(disciplinaF)
				.addDisciplina(disciplinaG)				
				.addLivro(new Livro("LIVSTR", "Strategy", 20, "ISBNSTR"))
				.addLivro(new Livro("LIVVIS", "Visitor", 20, "ISBNVIS"))
				.addLivro(new Livro("LIVSTA", "State", 20, "ISBNSTA"))
				.build();	
		catalogo.catalogar(cursoComportamentais.getNome(), cursoComportamentais);
				
		//Estruturais
		Disciplina disciplinaH = (Disciplina)factory.getProduto("Bridge", "DISCBRI");
		Disciplina disciplinaI = (Disciplina)factory.getProduto("Composite", "DISCCOM");
		Disciplina disciplinaJ = (Disciplina)factory.getProduto("Proxy", "DISCPRO");
		
		Curso cursoEstruturais = CursoBuilder.reset()
				.addNomeCurso("Estruturais")
				.addCodigoCurso("EST001")
				.addDisciplina(disciplinaH)
				.addDisciplina(disciplinaI)
				.addDisciplina(disciplinaJ)				
				.addLivro(new Livro("LIVBRI", "Bridge", 20, "ISBNBRI"))
				.addLivro(new Livro("LIVCOM", "Composite", 20, "ISBNCOM"))
				.addLivro(new Livro("LIVPRO", "Proxy", 20, "ISBNPRO"))
				.build();	
		catalogo.catalogar(cursoEstruturais.getNome(), cursoEstruturais);		
		
		//Clonando um curso
		Curso curso = catalogo.getCurso("Criacionais");		
		System.out.println(curso.toString());
		
		//Alterando atributos do clone
		System.out.println(" ");
		curso = CursoBuilder.reset()
				.addNomeCurso("Teste")
				.addCodigoCurso("TEST001")
				.addDisciplina(disciplinaH)	
				.addDisciplina(new Disciplina("TESTEDISC", "CODIGODISC", 0, 30, 0.5))	
				.addLivro(new Livro("LIVBRI", "Bridge", 20, "ISBNBRI"))
				.build();	
		System.out.println(curso.toString());
		
		//Sobrescrevendo (Clonando novamente)
		System.out.println(" ");
		curso = catalogo.getCurso("Criacionais");		
		System.out.println(curso.toString());
	}
	
	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Aplicacao app = new Aplicacao();
		app.Q2();
	}
}
