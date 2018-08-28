/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kadesh.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Carbono
 * @param <E>
 * @param <I>
 */
public interface IGenericDAO<E extends Serializable, I> {

    public void inserir(E entity);

    public E atualizar(E entity);

    public void excluir(E entity);
    
    List<E> consultarTodos(String condicao);

    public E consultarPorId(I id);
}
