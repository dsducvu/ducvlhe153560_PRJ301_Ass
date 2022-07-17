/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.GroupDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author ASUS
 */
public class GroupStudent extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         StudentDBContext sdbc = new StudentDBContext();
         GroupDBContext gdbc = new GroupDBContext();
        String campus = request.getParameter("campus");
        String term = request.getParameter("term");
        String dept = request.getParameter("dept");
        String courseid = request.getParameter("courseid");
        String group = request.getParameter("group");
        ArrayList campuslist = gdbc.getAllCampus();
        ArrayList termlist = gdbc.getAllTerm(campus);
        ArrayList departmentlist = gdbc.getAllDep(term);
        ArrayList courselist = gdbc.getAllSubject(term, dept);
        ArrayList grouplist = gdbc.getAllGroup(term, dept, courseid);
        ArrayList<Student> liststudent = sdbc.getAllStudentByGroupCode(group);
        request.setAttribute("liststudent", liststudent);
        request.setAttribute("campuslist", campuslist);
        request.setAttribute("termlist", termlist);
        request.setAttribute("departmentlist", departmentlist);
        request.setAttribute("courselist", courselist);
        request.setAttribute("grouplist", grouplist);
        request.getRequestDispatcher("DisplayGroup.jsp").forward(request, response);
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
        processRequest(request, response);
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
