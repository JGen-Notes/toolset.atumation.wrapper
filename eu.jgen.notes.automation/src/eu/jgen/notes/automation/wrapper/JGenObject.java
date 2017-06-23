/**
 * [The "BSD license"]
 * Copyright (c) 2016, JGen Notes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions 
 *    and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS 
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package eu.jgen.notes.automation.wrapper;

/**
 * Represents single object with in the model.
 * 
 * @since 1.1
 * @author Marek Stankiewicz
 */

public interface JGenObject {

	/**
	 * Retrieves an object type code of the object.
	 * 
	 * @return an object type code
	 */
	public int findObjectTypeCode();

	/**
	 * Finds the value of a single character property for the object.
	 * 
	 * @param prpTypeCode
	 *            a property type code
	 * @return value of a single character property
	 * 
	 */
	public char findCharacterProperty(int prpTypeCode);

	/**
	 * Finds the value of a numeric property for a given object. It takes
	 * property type code for the numeric property.
	 * 
	 * @param prpTypeCode
	 *            a property type code
	 * @return an value of a single numeric property
	 * 
	 */
	public int findNumericProperty(int prpTypeCode);

	/**
	 * Finds a value of a string property for the given object. It takes
	 * property type code for the string property.
	 * 
	 * @param prpTypeCode
	 *            a property type code
	 * @return a value of character property
	 * 
	 */
	public String findTextProperty(int prpTypeCode);

	/**
	 * Adds to the model an association of given type between two existing
	 * objects.
	 * 
	 * @param toObject
	 *            to an object
	 * @param ascTypeCode
	 *            an association type code
	 * 
	 */

	public void addAssociation(JGenObject toObject, int ascTypeCode);

	/**
	 * Deletes an association of a given association type from one given object
	 * to another given object. A flag indicates whether a trigger should be
	 * executed or not. False indicate non-trigger delete. True indicates that
	 * trigger delete will be executed and association and any objects related
	 * to it via mandatory associations will be deleted as well.
	 * 
	 * @param toObject
	 *            to an object
	 * @param ascTypeCode
	 *            an association type code
	 * @param triggerFlag
	 *            a delete trigger flag
	 * 
	 */
	public void deleteAssociation(JGenObject toObject, int ascTypeCode, boolean triggerFlag);

	/**
	 * Reorders given two objects that are related to the current object via a
	 * given ordered association by changing the order that a second object
	 * comes after the first.
	 * 
	 * @param ascTypeCode
	 *            an association type code
	 * @param toObject1
	 *            to object 1
	 * @param toObject2
	 *            to object (will be moved after toObject 1)
	 */
	public void reorderAssociationAfter(int ascTypeCode, JGenObject toObject1, JGenObject toObject2);

	/**
	 * Given two objects that are related to the given object via a given
	 * ordered association, change the order of the two objects so that the
	 * second object comes before the first.
	 * 
	 * @param ascTypeCode
	 *            an association type code
	 * @param toObject1
	 *            to a object 1
	 * @param toObject2
	 *            to a object 2 (will be moved before toObject 1)
	 */
	public void reorderAssociationBefore(int ascTypeCode, JGenObject toObject1, JGenObject toObject2);

	/**
	 * Updates a string property value for a given object.
	 * 
	 * @param prpTypeCode
	 *            a property type code
	 * @param value
	 *            a new value of the property
	 */
	public void updateTextProperty(int prpTypeCode, String value);

	/**
	 * Updates a numeric property value for a current object.
	 * 
	 * @param prpTypeCode
	 *            a property type code
	 * @param value
	 *            a new value of the property
	 */
	public void updateNumericProperty(int prpTypeCode, int value);

	/**
	 * Updates a single character property value for the current object.
	 * 
	 * @param prpTypeCode
	 *            property type code
	 * @param value
	 *            a new value of the property
	 */
	public void updateCharacterProperty(int prpTypeCode, char value);

	/**
	 * Finds all objects associated with the given object via a given
	 * association.
	 * 
	 * @param ascTypeCode
	 *            association type code
	 * @return array of objects associated with the given object via a given
	 *         association
	 */
	public JGenObject[] findAssociationMany(int ascTypeCode);

	/**
	 * Finds an object associated with the given object via a given association.
	 * 
	 * @param ascTypeCode
	 *            an association type code
	 * @return object associated with specific object via a given association or
	 *         null if destination object cannot be found
	 */
	public JGenObject findAssociationOne(int ascTypeCode);

	/**
	 * This method returns a count of the number of objects that are associated
	 * with the given object via a given association.
	 * 
	 * 
	 * @param ascTypeCode
	 *            int association type code
	 * 
	 * @return number of associations of the specified type
	 */
	public int countAssociationMany(int ascTypeCode);

	/**
	 * Checks if specified association exist.
	 * 
	 * @param ascTypeCode
	 *            association type code
	 * @return true if specified association exist
	 */
	public boolean isAssociationOneExists(int ascTypeCode);

	/**
	 * 
	 * @return Returns the ency.
	 */
	public abstract JGenEncyclopedia getEncy();

	/**
	 * @return Returns the id.
	 */
	public abstract int getId();

	/**
	 * @return Returns the model.
	 */
	public abstract JGenModel getModel();

	/**
	 * @return Returns the objTypeCode.
	 */
	public abstract int getObjTypeCode();

	public String toString();

	@Override
	boolean equals(Object obj);
}