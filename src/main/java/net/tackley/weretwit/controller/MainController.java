package net.tackley.weretwit.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.tackley.weretwit.Moon;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Singleton
public class MainController extends HttpServlet {

	@Inject
	public MainController() {
		System.out.println("Constructed!");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		Twitter twit = new Twitter();
		try {
			List<Status> timeline = twit.getUserTimeline("tackers");

			for (Status status : timeline) {
				writer.printf("\"%s\" tweeted at %s (phase %d)\n", status.getText(), status.getCreatedAt(),
						Moon.getPhaseOfMoon(status.getCreatedAt()));
			}

		} catch (TwitterException e) {
			throw new ServletException(e);
		}



	}

}
