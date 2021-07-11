package llops.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import llops.modelo.Mort;
import llops.modelo.Partida;
import llops.modelo.xatMessage;



public interface XatMessageRepository extends CrudRepository<xatMessage, Integer>{

	
}
