package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "salt")
public class Salt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(name = "byte_array_column", nullable = false)
	private byte[] saltValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getSaltValue() {
		return saltValue;
	}

	public void setSaltValue(byte[] saltValue) {
		this.saltValue = saltValue;
	}

}
