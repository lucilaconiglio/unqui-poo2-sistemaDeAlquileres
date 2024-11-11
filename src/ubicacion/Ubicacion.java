package ubicacion;


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

public String getPais() {
	return pais;
}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
