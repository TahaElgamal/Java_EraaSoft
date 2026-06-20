package com.item.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.model.User;
import com.item.service.ItemService;
import com.item.service.ItemDetailsService;
import com.item.service.impl.ItemServiceImpl;
import com.item.service.impl.ItemDetailsServiceImpl;

/**
 * Servlet implementation class itemController
 */
@WebServlet("/itemController")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name= "jdbc/item")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(Objects.isNull(action)) {
			action="show-items";
		}
		
		switch(action) {
			case "show-item":
				showItem(request,response);
				break;
			case "show-items":
				showItems(request,response);
				break;
			case "add-item":
				addItem(request,response);
				break;
			case "update-item":
				updateItem(request,response);
				break;
			case "delete-item":
				deleteItem(request,response);
				break;
			case "add-details":
				addDetails(request,response);
				break;
			case "show-details":
				showDetails(request,response);
				break;
			case "update-details":
				updateDetails(request,response);
				break;
			default :
				showItems(request,response);
		
		}
		
	}
	private void updateDetails(HttpServletRequest request, HttpServletResponse response) {
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		HttpSession session =request.getSession(false);
		if(session == null ||session.getAttribute("loggedUser") == null){
		    try {
		        response.sendRedirect("Login.jsp");
		    } catch(IOException e){
		        System.out.println(
		            "Exception " + e.getMessage()
		        );
		    }
		    return;
		}
		String color = request.getParameter("color");
		double weight = Double.parseDouble(request.getParameter("weight"));
		Date manufactureDate = Date.valueOf(request.getParameter("manuDate"));
		Date expiryDate = Date.valueOf(request.getParameter("expDate"));
		String countryOrgin = request.getParameter("countryOrg");
		String description = request.getParameter("description");
		long itemId = Long.parseLong(request.getParameter("itemId"));
		
		ItemDetails newDetails = new ItemDetails(description,color,weight,manufactureDate,expiryDate,countryOrgin,itemId);
		boolean  detailsUpdated = itemDetailsService.updateItemDetails(newDetails);
		
		if(detailsUpdated) {
			showItems(request,response);
		}
	}

	private void addDetails(HttpServletRequest request, HttpServletResponse response) {
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		HttpSession session =request.getSession(false);
		if(session == null ||session.getAttribute("loggedUser") == null){
		    try {
		        response.sendRedirect("Login.jsp");
		    } catch(IOException e){
		        System.out.println(
		            "Exception " + e.getMessage()
		        );
		    }
		    return;
		}
		String color = request.getParameter("color");
		double weight = Double.parseDouble(request.getParameter("weight"));
		Date manufactureDate = Date.valueOf(request.getParameter("manuDate"));
		Date expiryDate = Date.valueOf(request.getParameter("expDate"));
		String countryOrgin = request.getParameter("countryOrg");
		String description = request.getParameter("description");
		long itemId = Long.parseLong(request.getParameter("itemId"));
		
		ItemDetails details = new ItemDetails(description,color,weight,manufactureDate,expiryDate,countryOrgin,itemId);
		
		boolean  detailsAdded = itemDetailsService.addItemDetails(details);
		
		if(detailsAdded) {
			showItems(request,response);
		}
		

		
	}

	private void showDetails(HttpServletRequest request, HttpServletResponse response) {
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		
		long id = Long.parseLong(request.getParameter("id"));
		HttpSession session =request.getSession(false);

				if(session == null ||session.getAttribute("loggedUser") == null){

				    try {
				        response.sendRedirect("Login.jsp");
				    } catch(IOException e){

				        System.out.println(
				            "Exception " + e.getMessage()
				        );
				    }

				    return;
				}
		ItemDetails itemDetails = itemDetailsService.selectItemDetails(id);
		
		
		if(Objects.nonNull(itemDetails)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				request.setAttribute(
				    "manuDate",
				    sdf.format(itemDetails.getManufactureDate())
				);

				request.setAttribute(
				    "expDate",
				    sdf.format(itemDetails.getExpiryDate())
				);
				
			request.setAttribute("detailsSelected", itemDetails);
			
			try {
				request.getRequestDispatcher("/update-details.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				System.out.println("Exception "+ e.getMessage());
			}
		}else {
			
			try {
				request.getRequestDispatcher("/error-item.html").forward(request, response);
			} catch (ServletException | IOException e) {
				System.out.println("Exception "+ e.getMessage());
				
			}
		}
		
	}

	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		ItemService itemService = new ItemServiceImpl(dataSource);
		HttpSession session =request.getSession(false);
		if(session == null ||session.getAttribute("loggedUser") == null){
			        try {

			            response.sendRedirect("Login.jsp");

			        } catch (IOException e) {

			            System.out.println(
			                "Exception " + e.getMessage()
			            );
			        }

			        return;
			    }

		User user =(User) session.getAttribute("loggedUser");
		Item item=itemService.selectItem(user.getId(),id);
		
		if (Objects.nonNull(item)) {
			request.setAttribute("itemSelected", item);
			
			try {
				request.getRequestDispatcher("/update-item.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				System.out.println("Exception "+ e.getMessage());
			}
		}else {
			try {
				request.getRequestDispatcher("/error-item.html").forward(request, response);
			} catch (ServletException | IOException e) {
				System.out.println("Exception "+ e.getMessage());
				
			}
		}
		
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session =request.getSession(false);
		if(session == null ||session.getAttribute("loggedUser") == null){
			        try {

			            response.sendRedirect("Login.jsp");

			        } catch (IOException e) {

			            System.out.println(
			                "Exception " + e.getMessage()
			            );
			        }

			        return ;
			    }

		User user =(User) session.getAttribute("loggedUser");
		ItemService itemService = new ItemServiceImpl(dataSource);
		String name = request.getParameter("nameItem");
		double price = Double.parseDouble(request.getParameter("priceItem"));
		int total_number =Integer.parseInt(request.getParameter("totalNumberItem"));
		
		if(itemService.isItemExist(name, user.getId())) {
		    request.setAttribute("errorMessage", "Item already exists for this user");

		    try {
				request.getRequestDispatcher("/add-item.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    return;
		}
		
		Item item =new Item(name, price, total_number, user.getId());
		
		boolean isItemAdded = itemService.addItem(item);
		
		if(isItemAdded) {
			showItems(request,response);
		}
		
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("nameItem");
		double price = Double.parseDouble(request.getParameter("priceItem"));
		int total_number =Integer.parseInt(request.getParameter("totalNumberItem"));
		
		HttpSession session =request.getSession(false);
		if(session == null ||session.getAttribute("loggedUser") == null){
			        try {

			            response.sendRedirect("Login.jsp");

			        } catch (IOException e) {

			            System.out.println(
			                "Exception " + e.getMessage()
			            );
			        }

			        return;
			    }

		User user =(User) session.getAttribute("loggedUser");
		
		Item item =new Item(id,name,price,total_number,user.getId());
		
		ItemService itemService = new ItemServiceImpl(dataSource);
		
		boolean isItemUpdated = itemService.updateItem(item);
		
		if(isItemUpdated) {
			showItems(request,response);
		}
		
		
		
		
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session =request.getSession(false);

	    if(session == null ||session.getAttribute("loggedUser") == null){

	        try {

	            response.sendRedirect("Login.jsp");

	        } catch (IOException e) {

	            System.out.println(
	                "Exception " + e.getMessage()
	            );
	        }

	        return;
	    }
		Long id = Long.parseLong(request.getParameter("id"));
		ItemService itemService = new ItemServiceImpl(dataSource);
		boolean isItemDeleted = itemService.deleteItem(id);
		
		if(isItemDeleted) {
			showItems(request,response);
		}
	}

	private void showItems(HttpServletRequest request, HttpServletResponse response) {
		
		 HttpSession session =request.getSession(false);

				    if(session == null ||
				       session.getAttribute("loggedUser") == null){

				        try {

				            response.sendRedirect("Login.jsp");

				        } catch (IOException e) {

				            System.out.println(
				                "Exception " + e.getMessage()
				            );
				        }

				        return;
				    }
	    
		User user =(User) session.getAttribute("loggedUser");
		
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		ItemService itemService = new ItemServiceImpl(dataSource);
		
		List<Item> items= itemService.getAllItems(user);
		for(Item item : items){

		    boolean hasDetails =
		    itemDetailsService.hasDetails(
		        item.getId()
		    );

		    item.setHasDetails(hasDetails);
		}
		request.setAttribute("allItems", items);
		request.setAttribute("User", user);
		
		try {
			request.getRequestDispatcher("/show-items.jsp").forward(request, response);
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
