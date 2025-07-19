/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional; // Para gerenciamento de transações

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author inque
 */
@Repository // Marca esta classe como um componente da camada de persistência
@Transactional
public interface GenericDao<T, ID extends Serializable> implements GenericDao<T, ID> {
    
    @PersistenceContext // Injeta o EntityManager, que é a interface JPA para interagir com o contexto de persistência
    protected EntityManager entityManager;

    private final Class<T> entityClass; // Classe da entidade (ex: Curso.class)

    @SuppressWarnings("unchecked") // Ignora o aviso de unchecked cast
    public GenericDaoImpl() {
        // Construtor para obter o tipo da entidade genérica (T) em tempo de execução
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        if (entityManager.contains(entity)) { // Verifica se a entidade já está no contexto de persistência (para update)
            return entityManager.merge(entity); // Atualiza
        } else {
            entityManager.persist(entity); // Salva nova
            return entity;
        }
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete); // Encontra e deleta se existir
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        return entityManager.createQuery(all).getResultList();
    }

    @Override
    public long count() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(entityClass)));
        return entityManager.createQuery(cq).getSingleResult();
    }
    
}
