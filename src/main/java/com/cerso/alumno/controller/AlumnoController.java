package com.cerso.alumno.controller;

import com.cerso.alumno.entity.Alumno;
import com.cerso.alumno.service.AlumnoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"*"})
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public List<Alumno> index() {

        return alumnoService.getAlumnos();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Alumno alumno = null;
        Map<String, Object> response = new HashMap<>();
        try {
            alumno = alumnoService.findAlumnoById(id);
        } catch (DataAccessException e) {
            response.put("mensaje:", "Error de acceso a la base de datos");
            response.put("error:", e.getMessage().concat("").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (alumno == null) {
            response.put("mensaje", "El alumno no se encuentra en el sistema");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
        //return alumnoService.findAlumnoById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody Alumno alumno) {

        Alumno alumnoNuevo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            alumnoNuevo = alumnoService.saveAlumno(alumno);

        } catch (DataAccessException e) {
            response.put("mensaje:", "Error al insertar el alumno a la base de datos");
            response.put("error:", e.getMessage().concat("").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje:", "Alumno creado con exito");
        response.put("alumno", alumnoNuevo);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
        //return alumnoService.saveAlumno(alumno);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alumno alumno) {

        Alumno alumnoActualizado = null;
        Map<String, Object> response = new HashMap<>();

        alumnoActualizado = alumnoService.findAlumnoById(id);

        if (alumnoActualizado == null) {
            response.put("mensaje", "No se ha podido actualizar al alumno porque no se encuentra en el sistema");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            alumnoActualizado = alumnoService.updateAlumno(id, alumno);

        } catch (DataAccessException e) {

            response.put("mensaje:", "Error al actualizar el alumno en la base de datos");
            response.put("error:", e.getMessage().concat("").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }

        response.put("mensaje", "alumno actualizado con exito");
        response.put("alumno actualizado", alumnoActualizado);

        return new ResponseEntity<Alumno>(alumnoActualizado, HttpStatus.CREATED);

        //return alumnoService.updateAlumno(id, alumno);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        
        try {
            alumnoService.deleteAlumno(id);
            
        } catch (DataAccessException e) {
            
            response.put("mensaje:", "Error al eliminar el alumno de la base de datos");
            response.put("error:", e.getMessage().concat("").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje:", "Alumno eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

        //alumnoService.deleteAlumno(id);
    }

}
