package br.ifpb.monteiro.scream.services;

import java.util.List;

import javax.inject.Inject;

import br.ifpb.monteiro.scream.dao.AtividadeDAO;
import br.ifpb.monteiro.scream.entities.Atividade;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 * 
 * @author Markus Patriota
 *
 */
public class AtividadeService {

	@Inject
	private AtividadeDAO atividadeDAO;

	public void remove(Atividade entity){
		atividadeDAO.delete(entity);
	}

	@Transactional
	public void create(Atividade entity){
		atividadeDAO.create(entity);
	}

	@Transactional
	public void edit(Atividade entity){
		atividadeDAO.update(entity);
	}

	public Atividade find(Long id){
		return atividadeDAO.findById(id);
	}

	public List<Atividade> findAll(){
		return atividadeDAO.findAll();
	}

	public int count(){
		return atividadeDAO.count();
	}

}
