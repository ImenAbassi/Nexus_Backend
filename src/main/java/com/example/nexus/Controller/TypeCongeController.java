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

import com.example.nexus.Entitie.TypeConge;
import com.example.nexus.Services.TypeCongeService;

@RestController
@RequestMapping("/type-conges")
public class TypeCongeController {

    @Autowired
    private TypeCongeService typeCongeService;

    @PostMapping
    public TypeConge createTypeConge(@RequestBody TypeConge typeConge) {
        return typeCongeService.createTypeConge(typeConge);
    }

    @GetMapping
    public List<TypeConge> getAllTypeConges() {
        return typeCongeService.getAllTypeConges();
    }

    @GetMapping("/{id}")
    public TypeConge getTypeCongeById(@PathVariable Long id) {
        return typeCongeService.getTypeCongeById(id);
    }

    @PutMapping("/{id}")
    public TypeConge updateTypeConge(@PathVariable Long id, @RequestBody TypeConge typeConge) {
        return typeCongeService.updateTypeConge(id, typeConge);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeConge(@PathVariable Long id) {
        typeCongeService.deleteTypeConge(id);
    }
}
