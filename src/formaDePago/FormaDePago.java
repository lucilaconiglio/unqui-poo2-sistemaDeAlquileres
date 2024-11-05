package formaDePago;

public enum FormaDePago {
    EFECTIVO("Efectivo"),
    DEBITO("Débito"),
    CREDITO("Crédito");

    private final String descripcion;

    FormaDePago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}