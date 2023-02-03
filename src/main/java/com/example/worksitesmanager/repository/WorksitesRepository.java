package com.example.worksitesmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.worksitesmanager.model.Worksites;

public interface WorksitesRepository extends JpaRepository<Worksites, Long> {

    @Query(value = "SELECT * FROM worksites ORDER BY created_at desc", nativeQuery = true)
    List<Worksites> findAllOrderByCreatedAt();

    @Query(value = "SELECT MAX(id) FROM worksites ORDER BY id", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT * FROM worksites WHERE DATE_FORMAT(updated_at, '%Y%m%d') BETWEEN ?1 AND ?2 ORDER BY created_at desc", nativeQuery = true)
    List<Worksites> findByUpdatedAtBetweenByCreatedAt(String strFrom, String strTo);
    
}
