package tipoDeInmueble;

import lombok.Getter;

@Getter
public class TipoDeInmueble {
	private String concepto;
	
	public TipoDeInmueble(String concepto) {
		this.concepto=concepto;
	}
	
}
