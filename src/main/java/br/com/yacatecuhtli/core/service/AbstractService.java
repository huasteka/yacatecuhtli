package br.com.yacatecuhtli.core.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class AbstractService implements ServiceInterface {

}
