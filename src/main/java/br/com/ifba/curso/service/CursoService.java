/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.dao.ICursoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author inque
 */
@Service // Indica que esta classe é um componente de serviço do Spring
public class CursoService implements ICursoService {
    
    @Autowired // Injeção de dependência para o DAO de Curso
    private ICursoDao cursoDao; // Usa a interface para o DAO

    @Override
    //@Transactional(readOnly = true) // Transação de leitura, otimizada para consultas
    public List<Curso> findAll() {
        return cursoDao.findAll();
    }

    @Override
    //@Transactional // Inicia uma transação para operações de escrita
    public Curso save(Curso curso) {
        // Lógica de negócio: Verificar se o número de matrícula já existe
        if (curso.getCodCurso() != null && cursoDao.findByCodCurso(curso.getCodCurso()) != null) {
            throw new RuntimeException("Já existe um curso com este número de matrícula.");
        }
        return cursoDao.save(curso);
    }

    @Override
    //@Transactional // Inicia uma transação para operações de escrita
    public Curso update(Curso curso) {
        // Lógica de negócio: Verificar se o curso existe antes de atualizar
        if (curso.getId() == null || cursoDao.findById(curso.getId()) == null) {
            throw new RuntimeException("Curso não encontrado para atualização.");
        }
        return cursoDao.update(curso);
    }

    @Override
    //@Transactional // Inicia uma transação para operações de escrita
    public void delete(Long id) {
        // Lógica de negócio: Verificar se o curso existe antes de deletar
        if (cursoDao.findById(id) == null) {
            throw new RuntimeException("Curso não encontrado para exclusão.");
        }
        cursoDao.delete(id);
    }
    
    @Override
    //@Transactional(readOnly = true)
    public Curso findById(Long id) {
        return cursoDao.findById(id);
    }
}
