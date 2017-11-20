package br.com.casadocodigo.integracao.bookserver;

public class UsuarioSemAutorizacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
    public UsuarioSemAutorizacaoException(String mensagem) {
    	/**
    	 * Péssima prática utilizando Exception, apenas para fins de testes
    	 */
        super(mensagem);
    }
}