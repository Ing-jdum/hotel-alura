package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import utils.SecurityUtils;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String userName;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "salt_id", nullable = false)
	private Salt salt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordHash) {
		Salt salt = new Salt();
		salt.setSaltValue(SecurityUtils.generateSalt());
		this.setSalt(salt);
		this.password = SecurityUtils.hashPasswordWithSalt(passwordHash, this.getSalt());
	}

	public Salt getSalt() {
		return salt;
	}

	public void setSalt(Salt salt) {
		this.salt = salt;
	}
}
