package llops.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import llops.modelo.Partida;
import llops.modelo.Rol;
import llops.modelo.Roljugadorpartida;
import llops.modelo.User;
import llops.modelo.Vot;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;
@Controller    
@RequestMapping(path="/llops/partida")
public class PartidaController {
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
	// unirse
		// http://localhost:9003/llops/partida/unirse?user=Jon&idpartida=1
		@GetMapping(path = "/unirse")
		public @ResponseBody String unirse(@RequestParam String user, @RequestParam int idpartida) {
			Optional<Partida> partidote = partidaRepository.findById(idpartida);

			Optional<User> usuari1 = userRepository.findById(user);

			// return usuario.get().getUserName()+" esta es el usuario";
			// return partida.get().getId()+" esta es la idpartida";

			if (usuari1.get() != null && partidote.get() != null) {

				if (partidote.get().getTorn() < 1) {

					boolean existe = false;
					Set<User> usuarios = new HashSet();
					usuarios = partidote.get().getUsers();

					for (User usuariox : usuarios) {

						if (usuariox.getUserName().equals(usuari1.get().getUserName())) {

							existe = true;
							System.out.println(usuari1.toString());
						}

					}
					if (!existe) {

						usuarios.add(usuari1.get());
						partidote.get().setUsers(usuarios);
						partidaRepository.save(partidote.get());
						System.out.println("Usuario añadido a la partida con exito");
						return "Usuario añadido";
					}

					else {
						return "El usuario ya esta dentro de esta partida";
					}

				}

				else {
					System.out.println("Partida ya empezada");
				}
			}

			else

			{
				System.out.println("Usuario o partida inexistente");

			}
			return "Hola";

		}
		
		// VOTA
		// http://localhost:9003/llops/partida/vota?sender=Jose&receiver=Jon&idpartida=2
		 @GetMapping(path="/vota")
		public @ResponseBody boolean votar(@RequestParam String sender, @RequestParam String receiver,
				@RequestParam int idpartida) {
			Optional<Partida> partida = partidaRepository.findById(idpartida);
			Optional<User> envia = userRepository.findById(sender);
			Optional<User> recibe = userRepository.findById(receiver);
			// Optional<Roljugadorpartida> rol =
			// rolJugadorRepository.findById_partidaAndUserAllIgnoreCase(1, sender);
			// String rolnuevo = rol.get().getRol().getId();
			// System.out.println(rolnuevo);
			// rol.get
			// System.out.println(rol.get().getRol().getId());

			int turnoactual = partida.get().getTorn();
			// int partidaid = partida.get().getId();
			List<Roljugadorpartida> roles = rolJugadorRepository.findJugadorRol(partida.get(), envia.get());
			Rol rolusuari1 = roles.get(0).getRol();
			// TURNO DE NOCHE
			if (partida.get().getTorn() % 2 == 0) {
				// lobo
				if (rolusuari1.getId()==1) {
					Vot voto = new Vot(turnoactual, partida.get(), recibe.get(), envia.get());
					votRepository.save(voto);
					return true;
				}
			}

			// TURNO DE DIA
			else if (envia.isPresent() && recibe.isPresent() && partida.isPresent()) {
				Vot voto = new Vot(turnoactual, partida.get(), recibe.get(), envia.get());
				votRepository.save(voto);
				return true;
			} else
				return false;
			return false;

		}
		

}
