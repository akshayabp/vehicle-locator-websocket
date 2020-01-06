package org.apawaskar.vehiclelocator.domain;

import java.io.Serializable;

public class AppInfo implements Serializable{

	private String hostName;

	private String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

}
