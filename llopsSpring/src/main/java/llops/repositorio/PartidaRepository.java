package llops.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import llops.modelo.Partida;

public interface PartidaRepository extends CrudRepository<Partida, Integer>{

	void save(Optional<Partida> partida);

}
