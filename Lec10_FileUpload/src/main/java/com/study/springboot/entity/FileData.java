package com.study.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="FileData")
public class FileData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filedata_seq")
	@SequenceGenerator(name="filedata_seq",sequenceName = "filedata_SEQ", allocationSize = 1)
	private long id;
	
	private String name;
	private String type;
	private String filePath;
	
	@Builder
	public FileData(String name, String type, String filePath) {
		this.name = name;
		this.type = type;
		this.filePath = filePath;
	}

}
