package service;

import model.Carro;
import model.CarroException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(MockitoExtension.class)
public class CarroServiceImplTest {


    @Test
    public void deveAcelerarCorretamente() {
        CarroService service = new CarroServiceImpl();

        // Given
        Carro carroTeste01 = new Carro("Azul", "Fiat", "Uno", 2015, 150);
        service.ligar(carroTeste01);

        // When
        service.acelerar(carroTeste01, 10);
        Assert.assertTrue(carroTeste01.getVelocidadeAtual() == 10);

        assertThrows(CarroException.class, () -> {
            service.acelerar(carroTeste01, carroTeste01.getVelocidadeMaxima() + 200);
        });

        carroTeste01.setLigado(false);
        assumeFalse(carroTeste01.isLigado());
        assertThrows(CarroException.class, () -> {
            service.acelerar(carroTeste01, 10);
        });
    }

    @Test
    public void deveFrearCorretamente(){
        CarroService service = new CarroServiceImpl();
        Carro carro = new Carro("Azul", "Fiat", "Uno", 2015, 150);

        assumeFalse(carro.isLigado());
        assertThrows(CarroException.class, () -> {
           service.frear(carro, 10);
        });

        carro.setLigado(true);
        assumeTrue(carro.isLigado());

        service.acelerar(carro, 10);
        Assert.assertTrue(carro.getVelocidadeAtual() == 10);

        assertThrows(CarroException.class, () -> {
            service.frear(carro, 100);
        });

        service.frear(carro, 10);
        assertTrue(carro.getVelocidadeAtual() == 0);


    }

    @Test
    public void deveLigarCorretamente() {
        CarroService service = new CarroServiceImpl();
        Carro carro = new Carro("Azul", "Fiat", "Uno", 2015, 150);

        assumeFalse(carro.isLigado());
        service.ligar(carro);
        assertTrue(carro.isLigado());
    }

    @Test
    public void deveDesligarCorretamente(){
        CarroService service = new CarroServiceImpl();
        Carro carro = new Carro("Azul", "Fiat", "Uno", 2015, 150);

        assumeFalse(carro.isLigado());
        service.ligar(carro);
        assertTrue(carro.isLigado());


        service.desligar(carro);
        assertFalse(carro.isLigado());
    }

    @Test
    public void deveMostrarOEstadoAtualDoCarroCorretamente(){
        CarroService service = new CarroServiceImpl();
        Carro carro = new Carro("Azul", "Fiat", "Uno", 2015, 150);

        assumeFalse(carro.isLigado());
        assertEquals(String.valueOf(carro), service.estadoAtual(carro));

        carro.setLigado(true);
        assumeTrue(carro.isLigado());
        assertEquals(String.valueOf(carro), service.estadoAtual(carro));
    }

}
