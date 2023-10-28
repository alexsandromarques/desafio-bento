package br.com.bento.projeto.exception;

public class ArquivoObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArquivoObrigatorioException() {
        super("Por favor, selecione um arquivo CSV para upload.");
    }
}