package llops.servei;

import java.util.List;

import llops.modelo.Partida;
import llops.modelo.Roljugadorpartida;
import llops.modelo.User;

public interface RolJugadorPartidaServei {
    public List<Roljugadorpartida> getJugador(Partida idpartida, User user);

}
