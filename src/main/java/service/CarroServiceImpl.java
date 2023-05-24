package service;

import model.Carro;
import model.CarroException;

public class CarroServiceImpl implements CarroService {

    @Override
    public void acelerar(Carro carro, int velocidadeAMais) {
        if (!carro.isLigado() || carro.getVelocidadeAtual() + velocidadeAMais > carro.getVelocidadeMaxima()) throw new CarroException("Carro não pode acelerar.");

        carro.setVelocidadeAtual(carro.getVelocidadeAtual() + velocidadeAMais);
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) {
        if (carro.getVelocidadeAtual() - velocidadeAMenos < 0 || !carro.isLigado()) throw new CarroException("Carro " +
                "não pode frear.");;

        carro.setVelocidadeAtual(carro.getVelocidadeAtual() - velocidadeAMenos);
    }

    @Override
    public void ligar(Carro carro) {
        if (carro.isLigado()) throw new CarroException("Carro não pode ligar.");;
        carro.setLigado(true);
    }

    @Override
    public void desligar(Carro carro) {
        if (!carro.isLigado() && carro.getVelocidadeAtual() == 0) throw new CarroException("Carro não pode desligar.");;
        carro.setLigado(false);
    }

    @Override
    public String estadoAtual(Carro carro) {
        return String.valueOf(carro);
    }
}
