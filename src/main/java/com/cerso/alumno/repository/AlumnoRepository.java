
package com.cerso.alumno.repository;

import com.cerso.alumno.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cerso
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
}
