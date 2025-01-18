/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nexus.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ChedliJenhani
 */
public class ObjectMapper {
    private static ModelMapper modelMapper = new ModelMapper();
    
    static{
         modelMapper = new ModelMapper();
    }

    public ObjectMapper() {
    }

    	public static <D, T> D map(final T entity, Class<D> outClass) {
	        if(entity == null){
	           return null;
	        }
		return modelMapper.map(entity, outClass);
	}

	
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

	
	public static <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	}
}
