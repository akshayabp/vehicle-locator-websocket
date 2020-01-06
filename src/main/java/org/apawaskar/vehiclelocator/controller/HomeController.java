package org.apawaskar.vehiclelocator.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apawaskar.vehiclelocator.domain.AppInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

	@RequestMapping("/")
	public AppInfo home() throws UnknownHostException {
		InetAddress localhost = InetAddress.getLocalHost();

		String ipAddress = localhost.getHostAddress();
		String hostName = localhost.getHostName();

		AppInfo appInfo = new AppInfo();
		appInfo.setIpAddress(ipAddress);
		appInfo.setHostName(hostName);

		return appInfo;
	}
}
