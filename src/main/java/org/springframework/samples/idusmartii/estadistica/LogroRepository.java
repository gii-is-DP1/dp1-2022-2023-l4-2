package org.springframework.samples.idusmartii.estadistica;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Long>{
    List<Logro> findAll();
}
