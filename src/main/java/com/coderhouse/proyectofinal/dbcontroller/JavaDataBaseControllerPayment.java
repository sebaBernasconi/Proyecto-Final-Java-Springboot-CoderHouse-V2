package com.coderhouse.proyectofinal.dbcontroller;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Client;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDataBaseControllerPayment extends JavaDataBaseController {

    //Al heredar tiene las constantes y el objeto Connection

    //CRUD tarjetas de debito

    //Read. Deberia mostrarse(?
    public void mostrarTarjetasDebito() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM t_debito;";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int numeroTarjeta = resultSet.getInt("nro_tarjeta");
            String titular = resultSet.getString("nombre_titular");
            float saldo = resultSet.getFloat("saldo");

            System.out.println("La tarjeta con nro " + numeroTarjeta + " pertenece a " +
                    titular + " y tiene un saldo de " + saldo + " pesos");
        }

        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

    }

    //Create
    public void guardarTarjetaDebitoCliente(Client client) throws SQLException {
        PreparedStatement statement = null;

        String query ="INSERT INTO t_debito(nro_tarjeta,cuil_titular,nombre_titular,saldo)" +
                "       VALUES(?,?,?,?);";

        statement = connection.prepareStatement(query);
        statement.setInt(1,client.gettDebito().getNroTarjeta());
        statement.setInt(2,client.getCuil());
        statement.setString(3,client.getNombre());
        statement.setFloat(4,client.gettDebito().getSaldo());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Tarjeta con nro " + client.gettDebito().getNroTarjeta() +
                    " guardada en la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }

    //Update
    public void actualizarSaldoTarjeta(int nroTarjeta, float nuevoSaldo) throws SQLException {
        PreparedStatement statement = null;

        String query = "UPDATE t_debito SET saldo = ? WHERE nro_tarjeta = ?;";

        statement = connection.prepareStatement(query);
        statement.setFloat(1,nuevoSaldo);
        statement.setInt(2,nroTarjeta);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Saldo de la tarjeta con nro: " + nroTarjeta + " actualizado.\n" +
                    "Nuevo saldo: " + nuevoSaldo);
        }

        if (statement != null){
            statement.close();
        }
    }

    //Delete
    public void borrarTarjeta(int nroTarjeta) throws SQLException {
        PreparedStatement statement = null;

        String query = "DELETE FROM t_debito WHERE nro_tarjeta = ?;";

        statement = connection.prepareStatement(query);
        statement.setInt(1,nroTarjeta);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            System.out.println("Tarjeta con nro: " + nroTarjeta + " eliminada de la base de datos");
        }

        if (statement != null){
            statement.close();
        }
    }
}
