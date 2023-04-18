package com.instagram.instagram.service;

import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.repository.SavedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedService {
    private final SavedRepository savedRepository;

    public SavedService(SavedRepository savedRepository) {
        this.savedRepository = savedRepository;
    }

    public Page<Saved> getAllSavedMessages(Pageable pageable) {
        return savedRepository.findAll(pageable);
    }
    public List<Saved> getAllSavedMessages() {
        return savedRepository.findAll();
    }
    public Saved getById(Long id) {
     return savedRepository.findById(id).orElseThrow(()->new RuntimeException("Saved Post Not Found!"));
    }
}
