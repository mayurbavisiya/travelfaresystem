package com.ae.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ae.repository.TripRepository;

@Service
@Transactional
public class SwipedOutScheduler {

	private static final Logger loggerInfo = Logger.getLogger("travelFareSysInfo");
	@Autowired
	TripRepository tripRepository;

	// @Scheduled(fixedDelay = 15000)
	@Scheduled(cron = "0 0/5 * * * ?")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
		loggerInfo.info("Java cron job expression:: " + strDate);
		tripRepository.swipedOutTrips();
	}
}
