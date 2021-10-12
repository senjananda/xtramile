package com.senja.xtramile.sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senja.xtramile.sample.entity.Variable;

public interface VariableRepository extends JpaRepository<Variable, String>{
	List<Variable> getVarValueByVarName(String varName);
}
