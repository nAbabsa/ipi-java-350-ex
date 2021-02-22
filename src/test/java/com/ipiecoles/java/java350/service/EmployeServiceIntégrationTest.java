package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmployeServiceIntégrationTest  {
    @Autowired
    EmployeService employeservice;
    @Autowired
    EmployeRepository employerepository;

    @BeforeEach
    @AfterEach
    public void purgeBdd(){
        employerepository.deleteAll();
    }
    @Test
     void testIntegrationCalculPerformanceCommercial() throws EmployeException
    {
     employeservice.embaucheEmploye("john", "Doe", Poste.COMMERCIAL, NiveauEtude.INGENIEUR,1.0);
        List<Employe> listeEmployes = employerepository.findAll();
        Assertions.assertThat(listeEmployes).hasSize(1);
        Employe employe = listeEmployes.get(0);
        //when

        employeservice.calculPerformanceCommercial("C00001", 1000L, 1000L);
        //then

        Assertions.assertThat(employe.getPerformance()).isEqualTo(1);


    }


}
