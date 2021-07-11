package llops.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import llops.modelo.Partida;
import llops.modelo.Vot;

public interface VotRepository  extends CrudRepository<Vot, Integer> {
	
	Iterable<Vot> findByPartidaAndTorn(Partida idpartida, int turno);
	@Query("SELECT r  FROM Vot r where partida=?1 AND torn=?2 group by partida,receiver ORDER BY count(receiver) desc")
    List<Vot> findMax(Partida idpartida,int torn);

}
