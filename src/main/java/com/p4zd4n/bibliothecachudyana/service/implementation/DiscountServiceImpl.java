package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.DiscountDAO;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import com.p4zd4n.bibliothecachudyana.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

    @Override
    public List<Discount> findAll() {
        return discountDAO.findAll();
    }

    @Override
    public void save(Discount discount) {
        discountDAO.save(discount);
    }
}
