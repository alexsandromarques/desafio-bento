package br.com.bento.projeto.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradoException() {
        super("Funcionário não cadastrado");
    }
}