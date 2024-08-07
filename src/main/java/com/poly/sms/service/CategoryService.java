package com.poly.sms.service;

import com.poly.sms.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(Integer id);

    Category update(Category category);

    void deleteById(Integer id);
}
