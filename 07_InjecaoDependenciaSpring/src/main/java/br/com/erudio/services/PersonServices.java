package br.com.erudio.services;

import br.com.erudio.exceptions.ResponseEntityExceptionHandler;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person!");

        return repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));
    }

    public List<Person>  findByAll(){
        logger.info("Finding All persons!");

        return repository.findAll();
    }

    public Person create(Person p){
        logger.info("creating a person!");

        return repository.save(p);
    }

    public Person update(Person p){
        logger.info("Updating a person!");

        var entity = repository.findById(p.getId()).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        entity.setFirstName(p.getFirstName());
        entity.setLastName(p.getLastName());
        entity.setAddress(p.getAddress());
        entity.setGender(p.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting a person!");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        repository.delete(entity);
    }
}
