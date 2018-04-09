package ru.kpfu.itis.app.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.services.InstituteService;

import java.util.List;


/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/university")
public class UniversityRestController {
    private InstituteService instituteService;

    @Autowired
    public UniversityRestController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping("/{id}/institutes")
    public List<Institute> getInstitutesByUniversityId(@PathVariable("id") Long universityId) {
        return instituteService.getInstitutesByUniversityId(universityId);
    }

}
