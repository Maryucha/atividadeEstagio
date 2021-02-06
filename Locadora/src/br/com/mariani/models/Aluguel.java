package br.com.mariani.models;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author maryucha
 */
public class Aluguel {

    private int id;
    private int clienteId;
    private Date dataAluguel;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.clienteId);
        hash = 97 * hash + Objects.hashCode(this.dataAluguel);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluguel other = (Aluguel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
            return false;
        }
        if (!Objects.equals(this.dataAluguel, other.dataAluguel)) {
            return false;
        }
        return true;
    }
    public void imprimeAluguel(){
        System.out.println("ID ["+this.getId()+"] | ID_CLIENTE ["+this.getClienteId()+"] | DATA_LOCAÇÃO ["+this.getDataAluguel()+"] | VALOR ["+this.getValor()+"]");
    }

}
