/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Util.ValidacaoException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDao;
import model.domain.Cliente;
import model.service.ServiceLocator;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author jvianafi
 */
public class ClienteControl {
    
    private PropertyChangeSupport PropertyChangeSupport  = new PropertyChangeSupport(this);
            
            
   private Cliente clienteDigitado;
    private Cliente clienteSelecionado;
    private List<Cliente> clientesTabela;
    private ClienteDao clienteDao;
    
    public ClienteControl(){
    clienteDao = ServiceLocator.getClienteDao();
    clientesTabela = ObservableCollections.observableList(new ArrayList<Cliente>());
                
                novo();
                pesquisar();
                
    }

    public void novo() {
    setClienteDigitado(new Cliente());
        
        }

    public void pesquisar() {
    clientesTabela.clear();
    clientesTabela.addAll(clienteDao.pesquisar(clienteDigitado));
    
    
    }
    
    public void salvar() throws ValidacaoException {
        clienteDigitado.validar();
        clienteDao.salvarAtualizar(clienteDigitado);
        novo();
        pesquisar();
        
    }
    
     public void excluir(){
        clienteDao.excluir(clienteDigitado);
        novo();
        pesquisar();
        
    }

    public Cliente getClienteDigitado() {
        return clienteDigitado;
    }

    public void setClienteDigitado(Cliente clienteDigitado) {
        Cliente oldClienteDigitado = this.clienteDigitado;
        
        this.clienteDigitado = clienteDigitado;
        
        PropertyChangeSupport.firePropertyChange("clienteDigitado", oldClienteDigitado, clienteDigitado);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
         if(this.clienteSelecionado != null){
            setClienteDigitado(clienteSelecionado);    
    }
    }
    public List<Cliente> getClientesTabela() {
        return clientesTabela;
    }

   
    public void setClienteTabela(List<Cliente> clienteTabela) {
        this.clientesTabela = clienteTabela;
    }
    
    public void addPropertyChangeListe(PropertyChangeListener listener){
        PropertyChangeSupport.addPropertyChangeListener(null);  
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener){
      PropertyChangeSupport.removePropertyChangeListener(null);
    }

    private void setClienteDigiatado(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
