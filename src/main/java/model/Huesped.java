package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "huesped")
public class Huesped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(nullable = false)
	private String nacionalidad;

	@Column(nullable = false)
	private String telefono;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reserva_id")
	private Reserva reserva;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", reserva=" + reserva
				+ "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

}
