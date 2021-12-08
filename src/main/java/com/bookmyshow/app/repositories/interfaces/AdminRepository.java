package com.bookmyshow.app.repositories.interfaces;

import com.bookmyshow.app.models.Admin;
import com.bookmyshow.app.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    <S extends Admin> List<S> findAllByUserAndPhone1AndPhone2(User user, String phone1, String phone2);

    // SELECT * from admins where
    // user == {}
    // AND phone1 == {}
    // AND phone2 == {}
}
