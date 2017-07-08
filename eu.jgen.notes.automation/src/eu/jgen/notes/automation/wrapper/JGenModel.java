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
 * Interface defining methods acting on the model object.
 * 
 * @since 1.1
 * @author Marek Stankiewicz
 */
public interface JGenModel {

	/**
	 * Returns a count of objects in this model.
	 * 
	 * @return a count of objects in a model
	 */
	public int countObjects();

	/**
	 * This method fetches all the objects in a model with the given name and
	 * type. The parameters are the object type code, the property type code
	 * (usually this value is 224 which is the property type code for NAME), and
	 * a string for the name to be searched for.This method fetches all the
	 * objects in a model with the given name and type. The parameters are the
	 * object type code, the property type code (usually this value is 224 which
	 * is the property type code for NAME), and a string for the name to be
	 * searched for.
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @param prpTypeCode
	 *            a property type code
	 * @param name
	 *            a name of the object
	 * @return a count of the number of objects with a given type and name in
	 *         this model
	 */
	public int countNamedObjects(int objTypeCode, int prpTypeCode, String name);

	/**
	 * This method returns a count of the number of objects of a given type that
	 * there are in the model. The only parameter is the object type code.This
	 * method returns a count of the number of objects of a given type that
	 * there are in the model. The only parameter is the object type code.
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @return count of the number of objects of given type
	 * 
	 */
	public int countTypeObjects(int objTypeCode);

	/**
	 * Retrieves the model name. Retrieves the model name.
	 * 
	 * @return a name of the model
	 * 
	 */
	public String findInfo();

	/**
	 * This method fetches all the objects in the current model. It takes no
	 * parameters and returns an array of objects. This method fetches all the
	 * objects in the current model.
	 * 
	 * @return array of all objects in this model
	 */
	public JGenObject[] findAllObjects();

	/**
	 * This method fetches all the objects of a given type in the current model.
	 * The only parameter is the object type code and it returns an array of
	 * objects.
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @return array of all objects of a given type in the model
	 */
	public JGenObject[] findTypeObjects(int objTypeCode);

	/**
	 * Fetches array of objects in model by name, property type and object type.
	 * Given a model id, object type code, property type code and object name,
	 * this function retrieves the object id of the object with the given name.
	 * Use this function when the object name property is unique within the
	 * model.
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @param prpTypeCode
	 *            a property type code
	 * @param name
	 *            a name of the object
	 * @return an array of objects.
	 * 
	 */
	public JGenObject[] findNamedObjects(int objTypeCode, int prpTypeCode, String name);

	/**
	 * Fetches object in model by name, and object type. Given a model id,
	 * object type code, object name, this function retrieves the object id of
	 * the object with the given name. Use this function when the object name
	 * property is unique within the model.
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @param prpTypeCode
	 *            a property type code
	 * @param name
	 *            a name of the object
	 * @return an array of objects.
	 * 
	 */
	public JGenObject findNamedObject(int objTypeCode, int prpTypeCode, String name);

	/**
	 * Adds a new object to the model. It takes object type code as the single
	 * parameter and returns object that was created. *
	 * 
	 * @param objTypeCode
	 *            an object type code
	 * @return newly created object
	 * 
	 */
	public JGenObject addObject(int objTypeCode);

	/**
	 * Deletes a given object from this model. A flag indicates whether a
	 * trigger should be executed or not. False indicate non-trigger delete.
	 * True indicates that trigger delete will be executed and object and any
	 * objects related to it via mandatory associations will be deleted.
	 * 
	 * @param object
	 *            object to be deleted
	 * @param triggerFlag
	 *            delete trigger flag
	 * 
	 */
	public void deleteObject(JGenObject object, boolean triggerFlag);

	/**
	 * @return Returns the ency.
	 */
	public abstract JGenEncyclopedia getEncy();

	/**
	 * Get unique number within the encyclopedia that identifies the model. Zero
	 * for the model from the local encyclopedia.
	 * 
	 * @return Returns the id.
	 */
	public abstract int getId();

	/**
	 * Get name of the model.
	 * 
	 * @return Returns the name.
	 */
	public abstract String getName();

	/**
	 * Get local name of the model.
	 * 
	 * @return Returns the local name.
	 */
	public abstract String getLocalName();

	/**
	 * Gets the model path.
	 * 
	 * @return string with path to the model location
	 */
	public String getModelPath();

}