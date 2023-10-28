package br.com.bento.projeto.exception;

public class DadosIncompletoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadosIncompletoException() {
        super("Os dados no arquivo CSV estão incompletos.");

    }
}