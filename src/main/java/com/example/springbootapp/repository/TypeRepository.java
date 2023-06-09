package com.example.springbootapp.repository;

import com.example.springbootapp.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

   // void deleteByTutorialId(Long tutorialId);

}
