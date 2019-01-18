package com.m2i.tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({
	@NamedQuery(name="InfoAuth.findAll",query="SELECT ia FROM InfoAuth ia"),
	@NamedQuery(name="InfoAuth.clientFromUsername",query="SELECT ia.client FROM InfoAuth ia WHERE ia.username = :username")
})
@Getter @Setter @NoArgsConstructor
public class InfoAuth {
	@Id
	@Column(length=64)
    private String username;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name="ref_client")
	private Client client;
	
	

	@Override
	public String toString() {
		return "InfoAuth [username=" + username + ", password=" + password + "]";
	}

	public InfoAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	

}
