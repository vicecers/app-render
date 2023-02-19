
package com.cerso.alumno.service;

import com.cerso.alumno.entity.Alumno;
import com.cerso.alumno.repository.AlumnoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cerso
 */
@Service
public class AlumnoServiceImpl implements AlumnoService{
    
    @Autowired
    AlumnoRepository alumnorepository;

    @Override
    public List<Alumno> getAlumnos() {    
        return alumnorepository.findAll();
    }

    @Override
    public Alumno findAlumnoById(Long id) {
        return alumnorepository.findById(id).orElse(null);
    }

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        return alumnorepository.save(alumno);
    }
    
    
     @Override
    public Alumno updateAlumno(Long id, Alumno alumno) {
  
        Alumno alumnoExistente = alumnorepository.findById(id).orElse(null);
        if(alumnoExistente != null){
            alumnoExistente.setNombre(alumno.getNombre());
            alumnoExistente.setApellido(alumno.getApellido());
            alumnoExistente.setCurso(alumno.getCurso());
            alumnoExistente.setNota((alumno.getNota()));
            
              return alumnorepository.save(alumnoExistente);
        }else{
            
            return null;
        }
            
    }

    @Override
    public void deleteAlumno(Long id) {
        alumnorepository.deleteById(id);
    }

   
 
    
}
