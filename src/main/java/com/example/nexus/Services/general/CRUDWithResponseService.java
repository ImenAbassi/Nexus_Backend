/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nexus.Services.general;

import java.util.List;
import org.springframework.http.ResponseEntity;


public interface CRUDWithResponseService<T extends Object> {

    ResponseEntity<T> add(T entity);

    ResponseEntity<T> update(T entity);

    ResponseEntity delete(Long id);

    T findById(Long id);

    List<T> findAll();

}
