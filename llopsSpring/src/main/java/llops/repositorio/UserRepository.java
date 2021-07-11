package llops.repositorio;


import org.springframework.data.repository.CrudRepository;

import llops.modelo.User;

//Los repositorios se crean solos.

//hay dos tipos de repositorio, 
//CrudRepository: Create Read(Retrieve) Update Delete

public interface UserRepository extends CrudRepository<User, String> {




}
