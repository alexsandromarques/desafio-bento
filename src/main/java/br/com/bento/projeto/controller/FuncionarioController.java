package br.com.bento.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bento.projeto.entity.Funcionario;
import br.com.bento.projeto.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping(value = "/funcionario")
@Tag(name = "Controle de Funcionários")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping(value = "/upload", consumes = { "multipart/form-data" })
	@Operation(summary = "Upload dos dados dos funcionários", responses = { @ApiResponse(responseCode = "200") })
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
		funcionarioService.upload(file);
		return ResponseEntity.ok("Upload do arquivo CSV bem-sucedido!");
	}

	@GetMapping("/todos")
	@Operation(summary = "Pesquisar todos os funcionários", responses = { @ApiResponse(responseCode = "200") })
	public List<Funcionario> buscarTodosFuncionarios() {
		return funcionarioService.buscarTodosFuncionarios();
	}

	@GetMapping("/cpf/{cpf}")
	@Operation(summary = "Pesquisar funcionário por CPF", responses = { @ApiResponse(responseCode = "200") })
	public ResponseEntity<Funcionario> buscarFuncionarioPorCpf(@PathVariable String cpf) {
		Funcionario funcionario = funcionarioService.buscarFuncionarioPorCpf(cpf);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
