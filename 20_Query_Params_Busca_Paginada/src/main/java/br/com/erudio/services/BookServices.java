package br.com.erudio.services;

import java.util.List;

import br.com.erudio.controllers.BookController;
import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Book;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import br.com.erudio.mapper.DozerMapper;

import br.com.erudio.data.vo.v1.BookVO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;

	@Autowired
	PagedResourcesAssembler<BookVO> assembler;
		
	public BookVO create(BookVO book) {
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {

		var bookPage = repository.findAll(pageable);
		return getListVos(pageable, bookPage);
	}
	
	public BookVO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerMapper.parseObject(entity, BookVO.class);
	}
		
	public BookVO update(BookVO book) {
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	private PagedModel<EntityModel<BookVO>> getListVos(Pageable pageable, Page<Book> personPage) {
		var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, BookVO.class));
		personVosPage.map(p -> {
			try {
				return p.add(linkTo(methodOn(BookController.class)
						.findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		Link link = linkTo(methodOn(BookController.class)
				.findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

		return assembler.toModel(personVosPage, link);
	}

}
