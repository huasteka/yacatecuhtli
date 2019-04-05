package br.com.yacatecuhtli.core.port;

import java.util.List;

public interface ResponsePort {

	Object getAttributes();

	List<ErrorPort> getErrors();

}
