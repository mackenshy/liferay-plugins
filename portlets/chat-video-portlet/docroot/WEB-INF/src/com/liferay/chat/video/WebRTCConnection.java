/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.chat.video;

/**
 * @author Philippe Proulx
 */
public class WebRTCConnection {
	public WebRTCConnection(WebRTCClient caller) {
		this.caller = caller;
	}

	public WebRTCClient getCaller() {
		return this.caller;
	}

	public long getInitatedTsMs() {
		if (this.initiatedTsMs == -1) {
			return -1;
		}

		return System.currentTimeMillis() - this.initiatedTsMs;
	} public synchronized void setState(State state) {
		this.currentState = state;

		if (state == State.INITIATED) {
			this.initiatedTsMs = System.currentTimeMillis();
		} else {
			this.initiatedTsMs = -1;
		}
	}

	public synchronized State getState() {
		return this.currentState;
	}

	public enum State {
		INITIATED, CONNECTED, DISCONNECTED
	}

	private final WebRTCClient caller;
	private State currentState = State.DISCONNECTED;
	private long initiatedTsMs = -1;

}