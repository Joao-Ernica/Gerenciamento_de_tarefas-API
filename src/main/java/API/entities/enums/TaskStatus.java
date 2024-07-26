package API.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {

//	codigo numerico para caso no futuro precise

	DENTRO_DO_PRAZO(1),
	FINALIZADO(2),
	FORA_DO_PRAZO(3),
	CANCELADO(4);

	private final int code;

	public static TaskStatus valueOf(int code) {
		for (TaskStatus value : TaskStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}

