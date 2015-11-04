/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.DefinicaoDeProntoDAO;
import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;
import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class DefinicaoDeProntoService {

	@Inject
	private DefinicaoDeProntoDAO definicaoDeProntoDAO;

	@Transactional
	public Boolean update(DefinicaoDePronto entity){
		if(entity!=null){
			try {
				this.definicaoDeProntoDAO.update(entity);
				return true;
			} catch (Exception e) {
				getLogger().log(Level.SEVERE, "Erro no DefinicaoDeProntoService: ", e);
			}

		} 
		return false;

	}

	private void createDefinicaoProntoRelease(Projeto projeto){
		DefinicaoDePronto entity = new DefinicaoDePronto();
		entity.setDescricao("");
		entity.setTipoDefinicao(DefinicaoDeProntoEnum.RELEASE);
		entity.setProjeto(projeto);
		try {
			this.definicaoDeProntoDAO.create(entity);
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Erro no DefinicaoDeProntoService: ", e);
		}
	}

	private void createDefinicaoProntoSprint(Projeto projeto){
		DefinicaoDePronto entity = new DefinicaoDePronto();
		entity.setDescricao("");
		entity.setTipoDefinicao(DefinicaoDeProntoEnum.SPRINT);
		entity.setProjeto(projeto);
		try {
			this.definicaoDeProntoDAO.create(entity);
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Erro no DefinicaoDeProntoService: ", e);
		}
	}

	private void createDefinicaoProntoPB(Projeto projeto){
		DefinicaoDePronto entity = new DefinicaoDePronto();
		entity.setDescricao("");
		entity.setTipoDefinicao(DefinicaoDeProntoEnum.PRODUCTBACKLOG);
		entity.setProjeto(projeto);
		try {
			this.definicaoDeProntoDAO.create(entity);
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Erro no DefinicaoDeProntoService: ", e);
		}
	}

	public DefinicaoDePronto find(Long id) {
		return (DefinicaoDePronto) definicaoDeProntoDAO.findById(id);
	}

	public List<DefinicaoDePronto> findByProjeto(Projeto entity){
		return definicaoDeProntoDAO.findByProjeto(entity);
	}

	public List<DefinicaoDePronto> findAll() {
		return definicaoDeProntoDAO.findAll();
	}
	public void createDefinicaoPronto(Projeto projeto){
		createDefinicaoProntoPB(projeto);
		createDefinicaoProntoRelease(projeto);
		createDefinicaoProntoSprint(projeto);

	}
}
