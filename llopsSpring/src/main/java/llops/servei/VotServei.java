package llops.servei;

import java.util.List;

import llops.modelo.Partida;
import llops.modelo.Vot;

public interface VotServei {
	List<Vot> getVota(Partida idpartida,int torn);
}
