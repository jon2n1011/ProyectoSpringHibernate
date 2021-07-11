package llops.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import llops.modelo.Mort;
import llops.modelo.Partida;



public interface MortRepository extends CrudRepository<Mort, Integer>{

	Iterable<Mort> findByPartidaAndTorn(Partida idpartida, int turno);

	
}
