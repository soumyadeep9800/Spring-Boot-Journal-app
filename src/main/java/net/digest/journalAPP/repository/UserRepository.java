package net.digest.journalAPP.repository;


import net.digest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
//      Spring sees:
//      findBy... ➜ So it knows you’re searching.
//      Username ➜ So it maps to the username field in the User class.
//      This is called a Derived Query Method — Spring generates the query automatically based on the method name.
      User findByUsername(String username);
      User deleteByUsername(String username);

}
