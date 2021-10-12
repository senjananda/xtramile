package com.senja.xtramile.sample.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.*;

@Entity
@Table(name="global_var", schema="system")
@Getter
@Setter
public class Variable {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@Column(name = "var_id")
	private String varId;
	
	@Column(name = "var_name")
	private String varName;
	
	@Column(name = "var_value")
	private String varValue;
	
	@Column(name = "var_description")
	private String varDescription;
	
	@Column(name = "parent_id")
	private String parentId;
	
	public Variable(){
		
	}
	
	@Override
	public String toString(){
		return "Variable [varId=" +varId+", varName=" + varName + ", varValue="+varValue+", varDescription=" + varDescription+"parentId="+parentId+"]";
		
	}

	public Variable(String varName, String varValue, String varDescription, String parentId){
		this.varName = varName;
		this.varValue = varValue;
		this.varDescription = varDescription;
		this.parentId = parentId;
	}
}