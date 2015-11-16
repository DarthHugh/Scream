package br.ifpb.monteiro.scream.entities.enums;

/**
 * 
 * @author Markus Patriota
 *
 */

public enum StatusKanbanEnum {
	SNOOZE(0),
	TODO(1),
	DOING(2),
	DONE(3);

	private int statusKanban;
	StatusKanbanEnum(int statusKanban){
		this.statusKanban=statusKanban;
	}
	public int getStatusKanban() {
		return statusKanban;
	}
	public void setStatusKanban(int statusKanban) {
		this.statusKanban = statusKanban;
	}

}
