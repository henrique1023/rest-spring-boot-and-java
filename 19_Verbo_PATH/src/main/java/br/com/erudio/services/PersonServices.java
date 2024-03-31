package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectisNullException;
import br.com.erudio.exceptions.ResponseEntityExceptionHandler;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one person!");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findByAll() {
        logger.info("Finding All persons!");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO create(PersonVO p) throws Exception {
        if(p == null)
            throw new RequiredObjectisNullException();

        logger.info("creating a person!");

        var entity = DozerMapper.parseObject(p, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO p) throws Exception {

        if(p == null)
            throw new RequiredObjectisNullException();

        logger.info("Updating a person!");

        var entity = repository.findById(p.getKey()).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        entity.setFirstName(p.getFirstName());
        entity.setLastName(p.getLastName());
        entity.setAddress(p.getAddress());
        entity.setGender(p.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) throws Exception {
        logger.info("Disabling Person by id!");
        repository.disablePerson(id);

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }
    public void delete(Long id){
        logger.info("Deleting a person!");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResponseEntityExceptionHandler("No records found for this ID"));

        repository.delete(entity);
    }
}
