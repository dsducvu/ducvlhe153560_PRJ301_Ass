/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CheckAttendDBContext;
import dal.GroupDBContext;
import dal.SlotDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Check;
import model.Slot;
import model.Student;

/**
 *
 * @author ASUS
 */
public class CheckAttend extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String status = request.getParameter("status");
            if ("false".equals(status)) {
                CheckAttendDBContext cadbc = new CheckAttendDBContext();
                StudentDBContext studbc = new StudentDBContext();
                String sid = request.getParameter("sid");

                SlotDBContext sdbc = new SlotDBContext();
                ArrayList<Check> cklist = cadbc.getAllStudent(Integer.valueOf(sid));
                Slot s = sdbc.getSlotById(Integer.valueOf(sid));
                ArrayList<Student> stulist = studbc.getAllStudent(s.getGroup().getCode());
                request.setAttribute("cklist", cklist);
                request.setAttribute("stulist", stulist);
                request.setAttribute("s", s);
                request.getRequestDispatcher("CheckAttend.jsp").forward(request, response);
            }
            if ("true".equals(status)) {
                GroupDBContext gdbc = new GroupDBContext();
                CheckAttendDBContext cadbc = new CheckAttendDBContext();
                StudentDBContext studbc = new StudentDBContext();
                String sid = request.getParameter("sid");
                SlotDBContext sdao = new SlotDBContext();
                ArrayList<Check> cklist = cadbc.getAllStudent(Integer.valueOf(sid));
                Slot s = sdao.getSlotById(Integer.valueOf(sid));
                ArrayList<Student> stulist = studbc.getAllStudent(s.getGroup().getCode());
                request.setAttribute("cklist", cklist);
                request.setAttribute("stulist", stulist);
                request.setAttribute("s", s);
                request.getRequestDispatcher("Checked.jsp").forward(request, response);
            }

        } catch (Exception e) {
            response.getWriter().print("Lack parameter try again!");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SlotDBContext sdbc = new SlotDBContext();
        CheckAttendDBContext cadbc = new CheckAttendDBContext();
        StudentDBContext studbc= new StudentDBContext();
        String slotid = request.getParameter("sid");
        String instructorid = request.getParameter("instructorid");
        Slot s = sdbc.getSlotById(Integer.valueOf(slotid));
        ArrayList<Student> stulist = studbc.getAllStudent(s.getGroup().getCode());

        for (Student student : stulist) {
            String checkbox = request.getParameter(String.valueOf(student.getCode()));
            int checkstatus = 0;
            if (checkbox == null) {
                checkstatus = 0;
            } else {
                checkstatus = 1;
            }
            response.getWriter().println(student.getCode() + "-" + checkbox);
            cadbc.insertAttendance(Integer.valueOf(slotid), student.getId(), checkstatus, "", instructorid);
        }
        sdbc.updateStatus(Integer.valueOf(slotid));
        response.sendRedirect("TeachingSchedule");

//        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}