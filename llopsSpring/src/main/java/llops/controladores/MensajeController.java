package llops.controladores;

import static llops.modelo.tipus.text;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import llops.modelo.Message;
import llops.modelo.User;
import llops.modelo.tipus;
import llops.repositorio.MessageRepository;
import llops.repositorio.MortRepository;
import llops.repositorio.PartidaRepository;
import llops.repositorio.RolJugadorPartidaRepository;
import llops.repositorio.RolRepository;
import llops.repositorio.UserRepository;
import llops.repositorio.VotRepository;
import llops.repositorio.XatMessageRepository;

@Controller    
@RequestMapping(path="/llops/mensaje")
public class MensajeController {
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
	
	//envia 
	//http://localhost:9003/llops/mensaje/envia?sender=Jose&receiver=Jon&content=felizano&type=text
	@GetMapping(path="/envia")
	public @ResponseBody boolean enviaMissatge( @RequestParam String sender, @RequestParam String receiver,
			@RequestParam String content, @RequestParam tipus type ) {
		boolean b;
		Optional<User> envia = userRepository.findById(sender);
		Optional<User> recibe = userRepository.findById(receiver);
		Date data = new Date();
		if(envia.isPresent()&&recibe.isPresent()) {
			Message mensaje = new Message();
			
			
			mensaje.setContent(content);
			mensaje.setSender(envia.get());
			mensaje.setDate(data);
			mensaje.setReceiver(recibe.get());
			messageRepository.save(mensaje);
			return true;
		}
		else
			return false;
	
	}
	
	//veureMissatges
		//http://localhost:9003/llops/mensaje/veureMissatges?user=Jon
		@GetMapping(path="/veureMissatges")
		public @ResponseBody String veureMissatges(@RequestParam String user) {
			
			Optional<User> usuario = userRepository.findById(user);
			if (usuario.isPresent()) {
				
				String salid = "Mensajes enviados :";
				for (Message mensaje : usuario.get().getMessagesSender()) {
					salid+= mensaje.getContent()+" ";
					
				}
				salid+="Mensajes recibidos :";
				for (Message mensaje : usuario.get().getMessagesReceiver()) {
					salid+= mensaje.getContent()+" ";
				}
				return salid;
			}
			return "No hay mensajes";

		}
}
