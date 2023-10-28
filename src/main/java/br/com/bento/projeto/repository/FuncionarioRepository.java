package br.com.bento.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bento.projeto.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);;

}
