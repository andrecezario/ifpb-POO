
public class Devolvido implements EmprestimoState{

	@Override
	public EmprestimoState emprestimoEmAberto(Emprestimo e) {
		//System.out.println("Opera��o n�o permitida!");
		//return null;
		e.setStatus("Aberto!");
		return new Aberto();
	}

	@Override
	public EmprestimoState emprestimoDevolvido(Emprestimo e) {
		e.setStatus("Devolvido!");
		return new Devolvido();
	}
}
