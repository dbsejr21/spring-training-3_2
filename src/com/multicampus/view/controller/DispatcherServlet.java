package com.multicampus.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 1. 브라우저의 요청 URI로부터 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		// 2. 요청 path에 해당하는 Controller를 검색한다.
		HandlerMapping mapping = new HandlerMapping();
		Controller ctrl = mapping.getController(path);
		
		// 3. 검색된 Controller를 실행하고 View 정보를 리턴받는다.
		String viewPage = ctrl.handleRequest(request, response);
		
		// 4. 리턴된 View 화면으로 이동한다.
		response.sendRedirect(viewPage);
	}

}
