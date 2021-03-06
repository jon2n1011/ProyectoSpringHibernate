package llops.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="User")
public class User {

	public Set<Partida> getPartidas() {
		return partidas;
	}


	public void setPartidas(Set<Partida> partidas) {
		this.partidas = partidas;
	}

	@Id
	@Column (name = "user_name",columnDefinition="VARCHAR(50) not null", unique=true)
	private String userName;
	
	@Column (name = "password", length=25)
	private String password;
	
	@Column (name = "alies", length=50)
	private String alias;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name = "data_registre")
	private Date dataRegistre;
	
	@Column (name = "path_avatar")
	private String pathAvatar;
	
	@Column (name = "percentatge_victories")
	private double percentatgeVictories;
	

	
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy="users")
	private Set<Partida> partidas=new HashSet();
	

	//Messages
	@OneToMany(mappedBy="sender", cascade=CascadeType.ALL)
	Set<Message> messagesSender = new HashSet();
	//Messages
	@OneToMany(mappedBy="receiver", cascade=CascadeType.ALL)
	Set<Message> messagesReceiver = new HashSet();
	


	//Votos
	@OneToMany(mappedBy="sender", cascade=CascadeType.ALL)
	Set<Vot> votsSender = new HashSet();
	//Votos
	@OneToMany(mappedBy="receiver", cascade=CascadeType.ALL)
	Set<Vot> votsReceiver = new HashSet();
	//ROLJUGADORPARTIDA
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	Set<Roljugadorpartida> userRol = new HashSet();
	//xatMessage
	@OneToMany(mappedBy="sender", cascade=CascadeType.ALL)
	Set<xatMessage> xats = new HashSet();
	//Mort
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	Set<Mort> morts = new HashSet();


	
	public User() {
		
	}
	
	@JsonIgnore
	public Set<Roljugadorpartida> getUserRol() {
		return userRol;
	}

	
	public void setUserRol(Set<Roljugadorpartida> userRol) {
		this.userRol = userRol;
	}
	@JsonIgnore
	public Set<Message> getMessagesSender() {
		return messagesSender;
	}

	
	public void setMessagesSender(Set<Message> messagesSender) {
		this.messagesSender = messagesSender;
	}

	@JsonIgnore
	public Set<Message> getMessagesReceiver() {
		return messagesReceiver;
	}


	public void setMessagesReceiver(Set<Message> messagesReceiver) {
		this.messagesReceiver = messagesReceiver;
	}

	@JsonIgnore
	public Set<Vot> getVotsSender() {
		return votsSender;
	}


	public void setVotsSender(Set<Vot> votsSender) {
		this.votsSender = votsSender;
	}

	@JsonIgnore
	public Set<Vot> getVotsReceiver() {
		return votsReceiver;
	}

	
	public void setVotsReceiver(Set<Vot> votsReceiver) {
		this.votsReceiver = votsReceiver;
	}

	@JsonIgnore
	public Set<xatMessage> getXats() {
		return xats;
	}
	

	public void setXats(Set<xatMessage> xats) {
		this.xats = xats;
	}

	@JsonIgnore
	public Set<Mort> getMorts() {
		return morts;
	}

	
	public void setMorts(Set<Mort> morts) {
		this.morts = morts;
	}


	public User(String userName, String password, String alias, String pathAvatar) {
		super();
		this.userName = userName;
		this.password = password;
		this.alias = alias;
		this.dataRegistre = new Date();
		this.pathAvatar = pathAvatar;
		this.percentatgeVictories = 0;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getDataRegistre() {
		return dataRegistre;
	}

	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	public double getPercentatgeVictories() {
		return percentatgeVictories;
	}

	public void setPercentatgeVictories(double percentatgeVictories) {
		this.percentatgeVictories = percentatgeVictories;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + "]";
	}

	


	

	


	
	


}