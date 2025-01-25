package com.erp.usuario.utils;

import org.springframework.dao.DataIntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractSaveUpdateTemplate<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractSaveUpdateTemplate.class);


	public String saveOrUpdate(T entity) {
        try {
        	validateEntity(entity);
            executeSave(entity);
            return getSuccessMessage();
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação: {}", e.getMessage());
            return handleValidationException(e);
        } catch (NullPointerException e) {
            logger.error("Erro de null: {}", e.getMessage());
            return handleNullPointerException(e);
        } catch (DataIntegrityViolationException e) {
            logger.error("Violação de integridade: {}", e.getMessage());
            return handleDataIntegrityViolationException(e);
        } catch (Exception e) {
            logger.error("Erro inesperado: {}", e.getMessage(), e);
            return handleGeneralException(e);
        }
    }
	
	 protected abstract void executeSave(T entity);
	 
	 protected void validateEntity(T entity) {
	        if (entity == null) {
	            throw new IllegalArgumentException("A entidade não pode ser nula.");
	        }
	 }

	    protected String getSuccessMessage() {
	        return "Operação realizada com sucesso!";
	    }

	    protected String handleNullPointerException(NullPointerException e) {
	        return "NullPointerException";
	    }

	    protected String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
	        return "Não foi possível concluir a operação, há informações obrigatórias pendentes";
	    }

	    protected String handleGeneralException(Exception e) {
	        return "Erro desconhecido: " + e.getMessage();
	    }
	    
	    protected String handleValidationException(IllegalArgumentException e) {
	        return "Erro de validação: " + e.getMessage();
	    }
	}

