package com.ipiecoles.java.java350.Repository;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;

import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;


@SpringBootTest
@ExtendWith(SpringExtension.class)
 class EmployeRepositoryIntegrationTest {



        @Autowired
        EmployeService employeservice;
        @Autowired
        EmployeRepository employerepository;


        @BeforeEach
        @AfterEach
         void purgeBdd(){
            employerepository.deleteAll();
        }
        @Test


         void testAvgPerformanceWhereMatriculeStartsWith()
        {

            employerepository.save(new Employe("Punk", "Daft", "C00002", LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));
            employerepository.save(new Employe("David", "Guetta", "C00002", LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));
            employerepository.save(new Employe("Garrix", "Martin", "C00002", LocalDate.now(), Entreprise.SALAIRE_BASE, 4, 1.0));
            employerepository.save(new Employe("Obispo", "Pascal", "C00002", LocalDate.now(), Entreprise.SALAIRE_BASE, 4, 1.0));



            Assertions.assertThat(employerepository.avgPerformanceWhereMatriculeStartsWith("C")).isEqualTo(3);
        }

    }
