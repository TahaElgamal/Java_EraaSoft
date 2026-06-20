package com.item.controller;

import java.io.IOException;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.User;
import com.item.service.UserService;
import com.item.service.impl.UserServiceImpl;

import com.item.service.ValidationService;
import com.item.service.impl.ValidationServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name= "jdbc/item")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(Objects.isNull(action)) {
			action="login";
		}
		
		switch(action) {
			case "login":
				login(request,response);
				break;
			case "returnUser":
				returnUser(request,response);
				break;
			case "createUser":
				createUser(request,response);
				break;
			case "logOut":
				logOut(request,response);
				break;
			case "deleteUser":
				deleteUser(request,response);
				break;
			
			default :
				login(request,response);
		
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session =request.getSession(false);

    	    if(session == null || session.getAttribute("loggedUser") == null){

    	        try {

    	            response.sendRedirect("Login.jsp");

    	        } catch(IOException e){

    	            System.out.println(
    	                "Exception " + e.getMessage()
    	            );
    	        }

    	        return;
    	    }

    	    User user = (User) session.getAttribute("loggedUser");

    	    UserService userService = new UserServiceImpl(dataSource);

    	    boolean isUserDeleted = userService.deleteUser(user.getId());
    	    
    	    if(isUserDeleted) {
    	    	logOut(request,response);
    	    }else {
    	    	try {
    	            response.sendRedirect("error-user.html");
    	        	} catch (IOException e) {
    	    			System.out.println("Exception "+ e.getMessage());
    	    		}
    	    }
		
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) {

	    HttpSession session = request.getSession(false);

	    if(session != null) {
	        session.invalidate();
	    }

	    Cookie[] cookies = request.getCookies();
	    
	    if(cookies != null) {
	        for(Cookie cookie : cookies) {
	            if(cookie.getName().equals("username")) {
	                cookie.setMaxAge(0);
	                response.addCookie(cookie);
	            }
	        }
	    }
	    try {
	        response.sendRedirect("Login.jsp");
	    } catch(IOException e) {
	        System.out.println("Exception " + e.getMessage());
	    }
		
	}

	private void returnUser(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserServiceImpl(dataSource);
		String userName =request.getParameter("username");
		if(!(userService.isUserNameExist(userName))) {

		    request.setAttribute(
		        "errorMessage",
		        "Username Not exists"
		    );

		    try {

		        request.getRequestDispatcher("/Login.jsp")
		               .forward(request,response);

		    } catch (ServletException | IOException e) {

		        System.out.println(
		            "Exception " + e.getMessage()
		        );
		    }

		    return;
		}
		String password =request.getParameter("password");
	
		User user = new User(userName,password);
		User userLogged=userService.selectUser(user);
		if(Objects.nonNull(userLogged)) {
			HttpSession session = request.getSession();

            session.setAttribute("loggedUser",userLogged);
            
            String keepLoggedIn =request.getParameter("keepLoggedIn");
            if(keepLoggedIn != null) {
                Cookie cookie =new Cookie("username",userLogged.getUserName());

                cookie.setMaxAge(7 * 24 * 60 * 60 );

                response.addCookie(cookie);
            }else {
            	try {
    	            response.sendRedirect("error-user.html");
    	        	} catch (IOException e) {
    	    			System.out.println("Exception "+ e.getMessage());
    	    		}
            }

            try {
	            response.sendRedirect("/item-service-mvc-project/itemController");
	        	} catch (IOException e) {
	    			System.out.println("Exception "+ e.getMessage());
	    		}

        }else{

	        	try {
	            response.sendRedirect("error-user.html");
	        	} catch (IOException e) {
	    			System.out.println("Exception "+ e.getMessage());
	    		}
        	}
		
		
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) {
		
		UserService userService = new UserServiceImpl(dataSource);
		ValidationService validation =new ValidationServiceImpl();
		
		
		String userName =request.getParameter("username");
		if(!validation.isUserNameValid(userName)) {
			request.setAttribute(
			        "errorMessage",
			        "Username inValid"
			    );

			    try {

			        request.getRequestDispatcher("/Login.jsp")
			               .forward(request,response);

			    } catch (ServletException | IOException e) {

			        System.out.println(
			            "Exception " + e.getMessage()
			        );
			    }

			    return;
		}
		String email =request.getParameter("email");
		if(!validation.isEmailValid(email)) {
			request.setAttribute(
			        "errorMessage",
			        "Email inValid"
			    );

			    try {

			        request.getRequestDispatcher("/Login.jsp")
			               .forward(request,response);

			    } catch (ServletException | IOException e) {

			        System.out.println(
			            "Exception " + e.getMessage()
			        );
			    }

			    return;
		}
		String password =request.getParameter("password");
		if(!validation.isPasswordValid(password)) {
			request.setAttribute(
			        "errorMessage",
			        "Password inValid"
			    );

			    try {

			        request.getRequestDispatcher("/Login.jsp")
			               .forward(request,response);

			    } catch (ServletException | IOException e) {

			        System.out.println(
			            "Exception " + e.getMessage()
			        );
			    }

			    return;
		}
		int age =Integer.parseInt(request.getParameter("age"));
		if(!validation.isAgeValid(age)) {
			request.setAttribute(
			        "errorMessage",
			        "Age inValid"
			    );

			    try {

			        request.getRequestDispatcher("/Login.jsp")
			               .forward(request,response);

			    } catch (ServletException | IOException e) {

			        System.out.println(
			            "Exception " + e.getMessage()
			        );
			    }

			    return;
		}
		String phoneNumber =request.getParameter("phoneNumber");
		if(!validation.isPhonNumberValid(phoneNumber)) {
			request.setAttribute(
			        "errorMessage",
			        "PhoneNumber inValid"
			    );

			    try {

			        request.getRequestDispatcher("/Login.jsp")
			               .forward(request,response);

			    } catch (ServletException | IOException e) {

			        System.out.println(
			            "Exception " + e.getMessage()
			        );
			    }

			    return;
		}
		
		if(userService.isUserNameExist(userName)) {

		    request.setAttribute(
		        "errorMessage",
		        "Username already exists"
		    );

		    try {

		        request.getRequestDispatcher("/Login.jsp")
		               .forward(request,response);

		    } catch (ServletException | IOException e) {

		        System.out.println(
		            "Exception " + e.getMessage()
		        );
		    }

		    return;
		}
		
		User user = new User(userName,email,password,age,phoneNumber);
		boolean userCreated=userService.createUser(user);
		
		if (userCreated) {
			User userLogged =userService.selectUser(user);
			HttpSession session = request.getSession();

            session.setAttribute("loggedUser",userLogged);
            
            try {
	            response.sendRedirect("/item-service-mvc-project/itemController");
	        	} catch (IOException e) {
	    			System.out.println("Exception "+ e.getMessage());
	    		}

        }else{

	        	try {
	            response.sendRedirect("error-user.html");
	        	} catch (IOException e) {
	    			System.out.println("Exception "+ e.getMessage());
	    		}
        	}
		
		}
		
	

	private void login(HttpServletRequest request, HttpServletResponse response) {
	    Cookie[] cookies =
	    	    request.getCookies();

	    	    String rememberedUser = null;

	    	    if(cookies != null){
	    	        for(Cookie cookie : cookies){
	    	            if(cookie.getName().equals("username")){
	    	                rememberedUser = cookie.getValue();
	    	            }
	    	        }
	    	    }

	    	    request.setAttribute(
	    	        "rememberedUser",
	    	        rememberedUser );
	    	    
		try {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("Exception "+ e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
