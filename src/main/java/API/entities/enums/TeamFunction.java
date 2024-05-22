package API.entities.enums;

import lombok.Getter;

@Getter
public enum TeamFunction {

//	codigo numerico para caso no futuro precise

	MARKETING(1),
	DEVELOPER(2),
	ACCOUNTING(3);

	private final int code;

	private TeamFunction(int code) {
		this.code = code;
	}

	public static TeamFunction valueOf(int code) {
		for (TeamFunction value : TeamFunction.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
