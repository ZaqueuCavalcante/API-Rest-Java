package br.com.zaqueucavalcante.apirestjava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zaqueucavalcante.apirestjava.entities.User;
import br.com.zaqueucavalcante.apirestjava.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public List<User> findAll() {
		return userRepository.findAll();
	}

//	public User findById(Long id) {
//		Optional<User> entity = userRepository.findById(id);
//		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	public User insert(User user) {
//		return userRepository.save(user);
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	public void delete(Long id) {
//		try {
//			userRepository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException(id);
//		} catch (DataIntegrityViolationException e) {
//			throw new DatabaseException(e.getMessage());
//		}
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	public User update(Long id, User updatedUser) {
//		try {
//			User user = userRepository.getOne(id);
//			updateUser(user, updatedUser);
//			return userRepository.save(user);
//		} catch (EntityNotFoundException e) {
//			throw new ResourceNotFoundException(id);
//		}
//	}
//	
//	private void updateUser(User user, User updatedUser) {
//		user.setName(updatedUser.getName());
//		user.setEmail(updatedUser.getEmail());
//		user.setPhone(updatedUser.getPhone());
//	}
}
