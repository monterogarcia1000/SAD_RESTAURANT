package com.iesemilidarder.restaurants.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

//Lectura de los restaurantes guardados en la base de datos

public class ConexionBaseDeDatos {

    public ArrayList readRestaurant(){

        ArrayList rst = new ArrayList();

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@35.205.41.45:1521:XE", "usuari","usuari");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM (SELECT RE.RES_NOM, RE.RES_ADRECA, RE.RES_WEB, RE.RES_TELEFON, RR.TRS_DESCRIPCIO FROM RESTAURANTS RE, TRESTAURANTS RR WHERE RE.RES_TRS_CODI = RR.TRS_CODI ORDER BY RES_MITJANA DESC)where ROWNUM <= 5");

            while (rs.next()) {

                Restaurant rstt = new Restaurant();

                rstt.setNombre(rs.getString("RES_NOM"));
                rstt.setDireccion(rs.getString("RES_ADRECA"));
                rstt.setWeb(rs.getString("RES_WEB"));
                rstt.setTelefono(rs.getString("RES_TELEFON"));
                rstt.setTipo(rs.getString("TRS_DESCRIPCIO"));

                rst.add(rstt);

            }

            stmt.close();
            con.close();

        } catch (Exception e) {

            System.out.println(e.toString());

        }

        return rst;

    }

}

