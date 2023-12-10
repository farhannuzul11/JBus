package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Algorithm;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import com.farhanNuzulNJBusAF.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * An interface for basic operations to retrieve entities of type T.
 *
 * <p>
 * This interface provides methods to access a JSON table of serializable entities, retrieve
 * entities by their ID, and paginate through a subset of entities.
 * </p>
 *
 * <p>
 * Implementing classes are expected to provide the concrete implementation for the
 * {@link #getJsonTable()} method, which returns the JSON table containing entities of type T.
 * </p>
 *
 * @param <T> The type of entities to be retrieved.
 *
 * @version 1.0
 */
public interface BasicGetController<T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or null if not found.
     */
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }

    /**
     * Retrieves a paginated list of entities.
     *
     * @param page The page number (starting from 0).
     * @param pageSize The size of each page.
     * @return A list of entities for the specified page and page size.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a-> true);
    }
}
