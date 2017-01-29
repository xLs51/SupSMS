package com.supinfo.supsms.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.supinfo.supsms.entities.Invoice;
import com.supinfo.supsms.entities.User;
import com.supinfo.supsms.globals.Alert;
import com.supinfo.supsms.globals.InputName;
import com.supinfo.supsms.globals.RegisterConstraints;
import com.supinfo.supsms.service.InvoiceService;
import com.supinfo.supsms.service.RegisterService;
import com.supinfo.supsms.service.UserService;
import com.supinfo.supsms.utils.EncryptionServices;
import com.supinfo.supsms.utils.PatternValidator;

@WebServlet("/auth/editProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private RegisterService registerService;
	
	@EJB
	private UserService userService;
	
	@EJB
	private InvoiceService invoiceService;
	
    public EditProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User actualUser = (User) session.getAttribute("user");
		
		String stringId = (String) request.getParameter("id");
		if (stringId != null)
		{
			int userId = Integer.parseInt(stringId);
 
			if(actualUser.getId() == userId || actualUser.getIsAdmin()) //we need to be a regular user wanting to edit our own profile or an Admin
			{
				User user = null;
				if (actualUser.getIsAdmin())
				{
					user = userService.getById((long)userId);
					if (user == null)
					{
						session.setAttribute(Alert.PRMTR_WARNING, "No user with this id is currently existing.");
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/auth/profile"));
						return;
					}
				}
				List<Invoice> invoices = invoiceService.getAll();
				request.setAttribute("listInvoices", invoices);
				request.setAttribute("pageTitle", "Profile Edition");
				user = userService.getById((long)userId);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/auth/editProfile.jsp").forward(request, response);
				return;
			} 
			else
			{
				session.setAttribute(Alert.PRMTR_WARNING, "You're not allowed to visit this page.");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
			}
		 }
		 else
		 {
			List<Invoice> invoices = invoiceService.getAll();
			request.setAttribute("listInvoices", invoices);
			request.setAttribute("pageTitle", "Profile Edition");
			request.setAttribute("user", actualUser);
			request.getRequestDispatcher("/auth/editProfile.jsp").forward(request, response);
			return;
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User actualUser = (User) session.getAttribute("user");
		actualUser = userService.getById(actualUser.getId());
		
		//Getting the form's field's values
		String mail = request.getParameter(InputName.PRMTR_MAIL);
		String oldPassword = request.getParameter(InputName.PRMTR_OLDPASSWORD);
		String password = request.getParameter(InputName.PRMTR_PASSWORD);
		String passwordConfirm = request.getParameter(InputName.PRMTR_PASSWORDCONFIRM);
		String firstname = request.getParameter(InputName.PRMTR_FIRSTNAME);
		String lastname = request.getParameter(InputName.PRMTR_LASTNAME);
		String creditCard = request.getParameter(InputName.PRMTR_CREDITCARD);
		String phoneNumber = request.getParameter(InputName.PRMTR_PHONENUMBER);
		String invoiceString = request.getParameter(InputName.PRMTR_INVOICE);
		int invoiceId = 0;
		if (invoiceString != null)
		{
			invoiceId = Integer.parseInt(invoiceString);
		}
			
		boolean errorHappened = false;
		boolean somethingChanged = false;
		try
		{
			if(!mail.isEmpty() && mail != null)
			{
				if(!registerService.isMailExisting(new JSONObject("{ mail :'"+mail+"'}")))
				{
					somethingChanged = true;
					actualUser.setMail(mail);
				}
				else
				{
					errorHappened = true;
					session.setAttribute(Alert.PRMTR_ERROR, "This mail already exists.");
				}
			}
			
			if(!phoneNumber.isEmpty() && phoneNumber != null)
			{
				if(!registerService.isPhoneNumberExisting(new JSONObject("{ phone :'"+phoneNumber+"'}")) 
						&& PatternValidator.isPhoneNumberValid(phoneNumber.replace("-", "").replace(",", "")))
				{
					somethingChanged = true;
					actualUser.setPhone(phoneNumber);
				}
				else
				{
					errorHappened = true;
					session.setAttribute(Alert.PRMTR_ERROR, "Your phone number is incorrect. Please verify it's validity.");			
				}
			}			
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		if((!password.isEmpty() && password != null)
				|| (!passwordConfirm.isEmpty() && passwordConfirm != null)
				|| (!oldPassword.isEmpty() && oldPassword != null))
		{
			if((!password.isEmpty() && password != null)
					&& (!passwordConfirm.isEmpty() && passwordConfirm != null)
					&& (!oldPassword.isEmpty() && oldPassword != null))
			{
				if(PatternValidator.isPasswordValid(password)
						&& EncryptionServices.encryptSHA1(oldPassword).equals(actualUser.getPassword()) 
						&& passwordConfirm.equals(password))
				{		
					somethingChanged = true;
					actualUser.setPassword(EncryptionServices.encryptSHA1(password));		
				}
				else
				{
					errorHappened = true;
					session.setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify that passwords match and are at least "+ RegisterConstraints.PASSWORD_LENGTH  +" caracters long.");
				}	
			}
			else
			{
				errorHappened = true;
				session.setAttribute(Alert.PRMTR_ERROR, "You have to fill the 3 password related fields to modify it. Reminder : "+ RegisterConstraints.PASSWORD_LENGTH +" caracters minimum required.");
			}
		}
			
		
		if(!creditCard.isEmpty() && creditCard != null)
		{
			if(PatternValidator.isCreditCardNumberValid(creditCard.replace("-", "").replace(",", "")))
			{
				somethingChanged = true;
				actualUser.setCreditCard(creditCard);
			}	
			else
			{
				errorHappened = true;
				session.setAttribute(Alert.PRMTR_ERROR, "Your credit card number is incorrect. Please verify it's validity.");
			}
		}
		
		if(firstname != null && !firstname.isEmpty())
		{
			somethingChanged = true;
			actualUser.setFirstName(firstname);
		}
			
		if(lastname != null && !lastname.isEmpty())
		{
			somethingChanged = true;
			actualUser.setLastName(lastname);
		}			
		
		if(invoiceId > 0)
		{
			if(invoiceId != actualUser.getInvoice().getId()){
				somethingChanged = true;
				Invoice selectedInvoice = registerService.getSelectedInvoice(invoiceId);
				actualUser.setInvoice(selectedInvoice);
			}			
		}
		else
		{
			errorHappened = true;
			session.setAttribute(Alert.PRMTR_ERROR, "Please stop trying to hack you fool ! =O");
		}
		
		if(errorHappened || !somethingChanged) //nothing have changed => all parameters were blank
		{
			List<Invoice> invoices = invoiceService.getAll();
			request.setAttribute("listInvoices", invoices);
			request.setAttribute("pageTitle", "Profile Edition");
			request.setAttribute("user", actualUser);
			doGet(request, response);
		}
		else
		{
			System.out.println("Invoice id should change to " + actualUser.getInvoice().getId());
			System.out.println("CreditCard id should change to " + actualUser.getCreditCard());
			System.out.println("Firstname should change to " + actualUser.getFirstName());
			System.out.println("Lastname should change to " + actualUser.getLastName());
			System.out.println("Mail should change to " + actualUser.getMail());
			System.out.println("Lastname should change to " + actualUser.getPassword());
			System.out.println("Phonenumber should change to " + actualUser.getPhone());
			System.out.println("Username should change to " + actualUser.getUsername());
			
			// Updating User to the database
			userService.update(actualUser);
			session.setAttribute("user", actualUser);	
			session.setAttribute(Alert.PRMTR_INFO, "Your profile has been successfully modified.");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/auth/profile"));
		}

	}

}