package com.devsuperior.userdept.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.userdept.entities.Department;
import com.devsuperior.userdept.entities.User;
import com.devsuperior.userdept.repositories.DepartmentRepository;
import com.devsuperior.userdept.repositories.UserRepositoy;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserRepositoy userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping
	public List<User> findAll(){
		List<User> result = userRepository.findAll();
		return result;
	}

	@GetMapping(value = "/{id}")
	public User findById(@PathVariable Long id){
		User result = userRepository.findById(id).get();
		return result;
	}

	@PostMapping
	public User insert(@RequestBody User user){
		User result = userRepository.save(user);
		return result;
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id){
		userRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public User insertDepartOnUser(@PathVariable Long id, @RequestBody User userDTO){
		Optional<User> optional = userRepository.findById(id);
		User user = optional.get();
		userDTO.setId(id);
		user = userDTO;
		userRepository.save(user);
		return user;
	}

	@PutMapping("/{idUser}/{idDepartment}")
	public User insert(@PathVariable Long idUser, @PathVariable Long idDepartment){
		Optional<User> optionalUser = userRepository.findById(idUser);
		User user = optionalUser.get();
		Optional<Department> optionalDepartment = departmentRepository.findById(idDepartment);
		Department department = optionalDepartment.get();
		user.setDepartment(department);
		return userRepository.save(user);
	}
}
