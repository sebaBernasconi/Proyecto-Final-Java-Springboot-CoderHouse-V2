package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.product.Producto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Schema(description = "Modelo del Carrito")
@Entity
@Table(name = "carrito")
public class Carrito {

    @Schema(description = "Id autogenerado del Carrito")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCarrito;

    @Schema(description = "Cliente asociado al Carrito", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuil")
    private Client cliente;

    @Schema(description = "Cantidad de articulos en el Carrito", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")

    @Column(name = "cantidad_de_articulos")
    private int cantidadDeArticulos;

    @Schema(description = "Listado de Productos en el Carrito", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carrito_producto",
     joinColumns = @JoinColumn(name = "id_carrito"),
    inverseJoinColumns = @JoinColumn(name ="id_producto"))
    private List<Producto> productos;

    @Schema(description = "Estado del Carrito", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @Column(name = "pagado")
    private boolean pagado;

    @Schema(description = "Precio final del Carrito", requiredMode = Schema.RequiredMode.REQUIRED, example = "2345.894")
    @Column(name = "total")
    private float total;

    //Constructor

    public Carrito(Client cliente, int cantidadDeArticulos,
                   List<Producto> productos, boolean pagado, float total) {
        this.cliente = cliente;
        this.cantidadDeArticulos = cantidadDeArticulos;
        this.productos = productos;
        this.pagado = pagado;
        this.total = total;
    }

    public Carrito() {

    }

    //Metodos de la clase
    public void pagarCarrito(){

        this.pagado = true;
    }

    public void agregarAlCarrito(Producto p){
        this.productos.add(p);
        this.cantidadDeArticulos ++;
        this.total += p.getPrecio();

        p.actualizarStock();

        System.out.println("Producto: " + p.getNombre() +
                " agregado!");
        System.out.println("Nuevo total: " + this.total);
    }

    public boolean estaEnElCarrito(int codigoDeProducto){
        for (Producto p :
                productos) {
            if (p.getCodigoDeProducto() == codigoDeProducto) {
                return true;
            }
        }
        return false;
    }

    public void sacarDelCarrito(Producto p){
        this.productos.remove(p.getIdProd());
        this.total -= p.getPrecio();
        System.out.println("Producto: " + p.getNombre() +
                " eliminado del carrito!");
        System.out.println("Nuevo total: " + this.total);
    }

    public void verProductosEnElCarrito(){
        System.out.println("Id  |   Nombre  |   precio");
        for (Producto p :
                this.productos) {
            System.out.println(p.getIdProd() +
                    p.getNombre() + p.getPrecio());
        }
    }
    //Getters y Setters

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public int getCantidadDeArticulos() {
        return cantidadDeArticulos;
    }

    public void setCantidadDeArticulos(int cantidadDeArticulos) {
        this.cantidadDeArticulos = cantidadDeArticulos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", cliente=" + cliente +
                ", cantidadDeArticulos=" + cantidadDeArticulos +
                ", productos=" + productos +
                ", pagado=" + pagado +
                ", total=" + total +
                '}';
    }
}
