package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.DiscountDAO;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import com.p4zd4n.bibliothecachudyana.service.implementation.DiscountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceTest {

    @Mock
    private DiscountDAO discountDAO;

    @InjectMocks
    private DiscountServiceImpl discountService;

    @Test
    @DisplayName("Test for findAll() method")
    public void testFindAll() {

        List<Discount> discounts = List.of(new Discount(), new Discount());

        when(discountDAO.findAll()).thenReturn(discounts);

        List<Discount> result = discountService.findAll();

        assertEquals(discounts, result);
    }

    @Test
    @DisplayName("Test for findById() method")
    public void testFindById() {

        Discount discount = new Discount();
        discount.setId(1);

        when(discountDAO.findById(1)).thenReturn(discount);

        Discount result = discountService.findById(1);

        assertEquals(discount, result);
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        Discount discount = new Discount();

        discountService.save(discount);

        verify(discountDAO, times(1)).save(discount);
    }

    @Test
    @DisplayName("Test for update() method")
    public void testUpdate() {

        Discount discount = new Discount();

        discountService.update(discount);

        verify(discountDAO, times(1)).update(discount);
    }

    @Test
    @DisplayName("Test for delete() method")
    public void testDelete() {

        Discount discount = new Discount();

        discountService.delete(discount);

        verify(discountDAO, times(1)).delete(discount);
    }
}
