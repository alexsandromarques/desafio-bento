package br.com.bento.projeto.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerController {

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<String> funcionarioNaoEncontradoException(FuncionarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(CEPInvalidoException.class)
    public ResponseEntity<String> cepInvalidoException(CEPInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(ArquivoObrigatorioException.class)
    public ResponseEntity<String> cepInvalidoException(ArquivoObrigatorioException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
