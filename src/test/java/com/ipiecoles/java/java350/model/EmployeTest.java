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
     @Test
      void getNombreAnneeAncienneteNow(){
         //Given
         Employe e = new Employe();
         e.setDateEmbauche(LocalDate.now());

         //When
         Integer anneeAnciennete = e.getNombreAnneeAnciennete();

         //Then
         Assertions.assertThat( anneeAnciennete.intValue()).isZero();
     }

     @Test
      void getNombreAnneeAncienneteNminus2(){
         //Given
         Employe e = new Employe();
         e.setDateEmbauche(LocalDate.now().minusYears(2L));

         //When
         Integer anneeAnciennete = e.getNombreAnneeAnciennete();

         //Then
         Assertions.assertThat(anneeAnciennete.intValue()).isEqualTo(2);
     }

     @Test
      void getNombreAnneeAncienneteNull(){
         //Given
         Employe e = new Employe();
         e.setDateEmbauche(null);

         //When
         Integer anneeAnciennete = e.getNombreAnneeAnciennete();

         //Then
         Assertions.assertThat( anneeAnciennete).isNull();
     }

     @Test
      void getNombreAnneeAncienneteNplus2(){
         //Given
         Employe e = new Employe();
         e.setDateEmbauche(LocalDate.now().plusYears(2L));

         //When
         Integer anneeAnciennete = e.getNombreAnneeAnciennete();

         //Then
         Assertions.assertThat(anneeAnciennete).isNull();
     }

     @ParameterizedTest
     @CsvSource({
             "1, 'T12345', 0, 1.0, 1000.0",
             "1, 'T12345', 2, 0.5, 600.0",
             "1, 'T12345', 2, 1.0, 1200.0",
             "2, 'T12345', 0, 1.0, 2300.0",
             "2, 'T12345', 1, 1.0, 2400.0",
             "1, 'M12345', 0, 1.0, 1700.0",
             "1, 'M12345', 5, 1.0, 2200.0",
             "2, 'M12345', 0, 1.0, 1700.0",
             "2, 'M12345', 8, 1.0, 2500.0"
     })
      void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle){
         //Given
         Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

         //When
         Double prime = employe.getPrimeAnnuelle();

         //Then
         Assertions.assertThat(prime).isEqualTo(primeAnnuelle);

     }

}