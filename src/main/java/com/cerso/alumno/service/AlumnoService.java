
package com.cerso.alumno.service;

import com.cerso.alumno.entity.Alumno;
import java.util.List;

/**
 *
 * @author cerso
 */
public interface AlumnoService {
   public List<Alumno> getAlumnos();
   public Alumno findAlumnoById(Long id);
   public Alumno saveAlumno(Alumno alumno);
   public Alumno updateAlumno(Long id,Alumno alumno);
   public void deleteAlumno(Long id);
}
