/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.service;
import java.util.List;
import br.com.ifba.curso.entity.Curso;
/**
 *
 * @author inque
 */
public interface ICursoService {
    List<Curso> findAll();
    Curso save(Curso curso);
    Curso update(Curso curso);
    void delete(Long id);
    Curso findById(Long id);
}
