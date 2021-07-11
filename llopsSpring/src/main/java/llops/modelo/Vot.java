package llops.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Vot")
public class Vot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id",updatable = false, nullable = false)
	int id;
	
	@ManyToOne
	@JoinColumn (name = "sender")
	private User sender;
	@ManyToOne
	@JoinColumn (name = "receiver")
	private User receiver;
	
	@ManyToOne
	@JoinColumn (name = "partida")
	private Partida partida;
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@Column (name = "torn")
	private int torn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getTorn() {
		return torn;
	}

	public void setTorn(int torn) {
		this.torn = torn;
	}

	public Vot(int id, int torn) {
		super();
		this.id = id;
		this.torn = torn;
	}

	public Vot() {
		super();
	}

	public Vot( int torn,Partida partidaid, User receiver,User sender ) {
		super();
		this.torn = torn;
		this.partida = partidaid;
		this.receiver = receiver;
		this.sender = sender;
		
	}

	









}
