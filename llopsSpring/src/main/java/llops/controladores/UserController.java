package llops.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import llops.controladores.*;
import llops.servei.*;
import llops.repositorio.*;
import llops.modelo.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/llops/user") // This means URL's start with /demo (after Application path)
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private PartidaRepository partidaRepository;
	@Autowired
	private MortRepository mortRepository;
	@Autowired
	private VotRepository votRepository;
	@Autowired
	private XatMessageRepository xatMessageRepository;
	@Autowired
	private RolJugadorPartidaRepository rolJugadorRepository;

	@GetMapping(path = "/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {
		// metodo findById del repositorio. Devuelve opcional (puede ser null o no)
		Optional<User> user = userRepository.findById(id);
		// devuelve el valor del opcional
		if (user.isPresent())
			return user.get();
		else {
			return null;
		}
	}

	@GetMapping(path = "/countUsers")
	public @ResponseBody long countUsers() {

		return userRepository.count();
	}

	// register
	// http://localhost:9003/llops/user/register?name=Eloy&pw=super3&alias=eloy&pathAvatar=img
	@GetMapping(path = "/register")
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String pw,
			@RequestParam String alias, @RequestParam String pathAvatar) {

		User n = new User(name, pw, alias, pathAvatar);
		long bef = userRepository.count();
		userRepository.save(n);
		long aft = userRepository.count();
		if (bef + 1 == aft)
			return "OK";
		else
			return "Ya existe ese usuario.";

	}

	// login
	// http://localhost:9003/llops/user/login?user=Eloy&pw=super3
	@GetMapping(path = "/login")
	public @ResponseBody boolean login(@RequestParam String user, @RequestParam String pw) {
		boolean b;
		
		Optional<User> u = userRepository.findById(user);
		if (u.isPresent()) {
			b = (u.get().getPassword().equals(pw) ? true : false);
			return b;
		} else {
			return false;
		}

	}

	//http://localhost:9003/llops/user/inici?idpartida=21
	@GetMapping(path = "/inici")
	public @ResponseBody boolean inici(@RequestParam int idpartida) {

		Optional<Partida> partidote = partidaRepository.findById(idpartida);		
				if (partidote.isPresent() && partidote.get().getTorn() == 0) {
					// Set de los usuarios que recorreremos despues
					Set<User> usuarios = new HashSet();
					usuarios = partidote.get().getUsers();
					Random random = new Random();
					// Sacar la frequencia de los lobos de la partida
					int llops = usuarios.size() / 4;
					ArrayList<Integer> randomsllops = new ArrayList();
					int i = 0;
					// Generamos numeros randoms.
					while (i < llops) {
						int rd = random.nextInt(usuarios.size());
						if (!randomsllops.contains(random)) {

							randomsllops.add(rd);
							i++;
						}

					}
					// Generamos el numero random del vidente
					i = 0;
					int videnternd = 0;
					while (i < 1) {
						videnternd = random.nextInt(usuarios.size());
						if (!randomsllops.contains(videnternd)) {
							i++;
						}

					}

					// Ahora iniciaremos la partida, cogemos el set de jugadores dentro de esta
					// partida y lo recorremos

					i = 0;

					// Recorremos el set incrementando la i, para ir poniendo los roles
					
					for (User usuario : usuarios) {
						// Cogemos los 3 roles, vidente, llop y vilatans
					
						Optional<Rol> rol1 = rolRepository.findById(1); // Llops
						Optional<Rol> rol2 = rolRepository.findById(2); // Vilatans
						Optional<Rol> rol3 =  rolRepository.findById(3); // Vident
						
						Roljugadorpartida roljugador = new Roljugadorpartida(usuario, null, partidote.get(), true);
						if (randomsllops.contains(i)) {

							roljugador.setRol(rol1.get());
						} else if (i == videnternd) {
							roljugador.setRol(rol3.get());
						}

						else {
							roljugador.setRol(rol2.get());
						}
						
						rolJugadorRepository.save(roljugador);
						i++;
					}
					// Ponemos el turno de la partida a uno
					partidote.get().setTorn(1);
					partidaRepository.save(partidote.get());
					return true;
				} else {
					return false;
				}
				
	}



	


	// jugadorsVius ACABADO
	// http://localhost:9003/llops/user/JugadorsVius?idpartida=1
	@GetMapping(path = "/JugadorsVius")
	public @ResponseBody String jugadorsVius(@RequestParam int idpartida) {
		boolean muerto = false;
		boolean repetir=false;
		Optional<Partida> partida = partidaRepository.findById(idpartida);
		String jugadorsVius = "";

		if (partida.isPresent()) {
			for (User user : partida.get().getUsers()) {
				for (Mort mort : mortRepository.findAll()) {
					if (partida.get().getId() == idpartida) {
						if (mort.getUser().equals(user)) {
							muerto = true;
						}
					}
				}
				if (!muerto) {
					jugadorsVius += " Usuario: " + user.getUserName() + " ";
					
				}
				muerto = false;
			}
		

		}
		return jugadorsVius;
		

	}

	
	

	

	
}
