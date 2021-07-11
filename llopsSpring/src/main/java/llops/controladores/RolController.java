package llops.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import llops.modelo.Mort;
import llops.modelo.Partida;
import llops.modelo.Rol;
import llops.modelo.Roljugadorpartida;
import llops.modelo.User;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;
@Controller    
@RequestMapping(path="/llops/rol")
public class RolController {
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
	// http://localhost:9003/llops/rol/descobrir?user=Jon&idpartida=2&elegido=Jose
	@GetMapping(path = "/descobrir")
	public @ResponseBody String descobrirRol(@RequestParam String user, @RequestParam int idpartida,
			@RequestParam String elegido) {
		Optional<Partida> partida = partidaRepository.findById(idpartida);

		Optional<User> usuario = userRepository.findById(user);
		if (usuario.isPresent() && partida.isPresent()) {
			List<Roljugadorpartida> roles = rolJugadorRepository.findJugadorRol(partida.get(), usuario.get());
			// String salid = "Este es el rol de "+usuario.get().getUserName() + "
			// "+usuario.get().getUserRol();
			Rol rolusuari1 = roles.get(0).getRol();
			
			// ES VIDENTE
			if (rolusuari1.getId()==3) {
				Optional<User> elegido2 = userRepository.findById(elegido);
				List<Roljugadorpartida> roles2 = rolJugadorRepository.findJugadorRol(partida.get(), elegido2.get());
				Rol rolusuari2 = roles2.get(0).getRol();
				String salid = "Este es el rol de "+elegido2.get().getUserName() +"   "+ rolusuari2.getDescripcio();
				return salid;
			} else
				return "No es vidente ";
		} else
			return "No hay mensajes o la partida no existe";

	}
	// rolsVius
		// http://localhost:9003/llops/rol/RolsVius?idpartida=1
		@GetMapping(path = "/RolsVius")
		public @ResponseBody String rolsVius(@RequestParam int idpartida) {
			boolean muerto = false;
			Optional<Partida> partida = partidaRepository.findById(idpartida);
			String rolsVius = "";

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
						rolsVius += " Lista de roles: " + user.getUserRol() + "\n ";
					}
				}
			}
			return rolsVius;
		}

}
