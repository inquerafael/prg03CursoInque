/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;

import org.springframework.stereotype.Repository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


/**
 *
 * @author inque
 */
@Repository 
public class CursoDao extends GenericDaoImp<Curso, Long> implements ICursoDao{
    
    @Override
    public Curso findByCodCurso(String numeroMatricula) {
        try {
            // Cria e executa uma query para buscar um curso pelo número de matrícula
            TypedQuery<Curso> query = entityManager.createQuery(
                "SELECT c FROM Curso c WHERE c.numeroMatricula = :numeroMatricula", Curso.class);
            query.setParameter("numeroMatricula", numeroMatricula);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se nenhum curso for encontrado
        }
    }
}
