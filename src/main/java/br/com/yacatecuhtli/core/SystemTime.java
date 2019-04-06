package br.com.yacatecuhtli.core;

import java.time.Instant;
import java.util.Date;

public class SystemTime implements SystemTimeInterface {

	public static final SystemTime INSTANCE = new SystemTime();

	@Override
	public Date getNow() {
		return Date.from(Instant.now());
	}

}
