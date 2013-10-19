package com.demo.VaadinMavenSpringHibernate;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    	
    	// Getting a bean from the Spring bean factory:
      	SpringContextHelper helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
    	final MyBeanInterface bean = (MyBeanInterface)helper.getBean("myBean");
    	
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                
            	// layout.addComponent(new Label(new MyBeanInterface().myMethod()));
            	
            	// Using the bean to access the object method:
            	layout.addComponent(new Label(bean.myMethod()));
            }
        });
        layout.addComponent(button);
        
		// ********************************************************************************************
		//
		// Using postgresql with these data in pom.xml file:
		//
		/*
		 * <dependency> 
		 * 	<groupId>org.hibernate</groupId>
		 * 	<artifactId>hibernate-core</artifactId>
		 * 	<version>4.2.6.Final</version> 
		 * </dependency> 
		 * <dependency>
		 * 	<groupId>postgresql</groupId> 
		 * 	<artifactId>postgresql</artifactId>
		 * 	<version>9.0-801.jdbc4</version> 
		 * </dependency>
		 */

		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("The very first user)");

		// Getting a Session object:
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

		// Using session object to save an object.
		// First, begin a transaction:
		session.beginTransaction();

		// Saving:
		session.save(user);

		// Ending the transaction:
		session.getTransaction().commit();
		// ************************************************************************************************
    }

}
