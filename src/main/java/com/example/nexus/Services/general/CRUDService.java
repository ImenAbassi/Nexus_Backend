/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nexus.Services.general;

import java.util.List;


public interface CRUDService<T extends Object> {

    T add(T entity);

    T update(T entity);

    void delete(Long id);

    T findById(Long id);

    List<T> findAll();

}
