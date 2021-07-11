package llops.servei;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import llops.modelo.*;
import llops.repositorio.*;

@Service("VotServeiBD")
public class VotServeiBD implements VotServei {
	@Autowired
	private VotRepository repositori;

	public Iterable<Vot> buscarPartidaYTurno(Partida idpartida, int turno) {
		// TODO Auto-generated method stub
		//return repositori.findByApe1OrderByNombreAsc(cognom);
		return repositori.findByPartidaAndTorn(idpartida, turno);
		
		//  List<Profesor> findByApe1AndApe2AllIgnoreCase(String ape1, String ape2);

		//
	}
	@Override
    public List<Vot> getVota(Partida idpartida, int torn) {
        // TODO Auto-generated method stub
        return repositori.findMax(idpartida, torn);
    }
}
