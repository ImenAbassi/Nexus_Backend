package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.TypeContrat;
import com.example.nexus.Services.TypeContratService;

@RestController
@RequestMapping("/type-contrats")
public class TypeContratController {

    @Autowired
    private TypeContratService typeContratService;

    @PostMapping
    public TypeContrat createTypeContrat(@RequestBody TypeContrat typeContrat) {
        return typeContratService.createTypeContrat(typeContrat);
    }

    @GetMapping
    public List<TypeContrat> getAllTypeContrats() {
        return typeContratService.getAllTypeContrats();
    }

    @GetMapping("/{id}")
    public TypeContrat getTypeContratById(@PathVariable Long id) {
        return typeContratService.getTypeContratById(id);
    }

    @PutMapping("/{id}")
    public TypeContrat updateTypeContrat(@PathVariable Long id, @RequestBody TypeContrat typeContrat) {
        return typeContratService.updateTypeContrat(id, typeContrat);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeContrat(@PathVariable Long id) {
        typeContratService.deleteTypeContrat(id);
    }
}
