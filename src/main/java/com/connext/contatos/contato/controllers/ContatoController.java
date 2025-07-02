package com.connext.contatos.contato.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connext.contatos.contato.entities.Contato;
import com.connext.contatos.contato.services.ContatoService;

@CrossOrigin
@RestController
@RequestMapping("contatos")
public class ContatoController {
     @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato){
        return ResponseEntity.created(null).body(service.save(contato));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Contato contato){
        service.update(contato, id);
        return ResponseEntity.noContent().build();
    }
}

    

