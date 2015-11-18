package br.ifpb.monteiro.scream.services;

import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;

import java.util.List;
import java.util.logging.Level;

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
	public Boolean create(Atividade entity){
		try {
			atividadeDAO.create(entity);
			return true;
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Erro no ProjetoService ", e);
			return false;
		}
	}

	@Transactional
	public void edit(Atividade entity){
		this.atividadeDAO.update(entity);
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
