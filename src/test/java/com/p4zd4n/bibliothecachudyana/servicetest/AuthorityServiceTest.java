package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.service.implementation.AuthorityServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorityServiceTest {

    @Mock
    private AuthorityDAO authorityDAO;

    @InjectMocks
    private AuthorityServiceImpl authorityService;

    @Test
    @DisplayName("Test for cleanUpAuthorities() method")
    public void testCleanUpAuthorities() {

        authorityService.cleanUpAuthorities();
        verify(authorityDAO, times(1)).cleanUpAuthorities();
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        Authority authority = new Authority();

        authorityService.save(authority);
        verify(authorityDAO, times(1)).save(authority);
    }
}
