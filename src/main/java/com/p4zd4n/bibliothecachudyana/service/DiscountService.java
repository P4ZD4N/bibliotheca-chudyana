package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();
    Discount findById(Integer id);
    void save(Discount discount);
    void update(Discount discount);
    void delete(Discount discount);
}
