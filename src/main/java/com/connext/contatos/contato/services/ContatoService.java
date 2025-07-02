package com.connext.contatos.contato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connext.contatos.contato.entities.Contato;
import com.connext.contatos.contato.repositories.ContatoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {
     @Autowired
    private ContatoRepository repository;

    public List<Contato> getAll(){
        return repository.findAll();
    }   
    public Contato save(Contato contato){
        validarDuplicidade(contato);
        return repository.save(contato);
    }
    public void update(Contato contato, long id){
        Contato aux = repository.getReferenceById(id);
        validarDuplicidade(contato);
        aux.setNome(contato.getNome());
        aux.setApelido(contato.getApelido());
        aux.setTelefone(contato.getTelefone());
        aux.setEmail(contato.getEmail());
        aux.setEndereco(contato.getEndereco());
        aux.setAniversario(contato.getAniversario());
        aux.setCategoria(contato.getCategoria());
        aux.setGenero(contato.getGenero());
        aux.setProfissao(contato.getProfissao());
        aux.setNacionalidade(contato.getNacionalidade());
        aux.setFavorito(contato.getFavorito());
        repository.save(aux);
    }

     private void validarDuplicidade(Contato contato) {

        Optional<Contato> contatoPorEmail = repository.findByEmail(contato.getEmail());
        if (contatoPorEmail.isPresent() && !contatoPorEmail.get().getId().equals(contato.getId())) {
            throw new RuntimeException("Este email já está cadastrado em outro contato.");
        }

        // Validação de duplicidade de Telefone
        Optional<Contato> contatoPorTelefone = repository.findByTelefone(contato.getTelefone());
        if (contatoPorTelefone.isPresent() && !contatoPorTelefone.get().getId().equals(contato.getId())) {
            throw new RuntimeException("Este telefone já está cadastrado em outro contato.");
        }
    }
    public void delete(long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Contato não cadastrado");
        }
    }
    
}
