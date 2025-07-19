/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.controller;
import br.com.ifba.curso.entity.Curso;

import java.util.List;

/**
 *
 * @author inque
 */
public interface ICursoController {
    
    List<Curso> findAll();
    Curso save(Curso curso);
    Curso update(Curso curso);
    void delete(Long id);
    Curso findById(Long id);
    
}
