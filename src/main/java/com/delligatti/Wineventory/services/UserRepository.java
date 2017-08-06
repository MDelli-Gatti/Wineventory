package com.delligatti.Wineventory.services;

import org.springframework.data.repository.CrudRepository;
import com.delligatti.Wineventory.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
