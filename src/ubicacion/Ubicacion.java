package ubicacion;

import lombok.Getter;

@Getter
public class Ubicacion {
	private String pais;
	private String ciudad;
	private String direccion;

	public Ubicacion(String pais, String ciudad, String direccion) {
		super();
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}

}
