package llops.servei;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import llops.modelo.*;
import llops.repositorio.*;


@Service("XatServeiBD")
public class XatMessageServeiBD {
	@Autowired
	private XatMessageRepository repositori;
	public Iterable<xatMessage> buscarPartidaYTurno(Partida idpartida, int turno) {
		
		return null;
		// TODO Auto-generated method stub
		//return repositori.findByApe1OrderByNombreAsc(cognom);
		
		//  List<Profesor> findByApe1AndApe2AllIgnoreCase(String ape1, String ape2);

		//
	}
}
