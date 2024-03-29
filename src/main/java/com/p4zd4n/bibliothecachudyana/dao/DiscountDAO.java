package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Discount;

import java.util.List;

public interface DiscountDAO {
    List<Discount> findAll();
    void save(Discount discount);
}
