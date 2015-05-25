/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.ItemProductBacklogDAO;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class ItemProductBacklogService {
    
    
    @Inject
    private ItemProductBacklogDAO itemProductBacklogDAO;
    

    @Transactional
    public void create(ItemProductBacklog entity) {
        try {
            this.itemProductBacklogDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no ItemProductBacklogService: " + e.getMessage());
        }
    }
    
    @Transactional
    public void update(ItemProductBacklog entity){
        this.itemProductBacklogDAO.update(entity);
    }
    
    @Transactional
    public void remove(ItemProductBacklog entity) {
        this.itemProductBacklogDAO.delete(entity);
    }
    
     public int count() {
        return itemProductBacklogDAO.count();
    }
    
    public ItemProductBacklog find(Long id) {
        return (ItemProductBacklog) itemProductBacklogDAO.findById(id);
    }

    public List<ItemProductBacklog> findAll() {
        return itemProductBacklogDAO.findAll();
    }

    public List<ItemProductBacklog> findRange(int[] range) {
        return itemProductBacklogDAO.findRange(range);
    }


    public ItemProductBacklogDAO getItemProductBacklogDAO() {
        return itemProductBacklogDAO;
    }

    public void setItemProductBacklogDAO(ItemProductBacklogDAO itemProductBacklogDAO) {
        this.itemProductBacklogDAO = itemProductBacklogDAO;
    }
    
    
}
