package br.com.supera.game.store.repository;

import br.com.supera.game.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStoreRepository extends JpaRepository<Product, Long> {
}
