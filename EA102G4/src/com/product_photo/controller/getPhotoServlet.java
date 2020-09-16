package com.product_photo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_photo.model.PhotoJDBCDAO;
import com.product_photo.model.PhotoVO;
 
public class getPhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public getPhotoServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String bookId = request.getParameter("id");
        PhotoJDBCDAO dao = new PhotoJDBCDAO();
         
        PhotoVO book = dao.get(bookId);
		System.out.println(book.toString());
		request.setAttribute("photoVO", book);
		 
		String page = "show_photo.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
         
    }
}
