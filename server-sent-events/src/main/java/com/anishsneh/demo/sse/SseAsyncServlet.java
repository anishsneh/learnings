package com.anishsneh.demo.sse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class SseAsyncServlet.
 * 
 * @author Anish Sneh
 */
@WebServlet(urlPatterns = { "/helloworld" }, asyncSupported = true)
public class SseAsyncServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8155471730183825885L;
	
	/** The Constant executorService. */
	private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/event-stream");
		res.setCharacterEncoding("UTF-8");
		final String msg = req.getParameter("msg");
		final AsyncContext ac = req.startAsync();
		final PrintWriter writer = res.getWriter();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					if (i == 29) { // last
						writer.write("event: close\n");
					}
					writer.write("data: " + msg + "@" + UUID.randomUUID().toString() + "\n\n");
					writer.flush();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException iex) {
						iex.printStackTrace();
					}
				}
				ac.complete();
			}
		};
		executorService.submit(runnable);
	}
}
