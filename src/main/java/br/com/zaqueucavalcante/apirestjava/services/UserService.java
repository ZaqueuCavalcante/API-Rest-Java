package br.com.zaqueucavalcante.apirestjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.zaqueucavalcante.apirestjava.dtos.UserDTO;
import br.com.zaqueucavalcante.apirestjava.entities.User;
import br.com.zaqueucavalcante.apirestjava.repositories.UserRepository;
import br.com.zaqueucavalcante.apirestjava.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException("User not found."));
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public User insert(User user) {
		return userRepository.save(user);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public void delete(String id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("User not found. id: " + id);
		}
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public User update(String id, User updatedUser) {
		try {
			User user = userRepository.findById(id).get();
			updateUser(user, updatedUser);
			return userRepository.save(user);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateUser(User user, User updatedUser) {
		user.editName(updatedUser.getName());
		user.editEmail(updatedUser.getEmail());
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public User fromDTO(UserDTO userDTO) {
		String id = userDTO.getId();
		String name = userDTO.getName();
		String email = userDTO.getEmail();
		return new User(id, name, email);
	}
}
