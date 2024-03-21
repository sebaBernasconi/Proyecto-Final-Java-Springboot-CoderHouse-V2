package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;

import java.sql.*;
import java.util.function.Predicate;

public class JavaDataBaseControllerFactura  extends JavaDataBaseController{

    //Al heredar tiene las constantes y el objeto Connection

    //CRUD Factura A. Al ser un documento comercial no se puede actualizar sus datos una vez realizada
    //Por eso no tendra update

    //Read
    public void mostrarFacturasA() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM factura_a;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int nroFactura = resultSet.getInt("nro_factura");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            Date fecha = resultSet.getDate("fecha");
            float total = resultSet.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    public void mostrarFacturasB() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM factura_b;";

        statement =connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int nroFactura = resultSet.getInt("nro_factura");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            Date fecha = resultSet.getDate("fecha");
            float total = resultSet.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    public void mostrarFacturasC() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM factura_c;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            int nroFactura = resultSet.getInt("nro_factura");
            int cuilCliente = resultSet.getInt("cuil_cliente");
            Date fecha = resultSet.getDate("fecha");
            float total = resultSet.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }
    }

    //Create
    public void guardarFacturaA(FacturaA facturaA) throws SQLException{
        PreparedStatement statement = null;

        String query = "INSERT INTO factura_a(nro_factura,cuil_cliente,fecha,total)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,facturaA.getNroFactura());
        statement.setInt(2,facturaA.getCuilCliente());
        statement.setDate(3, Date.valueOf(facturaA.getFecha()));
        statement.setFloat(4,facturaA.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura A con nro " + facturaA.getNroFactura() +
                    " guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    public void guardarFacturaB(FacturaB facturaB) throws SQLException{
        PreparedStatement statement = null;

        String query = "INSERT INTO factura_b(nro_factura,cuil_cliente,fecha,total)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,facturaB.getNroFactura());
        statement.setInt(2,facturaB.getCuilCliente());
        statement.setDate(3, Date.valueOf(facturaB.getFecha()));
        statement.setFloat(4,facturaB.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura A con nro " + facturaB.getNroFactura() +
                    " guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    public void guardarFacturaC(FacturaC facturaC) throws SQLException{
        PreparedStatement statement = null;

        String query = "INSERT INTO factura_c(nro_factura,cuil_cliente,fecha,total)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,facturaC.getNroFactura());
        statement.setInt(2,facturaC.getCuilCliente());
        statement.setDate(3, Date.valueOf(facturaC.getFecha()));
        statement.setFloat(4,facturaC.getTotal());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura A con nro " + facturaC.getNroFactura() +
                    " guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Delete
    public void borrarFacturaA(int nroFactura) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM factura_a WHERE nro_factura = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nroFactura);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura A con numero " + nroFactura +
                    " eliminada con exito");
        }

        if (statement != null){
            statement.close();
        }
    }

    public void borrarFacturaB(int nroFactura) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM factura_b WHERE nro_factura = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nroFactura);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura B con numero " + nroFactura +
                    " eliminada con exito");
        }

        if (statement != null){
            statement.close();
        }
    }

    public void borrarFacturaC(int nroFactura) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM factura_c WHERE nro_factura = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nroFactura);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println();
            System.out.println("Factura C con numero " + nroFactura +
                    " eliminada con exito");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Mostrar facturas de un cliente
    public void mostrarFacturasDeUnCliente(int cuil) throws SQLException {
        PreparedStatement statementFacturaA = null;
        PreparedStatement statementFacturaB = null;
        PreparedStatement statementFacturaC = null;

        ResultSet resultSetFacturaA = null;
        ResultSet resultSetFacturaB = null;
        ResultSet resultSetFacturaC = null;

        String queryFacturaA = "SELECT * FROM factura_a WHERE cuil_cliente = ?;";
        String queryFacturaB = "SELECT * FROM factura_b WHERE cuil_cliente = ?;";
        String queryFacturaC = "SELECT * FROM factura_c WHERE cuil_cliente = ?;";

        statementFacturaA = connection.prepareStatement(queryFacturaA);
        statementFacturaB = connection.prepareStatement(queryFacturaB);
        statementFacturaC = connection.prepareStatement(queryFacturaC);

        statementFacturaA.setInt(1,cuil);
        statementFacturaB.setInt(1,cuil);
        statementFacturaC.setInt(1,cuil);

        resultSetFacturaA = statementFacturaA.executeQuery();
        resultSetFacturaB = statementFacturaB.executeQuery();
        resultSetFacturaC = statementFacturaC.executeQuery();

        System.out.println("Facturas A");
        while (resultSetFacturaA.next()){
            int nroFactura = resultSetFacturaA.getInt("nro_factura");
            int cuilCliente = resultSetFacturaA.getInt("cuil_cliente");
            Date fecha = resultSetFacturaA.getDate("fecha");
            float total = resultSetFacturaA.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        System.out.println("Facturas B");
        while (resultSetFacturaB.next()){
            int nroFactura = resultSetFacturaB.getInt("nro_factura");
            int cuilCliente = resultSetFacturaB.getInt("cuil_cliente");
            Date fecha = resultSetFacturaB.getDate("fecha");
            float total = resultSetFacturaB.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        System.out.println("Facturas C");
        while (resultSetFacturaC.next()){
            int nroFactura = resultSetFacturaC.getInt("nro_factura");
            int cuilCliente = resultSetFacturaC.getInt("cuil_cliente");
            Date fecha = resultSetFacturaC.getDate("fecha");
            float total = resultSetFacturaC.getFloat("total");

            System.out.println("---------------------------------------");
            System.out.println("numero: " + nroFactura);
            System.out.println("cuil: " + cuilCliente);
            System.out.println("fecha" + fecha.toString());
            System.out.println("total: " + total);
            System.out.println("---------------------------------------");
        }

        if (resultSetFacturaA != null||
                resultSetFacturaB != null|| resultSetFacturaC != null){
            resultSetFacturaA.close();
            resultSetFacturaB.close();
            resultSetFacturaC.close();
        }

        if (statementFacturaA != null || statementFacturaB != null || statementFacturaC != null){
            statementFacturaA.close();
            statementFacturaB.close();
            statementFacturaC.close();
        }


    }
}
