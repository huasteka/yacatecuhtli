package br.com.yacatecuhtli.core.port;

import org.springframework.core.convert.converter.Converter;

public interface ConverterPort<S, T> extends Converter<S, T> {

	void update(S source, T target);
	
}
