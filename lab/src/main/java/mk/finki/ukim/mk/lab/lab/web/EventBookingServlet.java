//package mk.finki.ukim.mk.lab.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.lab.model.EventBooking;
//import mk.finki.ukim.mk.lab.lab.model.Location;
//import mk.finki.ukim.mk.lab.lab.service.EventBookingService;
//import mk.finki.ukim.mk.lab.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "EventBookingServlet",urlPatterns = {"/eventBooking"})
//
//public class EventBookingServlet extends HttpServlet {
//
//    private final EventService eventService;
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine){
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String queryString = req.getParameter("bookingSearch");
//
//        List<EventBooking> eventBookings = eventBookingService.getEventBookings()
//                .stream().filter(savedBooking -> savedBooking.getEventName().toLowerCase().contains(queryString.toLowerCase()))
//                .toList();
//        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//        context.setVariable("savedBookingList", eventBookings);
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<EventBooking> savedBookingList = eventBookingService.getEventBookings();
//
//        String eventName = req.getParameter("eventName");
//        String numOfTickets = req.getParameter("numTickets");
//
//        eventBookingService.placeBooking(eventName, req.getRemoteHost(), req.getRemoteAddr(), Integer.parseInt(numOfTickets));
//
//        IWebExchange iWebExchange = JakartaServletWebApplication
//                .buildApplication(req.getServletContext())
//                .buildExchange(req,resp);
//        WebContext context = new WebContext(iWebExchange);
//        context.setVariable("hostName", req.getRemoteHost());
//        context.setVariable("hostAddress", req.getRemoteAddr());
//        context.setVariable("eventName",eventName);
//        context.setVariable("numTickets",numOfTickets);
//        context.setVariable("location", req.getParameter("location"));
//        context.setVariable("savedBookingList", savedBookingList);
//        springTemplateEngine.process("bookingConfirmation.html",context,resp.getWriter());
//    }
//
//
//}
