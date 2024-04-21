package com.projet.tsakitsaky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.Axe;
import com.projet.tsakitsaky.repository.AxeRepository;

@Service
public class AxeService {
    
    @Autowired
    private AxeRepository axeRepository;

    public List<Axe> getAllAxe()
    {
        return axeRepository.findAll();
    }
}
