package br.com.yacatecuhtli.core.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<E extends PersistentEntity> extends JpaRepository<E, Integer> {

}
