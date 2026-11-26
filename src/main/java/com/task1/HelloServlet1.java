package com.task1;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/hello1")
public class HelloServlet1 implements Servlet {

    private static final String GET_METHOD = "GET";
    private static final String DEFAULT_NAME = "Guest";

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws IOException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        if (!GET_METHOD.equals(httpRequest.getMethod())) {
            httpResponse.setHeader("Allow", GET_METHOD);
            httpResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            name = DEFAULT_NAME;
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().print("Hello, " + name);
    }

    @Override
    public String getServletInfo() {
        return "Personalized greeting using the Servlet interface";
    }

    @Override
    public void destroy() {
    }
}
