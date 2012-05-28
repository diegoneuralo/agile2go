package br.com.scrum.domain.entity.enums;

public enum Status {
	
	TODO("TODO"),
	
	ONGOING("ONGOING"),
	
	DONE("DONE"),
	
	WAITTING("WAITTING");
	
	private String status;
	
	private Status (final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
		
}
