package llops.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import llops.modelo.Mort;
import llops.modelo.Partida;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;

@Controller    
@RequestMapping(path="/llops/mort")
public class MortController {
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
	
	// GETMORTS() ACABADO
		// http://localhost:9003/llops/mort/veureMorts?idpartida=2&turno=2
		@GetMapping(path = "/veureMorts")
		public @ResponseBody String veureMorts(@RequestParam Partida idpartida, @RequestParam int turno) {
			Iterable<Mort> muertos = mortRepository.findByPartidaAndTorn(idpartida, turno);
			String salida = "Usuarios muertos: ";
			for (Mort muerto : muertos) {
				salida += muerto.getUser().getUserName() + " ";
			}
			return salida;

		}
}
