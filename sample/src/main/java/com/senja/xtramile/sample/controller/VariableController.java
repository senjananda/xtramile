package com.senja.xtramile.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senja.xtramile.sample.entity.Variable;
import com.senja.xtramile.sample.repository.VariableRepository;

@RestController
@RequestMapping("/variable")
public class VariableController {
	@Autowired
	VariableRepository variableRepository;
	
	@GetMapping("/getVariables")
	public ResponseEntity<List<Variable>> getVariables(@RequestParam(required = false) String varName){
		try {
		      List<Variable> variables = new ArrayList<Variable>();
		      if(varName == null){
		    	  variableRepository.findAll().forEach(variables::add);  
		      }else{
		    	  variableRepository.getVarValueByVarName(varName).forEach(variables::add);
		      }
		      

		      if (variables.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(variables, HttpStatus.OK);
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@GetMapping("/getByName/{varName}")
	public ResponseEntity<List<Variable>> getParametersByName(@PathVariable("varName") String varName){
		List<Variable> variables = new ArrayList<Variable>();
		if(varName == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}else{
			variableRepository.getVarValueByVarName(varName).forEach(variables::add);
			return new ResponseEntity<>(variables, HttpStatus.OK);
		}
	}
	
	@GetMapping("/getById/{varId}")
	public ResponseEntity<Variable> getProvinceById(@PathVariable("varId") String varId){
		Optional<Variable> variable = variableRepository.findById(varId);
		if(variable.isPresent()){
			return new ResponseEntity<>(variable.get(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/addNew")
	public ResponseEntity<Variable> postParameter(@RequestBody Variable data){
		try{
			Variable variable = variableRepository
					.save(new Variable(data.getVarName(), 
										data.getVarValue(), 
										data.getVarDescription(), 
										data.getParentId()));
			return new ResponseEntity<>(variable, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/variable/{varId}")
	public ResponseEntity<Variable> updateParameter(@PathVariable("varId") String varId, @RequestBody Variable data){
		Optional<Variable> variable = variableRepository.findById(varId);
		
		if(variable.isPresent()){
			Variable globalVarData = variable.get();
			globalVarData.setVarName(data.getVarName());
			globalVarData.setVarValue(data.getVarValue());
			globalVarData.setVarDescription(data.getVarDescription());
			return new ResponseEntity<>(variableRepository.save(globalVarData), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{varId}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("varId") String id){
		try{
			variableRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

