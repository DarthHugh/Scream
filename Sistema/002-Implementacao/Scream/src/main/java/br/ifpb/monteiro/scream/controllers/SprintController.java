package br.ifpb.monteiro.scream.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.services.SprintService;

/**
 * 
 * @author Markus Patriota
 *
 */
@Named
@RequestScoped
public class SprintController {

		@Inject
		private SprintService sprintService;

		private Sprint sprint;
		
		private List<Sprint> listSprint;
		
		public SprintController() {

		}
		
		public void detele(){
			
		}
		public void create(){
			sprintService.create(sprint);
		}
		
		public void update(){
			
		}
		
		public List<Sprint> findAll(){
			return sprintService.findAll();
		}
		
		/**
		 * 
		 * Get's and Set's
		 */
		public SprintService getSprintService() {
			return sprintService;
		}

		public void setSprintService(SprintService sprintService) {
			this.sprintService = sprintService;
		}

		public Sprint getSprint() {
			return sprint;
		}

		public void setSprint(Sprint sprint) {
			this.sprint = sprint;
		}

		public List<Sprint> getListSprint() {
			return listSprint;
		}

		public void setListSprint(List<Sprint> listSprint) {
			this.listSprint = listSprint;
		}
	    
}
