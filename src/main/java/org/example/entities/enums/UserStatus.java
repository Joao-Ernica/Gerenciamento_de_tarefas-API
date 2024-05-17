package org.example.entities.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

//	codigo numerico para caso no futuro precise

	LIDER(1),
	VICE_LIDER(2),
	FUNCIONARIO(3),
	ESTAGIARIO(4);

	private final int code;

	private UserStatus(int code) {
		this.code = code;
	}

	public static TaskStatus valueOf(int code) {
		for (TaskStatus value : TaskStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
