package org.example.entities.enums;

import lombok.Getter;

@Getter
public enum UserFunction {

//	codigo numerico para caso no futuro precise

	LIDER(1),
	VICE_LIDER(2),
	FUNCIONARIO(3),
	ESTAGIARIO(4);

	private final int code;

	private UserFunction(int code) {
		this.code = code;
	}

	public static UserFunction valueOf(int code) {
		for (UserFunction value : UserFunction.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid UserFunction code");
	}
}
