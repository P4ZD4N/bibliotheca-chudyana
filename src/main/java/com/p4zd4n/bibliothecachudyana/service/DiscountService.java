package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();
    void save(Discount discount);
}
