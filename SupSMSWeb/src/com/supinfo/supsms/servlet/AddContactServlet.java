package com.supinfo.supsms.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Contact;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.globals.InputName;
import com.supinfo.supsms.service.ContactService;
import com.supinfo.supsms.service.UserService;
import com.supinfo.supsms.utils.PatternValidator;

@WebServlet("/auth/addContact")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ContactService contactService;
	
	@EJB
	private UserService userService;
       
    public AddContactServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("pageTitle", "Add a contact");
		request.getRequestDispatcher("/auth/addContact.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Getting the form's field's values
		String firstname = request.getParameter(InputName.PRMTR_FIRSTNAME);
		String lastname = request.getParameter(InputName.PRMTR_LASTNAME);
		String phone = request.getParameter(InputName.PRMTR_PHONENUMBER);
		String mail = request.getParameter(InputName.PRMTR_MAIL);

		HttpSession session = request.getSession();

		if(PatternValidator.isPhoneNumberValid(phone))
		{
			try
			{
				if(userService.getUserByPhoneNumber(new JSONObject("{ phone :'"+phone+"'}")))
				{
					User user = (User) session.getAttribute("user");
					
					//Creation of a new Contact
					Contact contact = new Contact();
					contact.setFirstName(firstname);
					contact.setLastName(lastname);
					contact.setPhone(phone);
					contact.setMail(mail);
					contact.setUser_owner(user);
					contact = contactService.add(contact.toJson());
		
					response.sendRedirect(request.getContextPath()+"/auth/contact");
				}
				else
				{
					session.setAttribute(Alert.PRMTR_ERROR, "There is no user with this phone number!");
					request.setAttribute("pageTitle", "Add a contact");
					request.getRequestDispatcher("/auth/addContact.jsp").forward(request, response);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			session.setAttribute(Alert.PRMTR_ERROR, "Your phone number is not valid. Check it out!");
			request.setAttribute("pageTitle", "Add a contact");
			request.getRequestDispatcher("/auth/addContact.jsp").forward(request, response);
		}
	}

}
