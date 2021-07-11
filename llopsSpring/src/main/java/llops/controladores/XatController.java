package llops.controladores;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import llops.modelo.xatMessage;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;
@Controller // This means that this class is a Controller
@RequestMapping(path = "/llops/xat") // This means URL's start with /demo (after Application path)
public class XatController {
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
	
	// GETXAT
		// http://localhost:9003/llops/xat/getXat?idpartida=1
		@GetMapping(path = "/getXat")
		public @ResponseBody String getXat(@RequestParam int idpartida) {
			String salida = " ";
			Optional<Partida> partida = partidaRepository.findById(idpartida);

			if (partida.isPresent()) {
				for (xatMessage mensaje : xatMessageRepository.findAll()) {
					if (mensaje.getPartida().getId() == idpartida) {
						salida += " Contenido:" + mensaje.getContent() + " Emisor: " + mensaje.getSender();
					}
				}
				return salida;
			}
			return "No existe la partida";

		}

		// escriureMissatgeXat
		// http://localhost:9003/llops/xat/escriureMissatgeXat?user=Jose&idpartida=2&content=escribo
		@GetMapping(path = "/escriureMissatgeXat")
		public @ResponseBody boolean escriuMissatgeXat(@RequestParam String user, @RequestParam int idpartida,
				@RequestParam String content) {

			Optional<Partida> partida = partidaRepository.findById(idpartida);
			Optional<User> usuario = userRepository.findById(user);
			
			if (partida.isPresent() && usuario.isPresent()) {
				if (partida.get().getTorn() % 2 != 0) {
					xatMessage mensaje = new xatMessage(usuario.get(), partida.get(), content);
					xatMessageRepository.save(mensaje);
					return true;
				}
			}
			System.out.println("No es de dia o no existe la partida / usuario");
			return false;
		}

		

		//http://localhost:9003/llops/xat/getXatLLops?idpartida=21&user=Jugador 4
        @GetMapping(path = "/getXatLLops")
        public @ResponseBody String veureXatLlops(@RequestParam int idpartida, @RequestParam String user) {
            String salida = " ";
            Optional<Partida> partida = partidaRepository.findById(idpartida);

            Optional<User> usuario = userRepository.findById(user);
            if (usuario.isPresent() && partida.isPresent()) {
                List<Roljugadorpartida> roles = rolJugadorRepository.findJugadorRol(partida.get(), usuario.get());
                // String salid = "Este es el rol de "+usuario.get().getUserName() + "
                // "+usuario.get().getUserRol();
                Rol rolusuari1 = roles.get(0).getRol();

                System.out.println(rolusuari1.getId());
                // COMPROBACION DE SI ES LOBO
                if (rolusuari1.getId()==1) {
                    for (xatMessage mensaje : xatMessageRepository.findAll()) {
                        if (mensaje.getPartida().getId() == idpartida) {
                            // Ahora miraremos los usuarios que son lobos
                            Optional<User>usuario2=userRepository.findById(mensaje.getSender().getUserName());
                            roles = rolJugadorRepository.findJugadorRol(partida.get(), usuario2.get());
                            Rol rolusuari2 = roles.get(0).getRol();
                            // Si es lobo lo mostrara por pantalla
                            if (rolusuari2.getId()==1) {
                            salida += " Contenido:" + mensaje.getContent() + " Emisor: " + mensaje.getSender()+"\r\n";
                            }
                        }
                    }
                } else
                    return "No es lobo ";
            } else
                return "No hay mensajes o la partida no existe";
            return salida;
        }

		// EscriuMissatgeLlop
		// http://localhost:9003/llops/xat/escriuLlop?idpartida=1&user=Jose&content=soyunlobo
		@GetMapping(path = "/escriuLlop")
		public @ResponseBody boolean escriureLlop(@RequestParam int idpartida, @RequestParam String user,
				@RequestParam String content) {
			String salida = " ";
			Optional<Partida> partida = partidaRepository.findById(idpartida);

			Optional<User> usuario = userRepository.findById(user);
			if (usuario.isPresent() && partida.isPresent()) {
				List<Roljugadorpartida> roles = rolJugadorRepository.findJugadorRol(partida.get(), usuario.get());
				// String salid = "Este es el rol de "+usuario.get().getUserName() + "
				// "+usuario.get().getUserRol();
				Rol rolusuari1 = roles.get(0).getRol();

				System.out.println(rolusuari1.getId());
				// COMPROBACION DE SI ES LOBO
				if (rolusuari1.getId()==1 && partida.get().getTorn() % 2 == 0) {
					xatMessage mensaje = new xatMessage(usuario.get(), partida.get(), content);
					xatMessageRepository.save(mensaje);
					return true;
				} else
					return false;
			} else
				return false;
		}
}
