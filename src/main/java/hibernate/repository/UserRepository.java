package hibernate.repository;

import hibernate.model.User;
import org.springframework.data.repository.CrudRepository;

/*
T - User
ID - Integer
S - User
 */

public interface UserRepository extends CrudRepository<User,Integer>{

    /*
    List<integer>    tak jak tpyy generyczne moga byc stosowane rowniez w interfejsach
     */
//Crud to wbudowana klasa, ktora ma 11 metod w sobie pomaga nam odnoscis ei do wszystkiego
//ctrl + crudreposityory  by sprawdzic jakie to sa metody




}
