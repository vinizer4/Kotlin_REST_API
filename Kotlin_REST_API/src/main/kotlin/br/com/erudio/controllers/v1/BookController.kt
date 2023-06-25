package br.com.erudio.controllers.v1

import br.com.erudio.data.dto.v1.BookDTO
import br.com.erudio.services.BookService
import br.com.erudio.utils.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for managing Books")
class BookControllerV1 {

    @Autowired
    private lateinit var service: BookService

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(summary = "Finds all Books", description = "Finds all Books",
            tags = ["Books"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(array = ArraySchema(schema = Schema(implementation =
                            BookDTO::class)))
                        ]
                ),
                ApiResponse(description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun findAll(): List<BookDTO> {
        return service.findAll()
    }

    @GetMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    @Operation(summary = "Finds a Book", description = "Finds a Book",
            tags = ["Books"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(schema = Schema(implementation = BookDTO::class))
                        ]
                ),
                ApiResponse(description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun findById(@PathVariable(value="id") id: Long): BookDTO {
        return service.findById(id)
    }

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    @Operation(summary = "Adds a new Book", description = "Adds a new Book",
            tags = ["Books"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(schema = Schema(implementation = BookDTO::class))
                        ]
                ),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun create(@RequestBody book: BookDTO): BookDTO {
        return service.create(book)
    }

    @PutMapping(
            consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    @Operation(summary = "Updates a book's information", description = "Updates a book's information",
            tags = ["Books"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(schema = Schema(implementation = BookDTO::class))
                        ]
                ),
                ApiResponse(description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun update(@RequestBody book: BookDTO): BookDTO {
        return service.update(book)
    }

    @DeleteMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    fun delete(@PathVariable(value="id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
