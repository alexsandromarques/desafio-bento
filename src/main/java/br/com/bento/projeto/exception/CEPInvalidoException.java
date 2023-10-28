package br.com.bento.projeto.exception;

public class CEPInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CEPInvalidoException() {
        super("Existem um ou mais registros com o CEP inválido");
    }
}