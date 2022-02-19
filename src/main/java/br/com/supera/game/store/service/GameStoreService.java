package br.com.supera.game.store.service;

import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.exceptions.ProductNotFoundException;
import br.com.supera.game.store.repository.GameStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameStoreService {

    @Autowired
    private final GameStoreRepository repository;

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("id inexistente"));
    }
}
