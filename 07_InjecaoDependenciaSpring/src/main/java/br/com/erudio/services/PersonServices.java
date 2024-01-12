package br.com.erudio.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResponseEntityExceptionHandler;
import br.com.erudio.mapper.DozerMapper;
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

    public PersonVO findById(Long id){
        logger.info("Finding one person!");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findByAll(){
        logger.info("Finding All persons!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO p){
        logger.info("creating a person!");

        var entity = DozerMapper.parseObject(p, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO p){
        logger.info("Updating a person!");

        var entity = repository.findById(p.getId()).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        entity.setFirstName(p.getFirstName());
        entity.setLastName(p.getLastName());
        entity.setAddress(p.getAddress());
        entity.setGender(p.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id){
        logger.info("Deleting a person!");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        repository.delete(entity);
    }
}
