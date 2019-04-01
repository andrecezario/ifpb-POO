
public class Emprestimo {
	
	private int id;
	private String dataEmprestimo;
	private String dataDevolucao;
	private Double multa=0.0;
	private Livro livroEmprestado;
	private Usuario usuario;
	//private boolean  status;
	private String status;
	//Insercao do atributo estado
	private EmprestimoState estado;
	private String diaDevolvido;
	
	public Emprestimo(int id, Livro livroEmprestado, String dataEmprestimo, String dataDevolucao, Usuario usuario) {
		
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.livroEmprestado = livroEmprestado;
		this.usuario = usuario;
		//this.status = true;
		this.status = "Aberto!";
		this.estado = new Aberto();	
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
	
	/*
	public boolean getStatus() {
		return status;
	}


	public boolean setStatus(boolean status) {
		this.status = status;
	}*/
	
	//Get e Set para status de acordo com o novo tipo adotado (O tipo String nos 
	//da uma mobilidade maior caso usemos mais de um status de emprestimo)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
		return "\nDia da Devolu��o:"+ diaDevolvido;
	}
	
	
	//MUNDANCAS DE ESTADO COM O STATE, LOGO, SE QUISER MOSTRARMOS ALGO, USO O METODO "getStatus()"
	/*public String mostrarStatus(){
		if(status) return "Aberto!";
		return "Devolvido!";
	}*/
	
	public void aberto(Emprestimo e) {
		estado = estado.emprestimoEmAberto(e);
	}
	public void devolvido(Emprestimo e){
		estado = estado.emprestimoDevolvido(e);
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
				//+ "\n Status: " + mostrarStatus()
				+ "\n Status: " + getStatus()
				+"\n\n";
	}
	
	
	

}
