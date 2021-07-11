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
import llops.modelo.Vot;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/llops/vot") // This means URL's start with /demo (after Application path)
public class VotsController {
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
	
	// fitorn
	// http://localhost:9003/llops/vot/fitorn?idpartida=2
		@GetMapping(path = "/fitorn")
		public @ResponseBody String fiTorn(@RequestParam int idpartida) {
			Optional<Partida> partidote = partidaRepository.findById(idpartida);

			// Cogemos la partida y miramos si existe y esta empeazada
			if (partidote.get() != null && partidote.get().getTorn() > 0) {

				// Consulta creada a mano, con esta consulta sacaremos al mas votado
				List<Vot> vots = votRepository.findMax(partidote.get(), partidote.get().getTorn());
				// Si la lista que returna es mayor que una significara que hay un jugador mas
				// votado, si no no hay votos
				if (vots.size() > 0) {

					Vot votox = vots.get(0);
					Mort mort = new Mort();
					mort.setTorn(partidote.get().getTorn());
					mort.setPartida(partidote.get());
					mort.setUser(votox.getReceiver());
					mortRepository.save(mort);
					partidote.get().setTorn(partidote.get().getTorn() + 1);
					partidaRepository.save(partidote.get());

					return "Ha muerto el usuario " + votox.getReceiver().toString();

				}

				else {

					return "No hay votos";
				}

			}
			return null;
		}
		// GETHISTORIAL() ACABADO
		// http://localhost:9003/llops/vot/veureHistorial?idpartida=2&turno=3
		@GetMapping(path = "/veureHistorial")
		public @ResponseBody String veureHistorial(@RequestParam Partida idpartida, @RequestParam int turno) {
			Iterable<Vot> votos = votRepository.findByPartidaAndTorn(idpartida, turno);
			// los turnos por la noche no son publicos al ser de lobos
			int contador = 0;
			for (Vot voto : votos) {
				contador++;
				// salida += "Numero de votos en el turno :" +turno+voto.getId();
			}
			if (turno % 2 != 0) {
				String salida = "Registro de votos en el turno " + turno + " es :" + contador;
				return salida;
			} else
				return "No son publicos los votos por la noche";

		}
}
