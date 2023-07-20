package fr.idformation.evaluation.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.idformation.evaluation.security.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Search a user using it's username.
	 *
	 * @param username the user name to find the user with
	 * @return the corresponding user
	 */
	Optional<User> findByUsername(String username);

}
