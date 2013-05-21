import java.util.ArrayList;


public class Autor {
	
	private String nomeAutor;
    private ArrayList<Livro> livrosAutor = new ArrayList<Livro> ();
	
	public Autor(String nomeAutor) {
		this.nomeAutor = nomeAutor;	
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	
	public void adicionarLivrosAutor(Livro livro_){
		 livrosAutor.add(livro_);
	}

	public ArrayList<Livro> getLivrosAutor() {
		return livrosAutor;
	}

	
	public String mostrarLivrosAutor(){
		 
		String livros_ = "- Livros -\n";
	   
		 for (Livro livro : livrosAutor){
			 livros_ += "\nTitulo: " + livro.getTituloLivro()+"\n"
					 +livro.mostrarAutoresLivros() + "\n";
		  }
				
		
	  return livros_;
	}
	
	
	@Override
	public String toString() {
		return "NomeAutor= " + nomeAutor 
				+ "\nlivrosAutor= " + livrosAutor;
		
		
			
		
	}
    
	
	
	

}
