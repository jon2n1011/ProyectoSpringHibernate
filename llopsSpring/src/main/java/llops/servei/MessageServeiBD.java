package llops.servei;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import llops.modelo.*;
import llops.repositorio.*;

@Service("MessageServeiBD")
public class MessageServeiBD {
	@Autowired
	private MessageRepository repositori;

}
