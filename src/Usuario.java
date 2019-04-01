import java.util.ArrayList;


public abstract class Usuario {

	private String nomeUsuario;
	private String senhaUsuario;
	private ArrayList<Emprestimo> emprestimos= new ArrayList<Emprestimo>();


	public Usuario(String nomeUsuario, String senhaUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}


	public String getSenhaUsuario() {
		return senhaUsuario;
	}


	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public void setEmprestimo(Emprestimo emprestimo_){
		emprestimos.add(emprestimo_);

	}

	public void removeEmprestimo(Emprestimo emprestimo){
		emprestimos.remove(emprestimo);
	}

	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public String getEmprestimosUsuario(){

		String lista = "";
		for(Emprestimo emprestimo: emprestimos){

			lista +="\n-------------------------"
					+"\nID Emprestimo: " + emprestimo.getId()
					+ "\nLivro :" + emprestimo.getLivroEmprestado().getTituloLivro()
					+ "\nNome do Usuari: " + emprestimo.getUsuario().getNomeUsuario()
					+ "\nData Emprestimo: " + emprestimo.getDataEmprestimo()
					+ "\nData para Devolução: " + emprestimo.getDataDevolucao()
					+  emprestimo.mostrardiaDevolovido()
					+ "\nMulta: " + emprestimo.getMulta()
					//+ "\nStatus: " + emprestimo.mostrarStatus();	
					+ "\nStatus: " + emprestimo.getStatus();	
		}

		return lista;
	}

	public abstract int Prazo();


}
