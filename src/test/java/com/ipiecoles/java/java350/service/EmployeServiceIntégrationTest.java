package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmployeServiceIntegrationTest  {
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
    public void integrationEmbaucheEmploye() throws EmployeException {
        //Given
        employerepository.save(new Employe("Doe", "John", "T12345", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0));
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;

        //When
        employeservice.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        Employe employe = employerepository.findByMatricule("T12346");
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom );
        Assertions.assertThat(employe.getDateEmbauche().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).isEqualTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        Assertions.assertThat(employe.getMatricule()).isEqualTo("T12346" );
        Assertions.assertThat(employe.getTempsPartiel().doubleValue()).isEqualTo(1.0);

        //1521.22 * 1.2 * 1.0
        Assertions.assertThat(employe.getSalaire().doubleValue()).isEqualTo(1825.464);
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
