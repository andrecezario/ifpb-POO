import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.TreeMap;


public class Biblioteca{

	private String nome;
	private TreeMap<String, Usuario> usuarios_ = new TreeMap<String, Usuario>();
	private ArrayList<Emprestimo> emprestimos_ = new ArrayList<Emprestimo>();
	private ArrayList<Livro> livros_ = new ArrayList<Livro>();
	private ArrayList<Autor> autores_ = new ArrayList<Autor>();

    
	
	
	public Biblioteca(String nome) {
		this.nome = nome;
	}

	
	public void adicionarUsuario(Usuario usuario)throws Exception{
		if(usuarios_.values().contains(usuario))
			throw new Exception("Usuario J� existente!");

		usuarios_.put(usuario.getNomeUsuario(), usuario);
	}

	
	public TreeMap<String, Usuario> getUsuarios_() {
		return usuarios_;
	}

	
	
	
    
	                /* Livros */

	
	public void adicionarLivro(Livro livro)throws Exception{

		if(livros_.contains(livro))
			throw new Exception("Erro! Livro j� Existente!");

		livros_.add(livro);

		for(int i=0; i<livro.getAutoresLivro().size();i++){

			if(!autores_.contains(livro.getAutoresLivro().get(i)))
				autores_.add(livro.getAutoresLivro().get(i));

		}

	}
	

	public String listagemlivros(){
		String encontrados ="\n\n       ---------- livros do " + nome +" ----------";
		Collections.sort(livros_);
		for(Livro livro : livros_){
			encontrados += "\n\nTitulo: " + livro.getTituloLivro()
					+ "\n"+ livro.mostrarAutoresLivros()
					+"\nQuantidade de Exemplares: " + livro.getQuantidade()
					+"\nQuantidade de Emprestimos Realizados: " + livro.getQtdEmprestimosRealizado();
		}

		return encontrados;
	}


	public String buscarLivroAutor(String nomeAutor){

		for(Autor autor: autores_){
			if(autor.getNomeAutor().contains(nomeAutor))
				return autor.mostrarLivrosAutor();
		}

		return("Desculpe! N�o existe livros desse autor!");

	}



	public String buscaLivroTitulo(String tituloLivro){

		String encontrados = "";

		for(int i= 0; i<livros_.size(); i++){

			if(livros_.get(i).getTituloLivro().contains(tituloLivro))
				encontrados += "Encontrados: \n\nTitulo: " + livros_.get(i).getTituloLivro()
				+ "\n"+livros_.get(i).mostrarAutoresLivros();
		}

		return  encontrados; 
	}



	
		         /* Emprestimos */
	
	
	public void setEmprestimo(Emprestimo emprestimos) {
		this.emprestimos_.add(emprestimos);
	}

	
	
	public String emprestarLivro(String titulolivro, Usuario usuario)throws Exception {

		SimpleDateFormat formatar= new SimpleDateFormat("dd/MM/yyyy");

		Calendar dataemprestimo = new GregorianCalendar();
		Calendar datadevolucao = new GregorianCalendar();
		datadevolucao.add(Calendar.DAY_OF_MONTH, usuario.Prazo());
        
		boolean existelivro = false;
		Emprestimo emprestimo;
		int id = emprestimos_.size()+1;
		String resultado="";


		for(Livro livro: livros_){

			if(livro.getTituloLivro().equals(titulolivro)){

				if(livro.getQuantidade() == 0)throw new Exception("N�o h� em Estoque!");

				for(int i=0;i<usuario.getEmprestimos().size();i++){
					//if(usuario.getEmprestimos().get(i).getLivroEmprestado().equals(livro) && usuario.getEmprestimos().get(i).getStatus())
					if(usuario.getEmprestimos().get(i).getLivroEmprestado().equals(livro) && usuario.getEmprestimos().get(i).getStatus().equals("Aberto!"))
						throw new Exception("Voc� Ja possui o livro solicitado!");
				}

				emprestimo = new Emprestimo(id, livro, formatar.format(dataemprestimo.getTime()), formatar.format(datadevolucao.getTime()), usuario);

				setEmprestimo(emprestimo);

				//Adicionar emprestimo a lista do usuario
				for(Usuario u: usuarios_.values()){
					if(u.equals(usuario)) u.setEmprestimo(emprestimo);
				}

				livro.setQuantidade(livro.getQuantidade()-1);
				livro.setQtdEmprestimosRealizado(livro.getQtdEmprestimosRealizado()+1);
				resultado = "Id Emprestimo: " + emprestimo.getId() +"\nData do Emprestimo: " + emprestimo.getDataEmprestimo();
				existelivro = true;
			}			

		}

		if(!existelivro)throw new Exception("N�o Existe o Livro informado!");
        //System.out.println("Prazo" + usuario.Prazo());
		return resultado;
	}


	
	
	public void devolverLivro(String titulolivro, Usuario usuario)throws Exception{

		boolean existelivro = false;

		if(usuario.getEmprestimos().isEmpty()) throw new Exception("Voc� n�o possui Emprestimos");

		//Verificar se o usuario possui o livro informado
		for(int i=0;i<usuario.getEmprestimos().size();i++){
			if(usuario.getEmprestimos().get(i).getLivroEmprestado().getTituloLivro().equals(titulolivro))
				existelivro = true;
		}

		if(!existelivro) throw new Exception("Voc� N�o possui o livro informado!");


		for(Emprestimo emprestimo: emprestimos_){



			if(emprestimo.getLivroEmprestado().getTituloLivro().equals(titulolivro)){
				//Verificar se o Livro ja foi devolvido 
				
				//if(!emprestimo.getStatus()) throw new Exception("O livro ja foi devolvido!");				

				if(emprestimo.getStatus().equals("Devolvido!")) throw new Exception("O livro ja foi devolvido!");				
				//Alterar a quantidade do livro
				
				else { //
					for(Livro livro : livros_){
				
						if(livro.getTituloLivro().equals(emprestimo.getLivroEmprestado().getTituloLivro()))
							livro.setQuantidade(livro.getQuantidade()+1);
					}
					
					/*
					 * Mudaremos o status conforme implementamos com o padrao de projeto state
					 */
	
					//Mudar Status do livro na lista do usuario
					for(int i=0; i< usuario.getEmprestimos().size(); i++){
						if(usuario.getEmprestimos().get(i).equals(emprestimo))
							//usuario.getEmprestimos().get(i).setStatus(false);
							usuario.getEmprestimos().get(i).devolvido(usuario.getEmprestimos().get(i));
					}
				}//
				emprestimo.devolvido(emprestimo);
			}

		}

	}

	
	
	public void removerEmprestimo(Usuario usuario){
		/* Remove da lista de emprestimos da biblioteca os emprestimo do usuario */
		ArrayList<Emprestimo> aux = new ArrayList<Emprestimo>();

		for(Emprestimo emprestimo:emprestimos_){
			if(emprestimo.getUsuario().equals(usuario))
				aux.add(emprestimo);
		}

		
		for(Emprestimo e : aux){
			emprestimos_.remove(e);
		}

		
	}
	
	
	
	public String getEmprestimos(){

		String lista = "";
		for(Emprestimo emprestimo: emprestimos_){
			lista += "\n-------------------------"
					+ "\nID Emprestimo: " + emprestimo.getId()
					+ "\nLivro: " + emprestimo.getLivroEmprestado().getTituloLivro()
					+ "\nNome do Usuario: " + emprestimo.getUsuario().getNomeUsuario()
					+ "\nData do Emprestimo: " + emprestimo.getDataEmprestimo()
					+ "\nData para Devolu��o: " + emprestimo.getDataDevolucao()
					+  emprestimo.mostrardiaDevolovido()
					+ "\nMulta: " + emprestimo.getMulta()
					//+ "\nStatus: " + emprestimo.mostrarStatus();
					+ "\nStatus: " + emprestimo.getStatus();
		}

		return lista;
	}
	
	
	
	                               /* Multas */
	
	public double calcularMulta(String titulolivro, Usuario usuario)throws Exception{

		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataatual = new GregorianCalendar();
		Calendar devolucao = new GregorianCalendar();

		double multa=0.0;

		for(Emprestimo emprestimo: emprestimos_){

			if(emprestimo.getLivroEmprestado().getTituloLivro().equals(titulolivro)){

				//dataatual.setTime(formatar.parse("13/05/2013"));
				devolucao.setTime(formatar.parse(emprestimo.getDataDevolucao()));

				long m = dataatual.getTimeInMillis() - devolucao.getTimeInMillis();

				multa = m/(24*60*60*1000);

				if(multa<0)multa=0.0;

				emprestimo.setDiaDevolvido(formatar.format(dataatual.getTime()));
				emprestimo.setMulta(multa);


				return multa;
			}

		}
		return multa;
	}

	
	
	
	                                  /* Usuario */
	
	public String emprestimosUsuario(Usuario usuario){

		return usuario.getEmprestimosUsuario();

	}



	public String listarUsuarios(){

		String us = "\n\n--------Usuarios do Sistema------ ";

		for(Usuario usuario: usuarios_.values()){
			//if(!(usuario instanceof Administrador))
				us += "\n" + usuario.getNomeUsuario() +" - " + usuario.getClass().getSimpleName(); 
		}

		return us;
	}

	
	
	public void removerUsuario(String nome)throws Exception{

		if(! usuarios_.containsKey(nome))
			throw new Exception("Usuario n�o Existente!");

		for(Emprestimo emprestimo : usuarios_.get(nome).getEmprestimos()){
			//if(emprestimo.getStatus())
			if(emprestimo.getStatus().equals("Aberto!"))
				throw new Exception("N�o foi possivel Deletar Usuario! Verifique os Emprestimos em Aberto!");
		}

		removerEmprestimo(usuarios_.get(nome));	   	
		usuarios_.remove(nome);
	}


}
