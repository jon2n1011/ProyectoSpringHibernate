package llops.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="xatMessage")
public class xatMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	int id;
	// Many to one
	@ManyToOne
	@JoinColumn(name="sender")
	private User sender;
	// Many to one
	@ManyToOne
	@JoinColumn(name="partida")
	private Partida partida;
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	// Lob por que puede ser un mensaje muy largo
	 @Lob
	 @Column(name = "content")
	 private String content;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	@Column (name = "date")
	private Date dataRegistre;
	 

	 
	 
	public Date getDataRegistre() {
		return dataRegistre;
	}
	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public xatMessage(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public xatMessage( User sender, Partida partida, String content) {
		super();
		Date data = new Date();
		this.sender = sender;
		this.partida = partida;
		this.content = content;
		this.dataRegistre = data;
	}
	public xatMessage() {
		super();
	}
	@Override
	public String toString() {
		return "xatMessage [sender=" + sender + ", content=" + content + "]";
	}
	
	

	 
	 
	   
	}
	

