package llops.servei;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import llops.modelo.*;
import llops.repositorio.*;

@Service("RolJugadorPartidaServeiBD")
public class RolJugadorPartidaServeiBD implements RolJugadorPartidaServei{
	@Autowired
	private RolJugadorPartidaRepository repositori;
/*
	public Iterable<Roljugadorpartida> findById_partidaAndUser(int idpartida, String user) {
		// TODO Auto-generated method stub
		//return repositori.findByApe1OrderByNombreAsc(cognom);
		return repositori.findById_partidaAndUser(idpartida, user);
		
		//  List<Profesor> findByApe1AndApe2AllIgnoreCase(String ape1, String ape2);

		//
	}
	*/
	@Override
    public List<Roljugadorpartida> getJugador(Partida idpartida, User user) {
        // TODO Auto-generated method stub
        return repositori.findJugadorRol(idpartida, user);
    }

}
