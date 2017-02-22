package com.auklabs.assistlane.dto.models;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1149679584781161718L;

	private Long id;

	private String name;

	private Boolean isActive;
}
