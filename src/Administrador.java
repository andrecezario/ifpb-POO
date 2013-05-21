
public class Administrador extends Usuario{
	
	public Administrador(String nome, String senha){
		super(nome, senha);
	}
	
	public int Prazo(){
		return 30;
	}

}
