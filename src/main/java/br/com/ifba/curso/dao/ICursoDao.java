/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;

/**
 *
 * @author inque
 */
public interface ICursoDao extends GenericDao<Curso, Long> {
    Curso findByCodCurso(String numeroMatricula);
}
