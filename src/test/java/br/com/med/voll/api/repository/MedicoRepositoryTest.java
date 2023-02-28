package br.com.med.voll.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Test
    void findAllByAtivoTrue() {
    }

    @Test
    @DisplayName("Deve Devolver Null quando o médico não está disponivel na data")
    void escolherMedicoAlatorioCenarioUm() {
    }

    @Test
    @DisplayName("Deve Devolver True quando o médico não está disponivel na data")
    void escolherMedicoAlatorioCenarioDois() {
    }
    @Test
    void verificaMedicoAtivo() {
    }
}