package llops.modelo;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = true, nullable = false)
	int id;

	// Many to one receiver
	@ManyToOne
	@JoinColumn(name = "sender", nullable = false)
	private User sender;

	// Many to one sender
	@ManyToOne
	@JoinColumn(name = "receiver", nullable = false)
	private User receiver;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	tipus type;

	@Lob
	@Column(name = "content", nullable = false)
	String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registre")
	private Date date;

	// CONSTRUCTORES
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public tipus getType() {
		return type;
	}

	public void setType(tipus type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	public Message() {
		this.type=tipus.text;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", type=" + type + ", content="
				+ content + ", date=" + date + "]";
	}

}