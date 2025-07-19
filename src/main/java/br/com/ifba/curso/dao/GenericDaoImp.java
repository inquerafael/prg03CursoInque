/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author inque
 */
public abstract class GenericDaoImp<T, ID extends Serializable> implements GenericDao<T, ID> {
    
    @PersistenceContext // Injeta o EntityManager do JPA
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImp() {
        // Obtém o tipo da entidade em tempo de execução
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity); // Persiste a entidade no banco de dados
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity); // Atualiza a entidade no banco de dados
    }

    @Override
    public void delete(ID id) {
        T entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity); // Remove a entidade do banco de dados
        }
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id); // Busca a entidade pelo ID
    }

    @Override
    public List<T> findAll() {
        // Cria e executa uma query para buscar todas as entidades do tipo
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }
}
