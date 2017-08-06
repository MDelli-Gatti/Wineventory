package com.delligatti.Wineventory.services;

import com.delligatti.Wineventory.entities.Wine;
import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Integer> {
}
