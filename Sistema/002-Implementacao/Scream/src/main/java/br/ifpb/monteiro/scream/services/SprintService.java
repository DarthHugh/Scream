package br.ifpb.monteiro.scream.services;

import java.util.List;

import javax.inject.Inject;

import br.ifpb.monteiro.scream.dao.SprintDAO;
import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 * 
 * @author Markus Patriota
 *
 */

public class SprintService {

	@Inject
	private SprintDAO sprintDAO;

	public void remove(Sprint entity){
		sprintDAO.delete(entity);
	}
	
	@Transactional
	public void create(Sprint entity){
		sprintDAO.create(entity);
	}

	@Transactional
	public void edit(Sprint entity){
		sprintDAO.update(entity);
	}

	public Sprint find(Long id){
		return sprintDAO.findById(id);
	}
	
	public List<Sprint> findByProject(Projeto projetoEscolhido) {
		return sprintDAO.findByProjeto(projetoEscolhido);
	}

	public List<Sprint> findAll(){
		return sprintDAO.findAll();
	}

	public int count(){
		return sprintDAO.count();
	}
}
