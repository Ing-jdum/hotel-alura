package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reserva")
public class Reserva {
	private final BigDecimal PRICE = BigDecimal.valueOf(80.0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_entrada")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_salida")
	private Date fechaSalida;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(name = "forma_pago")
	private String formaPago;

	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Huesped> guests = new ArrayList<>();
	
	public Long getId() {
		return this.id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor() {
		this.valor = this.calculatePrice();
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public List<Huesped> getGuests() {
		return guests;
	}

	public void setGuests(List<Huesped> guests) {
		this.guests = guests;
	}

	private BigDecimal calculatePrice() {
		LocalDate dateFrom = convertDateToLocalDate(this.fechaEntrada);
		LocalDate dateTo = convertDateToLocalDate(this.fechaSalida);
		BigDecimal days = BigDecimal.valueOf(dateFrom.until(dateTo).getDays());
		return days.multiply(PRICE);
	}

	private LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void setId(Long id) {
		this.id = id;
	}

}
