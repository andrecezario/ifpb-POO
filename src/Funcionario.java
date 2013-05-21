
public class Funcionario extends Usuario{
	
	private String departamento;
	
	
	public Funcionario(String nomeUsuario, String senhaUsuario,
			String departamento) {
		super(nomeUsuario, senhaUsuario);
		this.departamento = departamento;
	}

		
	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public int Prazo(){
		return 20;	
	}

}
