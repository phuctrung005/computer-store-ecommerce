package com.computerstore.controllers.admin;

import java.io.IOException;

import com.computerstore.dao.StatsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/dashboard")
public class AdminStatsServlet extends HttpServlet {
	private StatsDAO statsDAO = new StatsDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Order stats
		req.setAttribute("totalOrders", statsDAO.getTotalOrders());
		req.setAttribute("totalRevenue", statsDAO.getTotalRevenue());
		req.setAttribute("totalCustomers", statsDAO.getTotalCustomers());
		req.setAttribute("pendingReviews", statsDAO.getPendingReviews());

		// Product stats
		req.setAttribute("totalProducts", statsDAO.getTotalProducts());
		req.setAttribute("activeProducts", statsDAO.getActiveProducts());
		req.setAttribute("lowStockProducts", statsDAO.getLowStockProducts());
		req.setAttribute("outOfStockProducts", statsDAO.getOutOfStockProducts());

		// Recent orders (có LIMIT từ DB)
		req.setAttribute("recentOrders", statsDAO.getRecentOrders(5));

		req.getRequestDispatcher("/jsp/admin/dashboard.jsp").forward(req, resp);
	}
}
