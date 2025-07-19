/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.service.ICursoService;
import br.com.ifba.curso.entity.Curso;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author inque
 */
@RestController // Combina @Controller e @ResponseBody
@RequestMapping("/api/cursos")
public class CursoController implements ICursoController{
    
    @Autowired // Injeção de dependência para o serviço de Curso
    private ICursoService cursoService; // Usa a interface para o serviço

    @Override
    @GetMapping // Mapeia requisições GET para /api/cursos
    public List<Curso> findAll() {
        return cursoService.findAll();
    }
    
    @Override
    @PostMapping // Mapeia requisições POST para /api/cursos
    public Curso save(@RequestBody Curso curso) { // @RequestBody para desserializar JSON para objeto Curso
        return cursoService.save(curso);
    }

    //@Override
    @PutMapping("/{id}") // Mapeia requisições PUT para /api/cursos/{id}
    public Curso update(@PathVariable Long id, @RequestBody Curso curso) {
        // Assume que o ID do curso a ser atualizado vem do path e do corpo da requisição
        curso.setId(id); // Garante que o ID do objeto corresponde ao ID do path
        return cursoService.update(curso);
    }
    
    @Override
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/cursos/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 No Content para deleção bem-sucedida
    public void delete(@PathVariable Long id) {
        cursoService.delete(id);
    }
    
    @Override
    @GetMapping("/{id}") // Mapeia requisições GET para /api/cursos/{id}
    public Curso findById(@PathVariable Long id) {
        return cursoService.findById(id);
    }
}
