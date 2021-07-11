package llops.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import llops.modelo.Partida;
import llops.modelo.Rol;
import llops.modelo.Roljugadorpartida;
import llops.modelo.User;
import llops.modelo.Vot;

public interface RolJugadorPartidaRepository extends CrudRepository<Roljugadorpartida, Integer>{

	//Optional<Roljugadorpartida> findById_partidaAndUser(int idpartida, String sender);
	//Optional<Roljugadorpartida> findById_partidaAndUserAllIgnoreCase(int idpartida, String sender);
	@Query("SELECT r  FROM Roljugadorpartida r where id_partida=?1 AND user LIKE ?2 ")
    List<Roljugadorpartida> findJugadorRol(Partida idpartida,User user);
	

}
