package br.com.bento.projeto.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.bento.projeto.dto.EmpresaDTO;
import br.com.bento.projeto.dto.EnderecoDTO;
import br.com.bento.projeto.entity.Empresa;
import br.com.bento.projeto.entity.Endereco;
import br.com.bento.projeto.entity.Funcionario;
import br.com.bento.projeto.exception.ArquivoObrigatorioException;
import br.com.bento.projeto.exception.CEPInvalidoException;
import br.com.bento.projeto.exception.FuncionarioNaoEncontradoException;
import br.com.bento.projeto.repository.EmpresaRepository;
import br.com.bento.projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	private static final String CEP_API_URL = "https://viacep.com.br/ws/";

	private static final String CNPJ_API_URL = "https://brasilapi.com.br/api/cnpj/v1/";

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private RestTemplate restTemplate;

	public void upload(MultipartFile file) {

		if (file.isEmpty()) {
			throw new ArquivoObrigatorioException();
		}

		try {

			InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String linha;
			bufferedReader.readLine();

			while ((linha = bufferedReader.readLine()) != null) {

				String[] dados = linha.split(";");

				String cpf = dados[0];
				String nome = dados[1];
				Endereco endereco = consultarEnderecoPorCep(dados[2]);
				Empresa empresaExiste = empresaRepository.findByCnpj(dados[3]);

				Funcionario funcionario;
				if (empresaExiste != null) {
					funcionario = new Funcionario(cpf, nome, endereco, empresaExiste);
				} else {
					Empresa empresaNova = consultarEmpresaPorCNPJ(dados[3]);
					funcionario = new Funcionario(cpf, nome, endereco, empresaNova);
				}

				salvar(funcionario);
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao processar o arquivo CSV: " + e.getMessage());
		}
	}

	private void salvar(Funcionario funcionario) {

		Funcionario funcionarioExiste = funcionarioRepository.findByCpf(funcionario.getCpf());

		if (funcionarioExiste != null) {
			funcionarioExiste.setNome(funcionario.getNome());
			Endereco endereco = new Endereco(funcionarioExiste.getEndereco().getId(),
					funcionario.getEndereco().getCep(), funcionario.getEndereco().getLogradouro(),
					funcionario.getEndereco().getComplemento(), funcionario.getEndereco().getBairro(),
					funcionario.getEndereco().getLocalidade(), funcionario.getEndereco().getUf());
			funcionarioExiste.setEndereco(endereco);
			funcionarioRepository.save(funcionarioExiste);
		} else {
			funcionarioRepository.save(funcionario);
		}
	}

	private Endereco consultarEnderecoPorCep(String cep) {

		if (!isCepValido(cep)) {
			throw new CEPInvalidoException();
		}

		String apiUrl = CEP_API_URL + cep + "/json";
		EnderecoDTO enderecoDTO = restTemplate.getForObject(apiUrl, EnderecoDTO.class);

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(enderecoDTO, Endereco.class);
	}

	private Empresa consultarEmpresaPorCNPJ(String cnpj) {

		String apiUrl = CNPJ_API_URL + cnpj;
		EmpresaDTO empresaDTO = restTemplate.getForObject(apiUrl, EmpresaDTO.class);

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(empresaDTO, Empresa.class);
	}

	private boolean isCepValido(String cep) {
		cep = cep.replaceAll("[^0-9]", "");
		return cep.length() == 8;
	}

	public List<Funcionario> buscarTodosFuncionarios() {
		return funcionarioRepository.findAll();
	}

	public Funcionario buscarFuncionarioPorCpf(String cpf) {

		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		if (funcionario == null) {
			throw new FuncionarioNaoEncontradoException();
		}
		return funcionario;
	}

}
