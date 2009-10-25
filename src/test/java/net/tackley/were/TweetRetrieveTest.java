package net.tackley.were;

import net.tackley.weretwit.Moon;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.Twitter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TweetRetrieveTest {
	@Test
	public void shouldGetListOfTweets() throws Exception {
		Twitter twit = new Twitter();
		List<Status> timeline = twit.getUserTimeline("tackers");


		for (Status status : timeline) {
			System.out.printf("\"%s\" tweeted at %s (phase %d)\n", status.getText(), status.getCreatedAt(),
					Moon.getPhaseOfMoon(status.getCreatedAt()));
		}
	}



}
