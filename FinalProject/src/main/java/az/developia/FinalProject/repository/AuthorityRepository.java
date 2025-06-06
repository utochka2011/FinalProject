package az.developia.FinalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.FinalProject.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
