
public class Aluno extends Usuario {

	private String curso;

	public Aluno(String nomeUsuario, String senhaUsuario, String curso) {
		super(nomeUsuario, senhaUsuario);
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	public int Prazo(){
		return 10;
	}
	
}
