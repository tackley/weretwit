package net.tackley.were;

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
					getPhaseOfMoon(status.getCreatedAt()));
		}
	}

	/**
	 * Copied from  org.apache.tools.ant.util.DateUtils
	 * <p/>
	 * <p/>
	 * Copyright  2002-2004 The Apache Software Foundation
	 * <p/>
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 * <p/>
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * <p/>
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 * <p/>
	 * <p/>
	 * <p/>
	 * Calculate the phase of the moon for a given date.
	 * <p/>
	 * <p>Code heavily influenced by hacklib.c in <a
	 * href="http://www.nethack.org/">Nethack</a></p>
	 * <p/>
	 * <p>The Algorithm:
	 * <p/>
	 * <pre>
	 * moon period = 29.53058 days ~= 30, year = 365.2422 days
	 * <p/>
	 * days moon phase advances on first day of year compared to preceding year
	 *  = 365.2422 - 12*29.53058 ~= 11
	 * <p/>
	 * years in Metonic cycle (time until same phases fall on the same days of
	 *  the month) = 18.6 ~= 19
	 * <p/>
	 * moon phase on first day of year (epact) ~= (11*(year%19) + 18) % 30
	 *  (18 as initial condition for 1900)
	 * <p/>
	 * current phase in days = first day phase + days elapsed in year
	 * <p/>
	 * 6 moons ~= 177 days
	 * 177 ~= 8 reported phases * 22
	 * + 11/22 for rounding
	 * </pre>
	 *
	 * @return The phase of the moon as a number between 0 and 7 with
	 *         0 meaning new moon and 4 meaning full moon.
	 * @since 1.2, Ant 1.5
	 */
	public static int getPhaseOfMoon(Calendar cal) {
		int dayOfTheYear = cal.get(Calendar.DAY_OF_YEAR);
		int yearInMetonicCycle = ((cal.get(Calendar.YEAR) - 1900) % 19) + 1;
		int epact = (11 * yearInMetonicCycle + 18) % 30;
		if ((epact == 25 && yearInMetonicCycle > 11) || epact == 24) {
			epact++;
		}
		return (((((dayOfTheYear + epact) * 6) + 11) % 177) / 22) & 7;
	}

	public static int getPhaseOfMoon(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getPhaseOfMoon(calendar);
	}


}
