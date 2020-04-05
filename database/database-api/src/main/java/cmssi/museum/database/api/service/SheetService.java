/* 
 * Copyright 2019-2020 Christophe Vincent Munilla, FR Micro Entreprise - All Rights Reserved
 * 
 * RecolInline Tools Suite
 * @contact cmunilla@cmssi.fr
 */
package cmssi.museum.database.api.service;


import cmssi.museum.database.api.format.SheetFormat;

/**
 * Service allowing to read, update, create and delete sheet data structures stored in the database
 * 
 * @author cmunilla@cmssi.fr
 * @version 0.3
 */
public interface SheetService {
	
	/**
	 * Returns the {@link SheetFormat} of the selected sheet stored in the database whose Integer 
	 * identifier is passed as parameter
	 * 
	 * @param idSheet the Integer identifier of the sheet stored in the database 
	 * @param idUser the Integer identifier of the user asking for the sheet
	 *
	 * @return the {@link SheetFormat} for the selected sheet
	 * @throws UnauthorizedSheetAccessException if the specified user is not allowed to read the sheet
	 */
	 SheetFormat getSheet(Integer idSheet, Integer idUser) throws UnauthorizedSheetAccessException;

	/**
	 * Returns the {@link SheetFormat} of the updated sheet stored in the database, whose String formated 
	 * description is passed as parameter
	 * 
	 * @param sheet the String formated description of the sheet stored in the database to be updated
	 * @param idUser the Integer identifier of the user asking for the sheet update
	 * 
	 * @return the {@link SheetFormat} of the updated sheet 
	 * @throws UnauthorizedSheetAccessException if the specified user is not allowed to update the sheet
	 */
	 SheetFormat updateSheet(String sheet, Integer idUser) throws UnauthorizedSheetAccessException;
	
	/**
	 * Returns the {@link SheetFormat} of the deleted sheet whose Integer identifier is passed as parameter
	 * 
	 * @param idSheet the Integer identifier of the sheet stored in the database 
	 * @param idUser the Integer identifier of the user asking for the deletion
	 * 
	 * @return the {@link SheetFormat} of the deleted sheet 
	 * @throws UnauthorizedSheetAccessException if the specified user is not allowed to delete the sheet
	 */
	 SheetFormat deleteSheet(Integer idSheet, Integer idUser) throws UnauthorizedSheetAccessException;
	
	/**
	 * Returns the {@link SheetFormat} of a newly created sheet stored in the database for the museum and 
	 * domain whose identifiers are passed as parameters
	 * 
	 * @param idMuseum the Integer identifier of the museum, to which the sheet to be created is attached to
	 * @param idDomain the Integer identifier of the domain, to which the sheet to be created is attached to
	 * @param idUser the Integer identifier of the user asking for the creation
	 * 
	 * @return the {@link SheetFormat} of the newly created sheet
	 * @throws UnauthorizedSheetAccessException if the specified user is not allowed to create a sheet
	 */
	 SheetFormat createSheet(Integer idMuseum, Integer idDomain, Integer idUser) throws UnauthorizedSheetAccessException;
		
}
