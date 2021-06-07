package com.biblequizappbackend.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private RegisterRepository dao;
	
	@GetMapping()
	public List<Register> getRegister() {
		List<Register> foundRegister = dao.findAll(); 
		return foundRegister; 
	}
	
	@PostMapping()
	public ResponseEntity<Register> postRegister(@RequestBody Register register) {
		Register createdRegister = dao.save(register);
 		
		return ResponseEntity.ok(createdRegister);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Register> updateRegister(@PathVariable(value="id") Long id , @RequestBody Register register) {
		Register foundRegister = dao.findById(id).orElse(null); 
		
		if(foundRegister == null) {
			return ResponseEntity.notFound().header("Register", "Nothing found with that ID").build(); 
		}
		else {
			foundRegister = dao.save(register); 
		}
		return ResponseEntity.ok(foundRegister); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Register> deleteRegister(@PathVariable(value="id") Long id) {
		Register foundRegister = dao.findById(id).orElse(null); 
		
		if(foundRegister ==  null) {
			return ResponseEntity.notFound().header("Register", "Nothing found with that ID").build(); 
		} else {
			dao.delete(foundRegister); 
		}
		
		return ResponseEntity.ok().build(); 
	}
}	
