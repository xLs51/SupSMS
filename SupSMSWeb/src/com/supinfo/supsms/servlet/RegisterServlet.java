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
import com.supinfo.supsms.service.RegisterService;
import com.supinfo.supsms.utils.EncryptionServices;
import com.supinfo.supsms.utils.PatternValidator;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	private RegisterService registerService;

	public RegisterServlet() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// User is already logged in, redirect to home page
		if(request.getSession().getAttribute("user") != null)
		{
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		List<Invoice> invoices = registerService.getAllInvoices();
		request.setAttribute("invoices", invoices);
		request.setAttribute("pageTitle", "Register");
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Getting the form's field's values
		String username = request.getParameter(InputName.PRMTR_USERNAME);
		String password = request.getParameter(InputName.PRMTR_PASSWORD);
		String passwordConfirm = request.getParameter(InputName.PRMTR_PASSWORDCONFIRM);
		String firstname = request.getParameter(InputName.PRMTR_FIRSTNAME);
		String lastname = request.getParameter(InputName.PRMTR_LASTNAME);
		String phone = request.getParameter(InputName.PRMTR_PHONENUMBER);
		String creditCard = request.getParameter(InputName.PRMTR_CREDITCARD);
		String mail = request.getParameter(InputName.PRMTR_MAIL);
		String invoice = request.getParameter(InputName.PRMTR_INVOICE);

		HttpSession session = request.getSession();

		if(password != null && passwordConfirm != null && username != null)
		{
			try
			{
				if(!registerService.isUserExisting(new JSONObject("{ username :'"+username+"'}")))
				{
					if(!registerService.isMailExisting(new JSONObject("{ mail :'"+mail+"'}")))
					{
						if(!registerService.isPhoneNumberExisting(new JSONObject("{ phone :'"+phone+"'}")))
						{
							if(PatternValidator.isPasswordValid(password) && password.equals(passwordConfirm))
							{
								if(PatternValidator.isPhoneNumberValid(phone.replace("-", "").replace(",", "")))
								{
									if(PatternValidator.isCreditCardNumberValid(creditCard.replace("-", "").replace(",", "")))
									{
										Invoice selectedInvoice = registerService.getSelectedInvoice(Integer.parseInt(invoice));
										
										if(selectedInvoice != null)
										{
											//Creation of a new User
											User user = new User();
											user.setUsername(username);
											user.setPassword(EncryptionServices.encryptSHA1(password));
											user.setFirstName(firstname);
											user.setLastName(lastname);
											user.setCreditCard(creditCard);
											user.setPhone(phone);
											user.setMail(mail);
											user.setInvoice(selectedInvoice);
											user = registerService.registerUser(user.toJson());
						
											//Connecting the new User
											session.setAttribute("user", user);
											response.sendRedirect(request.getContextPath() + "/home");
										}
										else
										{
											session.setAttribute(Alert.PRMTR_ERROR, "Why are u hacking ?");
											request.setAttribute("username", username);
											List<Invoice> invoices = registerService.getAllInvoices();
											request.setAttribute("invoices", invoices);
											request.setAttribute("pageTitle", "Register");
											request.getRequestDispatcher("/register.jsp").forward(request, response);
										}
									}
									else
									{
										session.setAttribute(Alert.PRMTR_ERROR, "Your credit card is not valid. Check it out!");
										request.setAttribute("username", username);
										List<Invoice> invoices = registerService.getAllInvoices();
										request.setAttribute("invoices", invoices);
										request.setAttribute("pageTitle", "Register");
										request.getRequestDispatcher("/register.jsp").forward(request, response);
									}
								}
								else
								{
									session.setAttribute(Alert.PRMTR_ERROR, "Your phone number is not valid. Check it out!");
									request.setAttribute("username", username);
									List<Invoice> invoices = registerService.getAllInvoices();
									request.setAttribute("invoices", invoices);
									request.setAttribute("pageTitle", "Register");
									request.getRequestDispatcher("/register.jsp").forward(request, response);
								}
							}
							else
							{
								session.setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. Please verify that passwords match and are at least "+ RegisterConstraints.PASSWORD_LENGTH  +" caracters long.");
								request.setAttribute("username", username);
								List<Invoice> invoices = registerService.getAllInvoices();
								request.setAttribute("invoices", invoices);
								request.setAttribute("pageTitle", "Register");
								request.getRequestDispatcher("/register.jsp").forward(request, response);
							}
						}
						else
						{
							session.setAttribute(Alert.PRMTR_ERROR, "This phone number already exists.");
							request.setAttribute("username", username);
							List<Invoice> invoices = registerService.getAllInvoices();
							request.setAttribute("invoices", invoices);
							request.setAttribute("pageTitle", "Register");
							request.getRequestDispatcher("/register.jsp").forward(request, response);
						}
					}
					else
					{
						session.setAttribute(Alert.PRMTR_ERROR, "This mail already exists.");
						request.setAttribute("username", username);
						List<Invoice> invoices = registerService.getAllInvoices();
						request.setAttribute("invoices", invoices);
						request.setAttribute("pageTitle", "Register");
						request.getRequestDispatcher("/register.jsp").forward(request, response);
					}
				}
				else
				{
					session.setAttribute(Alert.PRMTR_ERROR, "Your credentials are incorrect. This username is already used."); 
					List<Invoice> invoices = registerService.getAllInvoices();
					request.setAttribute("invoices", invoices);
					request.setAttribute("pageTitle", "Register");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}
}
