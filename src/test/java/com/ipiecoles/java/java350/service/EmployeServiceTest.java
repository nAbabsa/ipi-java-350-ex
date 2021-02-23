package com.ipiecoles.java.java350.service;



import java.time.LocalDate;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.repository.EmployeRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)

 class EmployeServiceTest {

    @InjectMocks
    EmployeService employeService;

    @Mock
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this.getClass());
    }
@ParameterizedTest
@CsvSource({"C00001,1500,1000,1,6", "C00001, 800, 1000, 5,4", "C00001, 1055, 1000, 5,7", "C00001, 1000, 1000,5,6" })
 void testCalculPerformanceCommercial(String matricule, Long caTraite, Long objectifCa, Integer perfBase, Integer perfAttendu) throws EmployeException {
    //Given
    Employe employe = new Employe("Marc", "Davis", matricule, LocalDate.now(), Entreprise.SALAIRE_BASE, perfBase, 1.0);
    Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(employe);

    //When
    employeService.calculPerformanceCommercial(matricule, caTraite, objectifCa);
    ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
    Mockito.verify(employeRepository, Mockito.times(1)).save(employeArgumentCaptor.capture());

    //Then
    Assertions.assertThat(employeArgumentCaptor.getValue().getPerformance()).isEqualTo(perfAttendu);
}
    @Test

    public void testPerformanceCommercialcaTraiteNull() throws EmployeException {
//given
        Employe employe = new Employe("Marc", "Davis", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE,Entreprise.PERFORMANCE_BASE,1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);
        //when
        //employeService.calculPerformanceCommercial("C00001",null, 1000L);
        //ArgumentCaptor<Employe> EmpPerf = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, Mockito.times(1)).save(EmpPerf.capture());
        //then

        try{
            employeService.calculPerformanceCommercial("C00001",null, 1000L);

        }
        catch (Exception e)
        {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaire traité ne peut être négatif ou null !");
        }
    }
    @Test
    public void testPerformanceCommercialobjCaNull() throws EmployeException {
//given
        Employe employe = new Employe("Marc", "Davis", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE,Entreprise.PERFORMANCE_BASE,1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);
        //when
        //employeService.calculPerformanceCommercial("C00001",1000L, null);
        //ArgumentCaptor<Employe> EmpPerf = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository, Mockito.times(1)).save(EmpPerf.capture());
        //then

        try{
            employeService.calculPerformanceCommercial("C00001",1000L, null);

        }
        catch (Exception e)
        {
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaire ne peut être négatif ou null !");
        }
    }
    @Test
    public void testPerformanceCommercialmatriculeNull() throws EmployeException {
//given
        Employe employe = new Employe("Marc", "Davis", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE,Entreprise.PERFORMANCE_BASE,1.0);
        //Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);
        //when

        //ArgumentCaptor<Employe> EmpPerf = ArgumentCaptor.forClass(Employe.class);
        //Mockito.verify(employeRepository,Mockito.Mockito.times(1)).save(EmpPerf.capture());
        //then
        try{
            employeService.calculPerformanceCommercial(null,1000L, 1000L);

        }
        catch (Exception e)
        {
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }

    }

    @Test
    public void testPerformanceCommercialmatriculenonNull() throws EmployeException {
//given
        Employe employe = new Employe("Marc", "Davis", "C00001", LocalDate.now(), Entreprise.SALAIRE_BASE,Entreprise.PERFORMANCE_BASE,1.0);
        Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(employe);
        //when
        employeService.calculPerformanceCommercial("C00001",1000L, 1000L);
        ArgumentCaptor<Employe> EmpPerf = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository, Mockito.times(1)).save(EmpPerf.capture());
        //then
        Assertions.assertThat(EmpPerf.getValue().getPerformance()).isEqualTo(2);
    }
}
