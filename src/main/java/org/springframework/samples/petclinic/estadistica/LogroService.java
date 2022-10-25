package org.springframework.samples.petclinic.estadistica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroService {
    private LogroRepository logroRepo;
    
    @Autowired
    public LogroService(LogroRepository logroRepo){
        this.logroRepo = logroRepo;
    }

    @Transactional(readOnly = true)
    public List<Logro> getLogros(){
        return logroRepo.findAll();
    }
    @Transactional
    public void deleteLogro(long id) {
        logroRepo.deleteById(id);
    }
    @Transactional
    public Optional<Logro> getLogroById(long id) {
		Optional<Logro> result = logroRepo.findById(id);
		return result;
	}

    @Transactional
    public void save(Logro a){
        logroRepo.save(a);
    }
}
