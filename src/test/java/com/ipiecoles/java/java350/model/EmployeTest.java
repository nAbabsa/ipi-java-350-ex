package com.ipiecoles.java.java350.model;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

 class EmployeTest{


@ParameterizedTest
    @CsvSource({"5000,0.18,5900", "1500, 0.36,2040", "8000, 0.80,14400", "100, 80,180"
})

     void testAugmenterSalaire(Double salaire,Double pourcentage, Double salaireAugmente){

        //given
        Employe employe = new Employe();
        employe.setSalaire(salaire);

        //when
        employe.augmenterSalaire(pourcentage);

        //then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaireAugmente);
    }
    @ParameterizedTest
    @CsvSource({"2019,8", "2021,10", "2022,10", "2032,11"})
     void nbRtt(int annee, int nombreRtt){
    //given
    Employe employe = new Employe();


    //when
        int nbRtt = employe.getNbRtt(LocalDate.of(annee,1,1));

        //then
        Assertions.assertThat(nbRtt).isEqualTo(nombreRtt);

    }
}