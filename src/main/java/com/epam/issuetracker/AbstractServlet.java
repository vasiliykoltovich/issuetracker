package com.epam.issuetracker;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public abstract class AbstractServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1445303293862822354L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				execute(request, response);
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				execute(request, response);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Abstract method to build UI and to handle User's requests.
		 * @param request Http request
		 * @param response Http response
		 * @throws ServletException exception to throw
		 * @throws IOException exception to throw
		 * @throws NamingException 
		 */
		protected abstract void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException, NamingException;

		/**
		 * Method that forward to chosen resource.
		 * @param url of chosen resource
		 * @param request Http request
		 * @param response Http response
		 * @throws ServletException exception to throw
		 * @throws IOException exception to throw
		 */
		protected void moveToUrl(String url, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		    rd.forward(request, response);
		    
		}

		/**
		 * Method which allow every child Servlet redirect users on ErrorServlet,
		 * which will show to user Error page.
		 * @param message Exception in String representation
		 * @param request Http request
		 * @param response Http response
		 * @throws ServletException exception to throw
		 * @throws IOException exception to throw
		 */
		protected void moveToErrorUrl(String message, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute(Web_Constants.KEY_ERROR, message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(Web_Constants.JSP_ERROR);
			rd.forward(request, response);
		}
}
