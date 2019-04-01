
public class Aberto implements EmprestimoState{

	@Override
	public EmprestimoState emprestimoEmAberto(Emprestimo e) {
		e.setStatus("Aberto!");
		return new Aberto();
	}

	@Override
	public EmprestimoState emprestimoDevolvido(Emprestimo e) {
		e.setStatus("Devolvido!");
		return new Devolvido();
	}

}
