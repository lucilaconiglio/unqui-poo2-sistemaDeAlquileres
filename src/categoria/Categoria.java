package categoria;

import lombok.Getter;

@Getter
public class Categoria {
	private String concepto;
	
	public Categoria(String concepto) {
		this.concepto=concepto;
	}
	
}
