import java.util.ArrayList;


public class Livro implements Comparable<Livro> {

	private String tituloLivro;
	private int quantidade;
	private ArrayList<Autor> autoresLivro = new ArrayList<Autor> ();
	private int qtdEmprestimosRealizado = 0;

	public Livro(String tituloLivro) {
		super();
		this.tituloLivro = tituloLivro;
		this.quantidade = 10;	
	}

	public String getTituloLivro() {
		return tituloLivro;
	}


	public int getQuantidade() {
		return quantidade;
	}
	

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public ArrayList<Autor> getAutoresLivro() {
		return autoresLivro;
	}
	
	public int getQtdEmprestimosRealizado() {
		return qtdEmprestimosRealizado;
	}

	public void setQtdEmprestimosRealizado(int qtdEmprestimosRealizado) {
		this.qtdEmprestimosRealizado = qtdEmprestimosRealizado;
	}

	public void adicionarAutor(Autor autor_) throws Exception{
		
		if(autoresLivro.contains(autor_))
			throw new Exception("Autor já Existente!");
		
		if(autor_.getLivrosAutor().contains(this))
			throw new Exception("Autor já Possui este Livro!");
		
		else{
			autoresLivro.add(autor_);
			autor_.adicionarLivrosAutor(this);
		}

	}
   
	public String mostrarAutoresLivros(){
		
		String autores_ = "Autores: ";    
	    
		for(Autor autor : autoresLivro)
	    	autores_ +="- " + autor.getNomeAutor() + " ";
	 
	return autores_;	 
	
	}


	@Override
	public String toString() {
		
		return "\n\nTitulo= " + tituloLivro
	           + "\nQuantidade= " + quantidade
	           + "\nAutores= |" + mostrarAutoresLivros();
	}
	
	
	
	public int compareTo(Livro livro) {
		return (this.getTituloLivro().compareTo(livro.getTituloLivro()));
		}




}
