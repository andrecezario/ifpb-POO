

public class Emprestimo {
	
	private int id;
	private String dataEmprestimo;
	private String dataDevolucao;
	private Double multa=0.0;
	private Livro livroEmprestado;
	private Usuario usuario;
	private boolean  status;
	private String diaDevolvido;
	
	public Emprestimo(int id, Livro livroEmprestado, String dataEmprestimo, String dataDevolucao, Usuario usuario) {
		
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.livroEmprestado = livroEmprestado;
		this.usuario = usuario;
		this.status = true;
	
	}
	public Emprestimo(){}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDataEmprestimo() {
		return dataEmprestimo;
	}


	public String getDataDevolucao() {
		return dataDevolucao;
	}


	public Livro getLivroEmprestado() {
		return livroEmprestado;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public Double getMulta() {
		return multa;
	}


	public void setMulta(Double multa) {
		this.multa = multa;
	}
	

	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDiaDevolvido() {
		return diaDevolvido;
	}


	public void setDiaDevolvido(String diaDevolvido) {
		this.diaDevolvido = diaDevolvido;
	}


	public String mostrardiaDevolovido(){
		if(multa==0) return "";
		return "\nDia da Devolução:"+ diaDevolvido;
	}
	
	public String mostrarStatus(){
		if(status) return "Aberto!";
		return "Devolvido!";
	}
	
	
	@Override
	public String toString() {
		return "\n\nID= " + id 
				+"\nUsuario= " + usuario.getNomeUsuario()
				+ "\nLivroEmprestado= " + livroEmprestado.getTituloLivro()
				+ "\nDataEmprestimo= " + dataEmprestimo
				+ "\nData para Devolucao= " + dataDevolucao
				+  mostrardiaDevolovido()
				+ "\nMulta=" + multa
				+ "\n Status: " + mostrarStatus()
				+"\n\n";
	}
	
	
	

}
