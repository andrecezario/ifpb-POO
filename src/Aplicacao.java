import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;


public class Aplicacao {

	private Scanner teclado = new Scanner(System.in);
	Biblioteca ifpb;
	Usuario usuarioLogado;

	public Aplicacao(){
		ifpb = new Biblioteca("IFPB");

		try{

			ifpb.adicionarUsuario(new Administrador("David","1234"));
			ifpb.adicionarUsuario(new Funcionario("Maria","1111","Limpeza"));
			ifpb.adicionarUsuario(new Funcionario("Gabriel","2222","TI"));
			ifpb.adicionarUsuario(new Aluno("Jairo","3333","TSI"));
			ifpb.adicionarUsuario(new Aluno("Ana","4444","TSI"));


			Autor ellen = new Autor("Ellen G. White");
			Autor nicolau = new Autor("Nicolau Maquiavel");
			Autor joao= new Autor("João Mota");

			Livro livro1 = new Livro("O Principe");
			Livro livro2  = new Livro("A Rede Social");
			Livro livro3 = new Livro("O Grande Conflito");

			livro1.adicionarAutor(nicolau);
			livro1.adicionarAutor(ellen);
			ifpb.adicionarLivro(livro1);

			livro2.adicionarAutor(ellen);
			livro2.adicionarAutor(joao);
			ifpb.adicionarLivro(livro2);

			livro3.adicionarAutor(ellen);
			livro3.adicionarAutor(nicolau);
			ifpb.adicionarLivro(livro3);


		}catch(Exception e){
			System.out.println("--> " + e.getMessage());
		}

	}

	
	public boolean autenticaUsuario(String nome, String senha)throws Exception{
        
		if(!ifpb.getUsuarios_().containsKey(nome)) throw new Exception("Usuario Inexistente!");
        	   	
		Usuario usuario = ifpb.getUsuarios_().get(nome);

		if(usuario.getNomeUsuario().equals(nome) && usuario.getSenhaUsuario().equals(senha)){
			usuarioLogado = usuario;
			return true; 
		}

		return false;
	}

	
	public void exibirMenu1(){

		System.out.println("\n\n");
		System.out.println("\n\n| - - - - - - - - -  Menu  - - - - - - - - - - - |");
		System.out.println("|  [0]- Sair do Sistema                          |");
		System.out.println("|  [1]- Listagem de livros da biblioteca         |");
		System.out.println("|  [2]- Busca de livro por titulo                |");
		System.out.println("|  [3]- Busca de livro por autor                 |");
		System.out.println("|  [4]- Listagem de Emprestimos da biblioteca    |");
		System.out.println("|  [5]- Login                                    |");
		System.out.println("|  [11]- Listagem de Usuarios                    |");
		System.out.print("  Opção :");
	}

	public void exibirMenu2(){

		System.out.println("\n\n");
		System.out.println("\n\n| - - - - - - - - -  Menu  - - - - - - - - - - - |");
		System.out.println("|  [0]- Sair do Sistema                          |");
		System.out.println("|  [1]- Listagem de livros da biblioteca         |");
		System.out.println("|  [2]- Busca de livro por titulo                |");
		System.out.println("|  [3]- Busca de livro por autor                 |");
		System.out.println("|  [4]- Listagem de Emprestimos da biblioteca    |");
		System.out.println("|  [6]- Logoff                                   |");
		System.out.println("|  [7]- Empréstimo                               |");
		System.out.println("|  [8]- Devolução                                |");
		System.out.println("|  [9]- Listagem de Emprestimos do usuário       |");
		System.out.println("|  [10]- Exclusão de Usuarios                    |");
		System.out.println("|  [11]- Listagem de Usuarios                    |");
		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - -|");
		System.out.print("  Opção :");
	}

	public void Executar(){

		String entrada;
		int opcao;
		int menu=1;

		do {

			if(menu==1) exibirMenu1();
			else exibirMenu2();

			try{

				entrada = teclado.nextLine();
				opcao = Integer.parseInt(entrada);

				switch (opcao) {

				case 0:	
					break;
				
				
				case 1:	

					System.out.println(ifpb.listagemlivros()); 
					break;

					
					
				case 2:   //Buscar Livro por Titulo

					System.out.println("Informe o titulo: ");
					String titulo = teclado.nextLine();

					if(ifpb.buscaLivroTitulo(titulo).isEmpty()){
						System.out.println("Não Existe Livros com Esse Titulo!");
						break;	
					}
					else{
						System.out.println(ifpb.buscaLivroTitulo(titulo));
						break;
					}


				case 3:     //Buscar Livro por Autor

					System.out.println("Informe o Autor: ");
					System.out.println(ifpb.buscarLivroAutor(teclado.nextLine()));
					break;

					
					
				case 4:    //Mostrar Livros da Bilioteca

					if(ifpb.getEmprestimos().isEmpty()) System.out.println("Não Existe Emprestimos!");
					else{
						System.out.println("============================");
						System.out.println("\nEmprestimos da Biblioteca: ");
						System.out.println(ifpb.getEmprestimos());
						System.out.println("============================");
					}
					break;

					
					
				case 5:   // Login

					if(usuarioLogado!=null){
						System.out.println("Já existe Usuario Logado!");
						break;
					}

					System.out.println("Informe o Usuario: ");
					String nome = teclado.nextLine();
					System.out.println("Informe a Senha:");
					String senha = teclado.nextLine();
                try{
					
                	if(autenticaUsuario(nome, senha)){
						System.out.println("\nUsuario: "+ usuarioLogado.getClass().getSimpleName()+ " - " +nome+ " Logado!");
						menu = 2;
					}
					
					else System.out.println("Usuario Invalido!");
					break;
                
                }catch(Exception e){
					System.out.println("--> " + e.getMessage());
					break;
				}
                
                
				case 6:    //Logof

					if(usuarioLogado==null){
						System.out.println("Opção Invalida!");
						break;
					}

					usuarioLogado = null;
					System.out.println("Usuário Desconectado!");
					menu=1;
					break;

					
				case 7:  //Emprestar Livro

					if(usuarioLogado==null){
						System.out.println("Opção Invalida!");
						break;
					}


					System.out.println("Informe o livro: ");
					String t = teclado.nextLine();
					try{
						System.out.println("\n" + ifpb.emprestarLivro(t, usuarioLogado));
						System.out.println("\nLivro Emprestado com sucesso!" );
						break;

					}catch(Exception e){
						System.out.println("--> " + e.getMessage());
						break;
					}
					

				case 8:  //Devolver Livro

					if(usuarioLogado==null){
						System.out.println("Opção Invalida!");
						break;
					}


					System.out.println("Informe o Livro: ");
					String li = teclado.nextLine();

					try{
						ifpb.devolverLivro(li, usuarioLogado);
						System.out.println("\n\nMulta: " + ifpb.calcularMulta(li, usuarioLogado)+"$");
						System.out.println("Livro devolvido com Sucesso!");
						break;

					}catch(Exception e){
						System.out.println("--> " + e.getMessage());
						break;
					}

					

				case 9:   //Listar Emprestimos do Usuario

					if(usuarioLogado==null){
						System.out.println("Opção Invalida!");
						break;
					}
					if(ifpb.emprestimosUsuario(usuarioLogado).isEmpty()){
						System.out.println("\nNão Existe Emprestimos! ");
						break;
					}
					else{
						System.out.println("=======================");
						System.out.println("\nEmprestimos do Usuario: ");
						System.out.println(ifpb.emprestimosUsuario(usuarioLogado));
						System.out.println("=======================");
					}
					break;

				case 10: //Remover Usuario
					
					if(usuarioLogado==null){
						System.out.println("Opção Invalida!");
						break;
					}

					if(!(usuarioLogado instanceof Administrador)){
						System.out.println("Apenas para Usuarios Administradores");
						break;
					}

					System.out.println("Informe o Usuario a ser removido: ");
					String user = teclado.nextLine();
					
					if(usuarioLogado.getNomeUsuario().equals(user)){
						System.out.println("Remorção não Permetida!");
						break;
					}
					
					try{	
						ifpb.removerUsuario(user);
						System.out.println("Removido com Sucesso!");
						break;
					}

					catch(Exception e){
						System.out.println("--> " + e.getMessage());
						break;
					}

					
				case 11:   // Listar Usuario

					System.out.println(ifpb.listarUsuarios());
					break;


				default: System.out.println("Opção Invalida! \n");
				}

			}
			catch(NumberFormatException e)	{
				System.out.println("Apenas números! \n");
				opcao=-1;
			}
			catch(Exception e){
				System.out.println("erro:" + e);
				e.printStackTrace();
				opcao=-1;
			}		
		}while(opcao != 0);
		System.out.println("\n <-- Até Breve -->");
	}


	public static void main(String[] args) throws Exception {

		Aplicacao aplicacao = new Aplicacao();
		aplicacao.Executar();



	}



}