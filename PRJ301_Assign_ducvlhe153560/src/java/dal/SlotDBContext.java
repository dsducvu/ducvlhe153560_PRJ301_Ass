/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Group;
import model.Instructor;
import model.Room;
import model.Slot;
import model.Subject;

/**
 *
 * @author ASUS
 */
public class SlotDBContext extends DBContext {

    public ArrayList<Slot> getAllSlot(String campus, String lecture) {
        if (campus == null) {
            campus = "";
        }
        if (lecture == null) {
            lecture = "";
        }
//        List<Group> slist = new ArrayList<>();
        ArrayList<Slot> slist = new ArrayList<>();
        String sql = "  select * from Slot s, [Group] g, Room r, Instructor i, [Subject] sub\n"
                + "  where s.GroupID = g.GroupID and r.RoomID =s.RoomID and i.InstructorID = g.InstructorID and sub.SubID =g.SubID\n"
                + "  and i.UserName =? and g.Campus =? order by s.Slot";
        try {

            PreparedStatement stm = connection.prepareStatement(sql);
            System.out.println("1");

            stm.setString(1, lecture);
            stm.setString(2, campus);
            ResultSet rs = stm.executeQuery();
            System.out.println("2");
            while (rs.next()) {
                Room r = new Room(rs.getString("RoomCode"));
                Group g = new Group(rs.getInt("GroupID"), rs.getString("GroupCode"), new Subject(rs.getString("SubCode")), rs.getString("Term"), rs.getString("Campus"));
                Slot s = new Slot(rs.getInt(1), g, rs.getDate(3), rs.getInt(4), r, rs.getBoolean(6));
                slist.add(s);
            }

        } catch (Exception e) {
        }
        return slist;
    }

    public Slot getSlotById(int sid) {

//        List<Group> slist = new ArrayList<>();
        ArrayList<Slot> slist = new ArrayList<>();
        String sql = "  select * from Slot s, [Group] g, Room r, Instructor i, [Subject] sub\n"
                + "  where s.GroupID = g.GroupID and r.RoomID =s.RoomID and i.InstructorID = g.InstructorID and sub.SubID =g.SubID\n"
                + "  and  s.SlotID = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room(rs.getString("RoomCode"));
                Group g = new Group(rs.getInt("GroupID"), rs.getString("GroupCode"), new Subject(rs.getString("SubCode")), rs.getString("Term"), rs.getString("Campus"), rs.getString("Department"), new Instructor(rs.getInt("InstructorID"), rs.getString("UserName")));
                Slot s = new Slot(rs.getInt(1), g, rs.getDate(3), rs.getInt(4), r, rs.getBoolean(6));
                return s;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateStatus(int sid) {
        try {
            String sql = "update Slot set status = 1 where SlotID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.executeUpdate();
        } catch (Exception e) {
        }

    }

}
