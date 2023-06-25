package br.com.erudio.services

import br.com.erudio.controllers.v1.BookControllerV1
import br.com.erudio.data.dto.v1.BookDTO
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.models.Book
import br.com.erudio.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {

    @Autowired
    private lateinit var repository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll(): List<BookDTO> {
        logger.info("Finding all books!")
        val books = repository.findAll()
        val dtos = DozerMapper.parseListObjects(books, BookDTO::class.java)

        for (book in dtos) {
            val withSelfRel = linkTo(BookControllerV1::class.java).slash(book.key).withSelfRel()
            book.add(withSelfRel)
        }

        return dtos
    }

    fun findById(id: Long): BookDTO {
        logger.info("Finding one person with ID $id!")

        val book = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                    "No records found for this ID!")
        }

        val bookDTO: BookDTO = DozerMapper.parseObject(book, BookDTO::class.java)
        val withSelfRel = linkTo(BookControllerV1::class.java).slash(bookDTO.key).withSelfRel()
        bookDTO.add(withSelfRel)
        return bookDTO
    }

    fun create(book: BookDTO?): BookDTO {
        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Creating one book with title ${book.title}!")
        val entity: Book = DozerMapper.parseObject(book, Book::class.java)
        val bookDTO: BookDTO = DozerMapper.parseObject(repository.save(entity), BookDTO::class.java)
        val withSelfRel = linkTo(BookControllerV1::class.java).slash(bookDTO.key).withSelfRel()
        bookDTO.add(withSelfRel)
        return bookDTO
    }

    fun update(book: BookDTO?): BookDTO {
        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Updating one person with ID ${book.key}!")
        val entity: Book = repository.findById(book.key)
                .orElseThrow {
            ResourceNotFoundException("No records found for this ID!")
        }

        entity.author = book.author
        entity.title = book.title
        entity.price = book.price
        entity.launchDate = book.launchDate

        val bookDTO: BookDTO = DozerMapper.parseObject(repository.save(entity), BookDTO::class.java)
        val withSelfRel = linkTo(BookControllerV1::class.java).slash(bookDTO.key).withSelfRel()
        bookDTO.add(withSelfRel)
        return bookDTO
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID ${id}!")
        val entity: Book = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                    "No records found for this ID!")
        }
        repository.delete(entity)
    }

}
