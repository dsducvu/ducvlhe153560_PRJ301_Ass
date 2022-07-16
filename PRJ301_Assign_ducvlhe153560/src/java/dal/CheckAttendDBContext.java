/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author ASUS
 */
public class CheckAttendDBContext extends DBContext {

    public ArrayList<Check> getAllStudent(int sid) {
        ArrayList<Check> alist = new ArrayList<>();
        String sql = "  select * from CheckAttedance ca , Student s\n"
                + "  where ca.[SID]= s.SID  and ca.SlotID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slot s = new Slot();
                s.setId(rs.getInt(2));
                Student stu = new Student(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getBoolean(11), rs.getString(12));
                Check c = new Check(rs.getInt(1), s, rs.getBoolean(4), rs.getString(5), stu, rs.getString(6), rs.getString(7));
                alist.add(c);
            }
        } catch (Exception e) {
        }
        return alist;
    }

    public void insertAttendance(int sid, int stid, int checkAttend, String comment, String taker) {
        String sql = "INSERT [CheckAttedance] ( [SlotID], [SID], [CheckAttendance], [Comment], [Taker], [RecordTime]) \n"
                + "VALUES ( ?, ?, ?, ?, ?, GETDATE())";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, stid);
            stm.setInt(3, checkAttend);
            stm.setString(4, comment);
            stm.setString(5, taker);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void deleteSlot(int sid){
      String sql = "delete CheckAttedance where SlotID =?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.executeUpdate();
        } catch (Exception e) {
        }}
    
    
}