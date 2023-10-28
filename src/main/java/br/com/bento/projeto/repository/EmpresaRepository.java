package br.com.bento.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bento.projeto.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByCnpj(String cnpj);

}
