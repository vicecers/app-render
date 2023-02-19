package com.cerso.alumno.controller;

import com.cerso.alumno.entity.Alumno;
import com.cerso.alumno.service.AlumnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlumnoController {
    
    @Autowired
    AlumnoService alumnoService;
    
    @GetMapping("/alumnos")
    public List<Alumno> index(){
        return alumnoService.getAlumnos();
    }
    
    
    @GetMapping("/alumnos/{id}")
    public Alumno show(@PathVariable Long id){
        return alumnoService.findAlumnoById(id);
    }
    
    @PostMapping("/save")
    public Alumno create(@RequestBody Alumno alumno){
        return alumnoService.saveAlumno(alumno);
    }
    
    
    @PutMapping("/update/{id}")
    public Alumno update(@PathVariable Long id, @RequestBody Alumno alumno){
        return alumnoService.updateAlumno(id, alumno);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        alumnoService.deleteAlumno(id);
    }
	
}
