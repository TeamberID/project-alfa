package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Criteria;
import ru.kpfu.itis.app.repositories.CriteriaRepository;
import ru.kpfu.itis.app.services.CriteriaService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Service
public class CriteriaServiceImpl implements CriteriaService {

    private CriteriaRepository criteriaRepository;

    public CriteriaServiceImpl(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
    }

    @Override
    public List<Criteria> getAllCriteria() {
        return criteriaRepository.findAll();
    }
}
