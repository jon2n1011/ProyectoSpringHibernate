package llops.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import llops.modelo.Message;





//Los repositorios se crean solos.

//hay dos tipos de repositorio, 
//CrudRepository: Create Read(Retrieve) Update Delete

public interface MessageRepository extends CrudRepository<Message, String> {

	
	  //List<Message> findByApe1AndApe2AllIgnoreCase(String ape1, String ape2);

	  List<Message> findByReceiverOrSender(String receiver, String sender);
	

}
