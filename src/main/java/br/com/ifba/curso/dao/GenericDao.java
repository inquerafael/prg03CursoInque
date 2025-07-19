/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
import java.util.List;
//import java.util.Optional;

/**
 *
 * @author inque
 */
//@Repository // Marca esta classe como um componente da camada de persistência
//@Transactional
public interface GenericDao<T, ID extends Serializable> {
    
    T save(T entity);
    T update(T entity);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
    
}
