package com.Cart.CartProject.repository;

import com.Cart.CartProject.model.Entry;
import com.Cart.CartProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

}