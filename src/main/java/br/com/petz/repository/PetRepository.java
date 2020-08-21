package br.com.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
