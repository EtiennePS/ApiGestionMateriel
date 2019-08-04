package com.materiel.gestion.apigestion.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "user")
public class User {
		
		public User() {}
		
		public User(String username, String password) {
			this.setUsername(username);
			this.setPassword(password);
		}
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "username", nullable = false, unique = true)
		private String username;
		
		@Column(name = "password", nullable = false)
		@JsonProperty(access = Access.WRITE_ONLY)
		private String password;
		
}

