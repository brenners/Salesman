/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 29.05.2015
 * Author     : author
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | author        | Initial Create.
 *         | dd-mm-yy      |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yy      | 
 *---------|---------------|---------------------------------------------------
 */
package com.reply.salesmen.model;

import java.util.ArrayList;

/**
 * @author s.brenner
 * @param <T>
 *
 */
public interface Model<T> {

	public ArrayList<T> query(int salesOrderObjectID); 
	
}
