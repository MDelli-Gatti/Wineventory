package com.delligatti.Wineventory.services;

import com.delligatti.Wineventory.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
