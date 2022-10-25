package com.hemtest.main;

import com.hemtest.webserver.HemtestServer;

/**
 * The Hemtest Server Application
 *
 * @author anton lekedal (Doldas)
 *
 */
public class HemtestStartupApp {
	/**
	 * main method for starting the server.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// starts the web server instance on a new thread.
		new Thread(() -> {
			new HemtestServer().run(args);
		}).start();
	}
}
