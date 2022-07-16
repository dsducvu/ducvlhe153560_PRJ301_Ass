/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Instructor;

/**
 *
 * @author ASUS
 */
public class InstructorDBContext extends DBContext {

    public ArrayList<Instructor> getAllInstructor() {
        ArrayList<Instructor> ilist = new ArrayList<>();
        String sql = "  select * from Instructor";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Instructor i = new Instructor(rs.getInt(1), rs.getString(2), rs.getString(3));
                ilist.add(i);
            }
        } catch (Exception e) {
        }
        return ilist;
    }
}


