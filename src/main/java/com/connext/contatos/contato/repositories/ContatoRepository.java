package com.connext.contatos.contato.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connext.contatos.contato.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
    Optional<Contato> findByEmail(String email);
    Optional<Contato> findByTelefone(String telefone);
}
