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
 * Interface defining methods accessing encyclopedia and its models.
 * 
 * @author Marek Stankiewicz
 * @since 1.0
 */
public interface JGenEncyclopedia {

	public static final int OLE_AUTOMATION_SPECIFIC_ERROR = 600;

	public static final int SUCCESSFUL = 0;

	/**
	 * Connects to the encyclopedia
	 * 
	 */
	public void connect();

	/**
	 * Counts models that can be accessed from this encyclopedia.
	 * 
	 * 
	 * @return number of models.
	 */
	public int countModels();

	/**
	 * Disconnects API to the encyclopedia
	 * 
	 */
	public void disconnect();

	/**
	 * Finds model by name. Given a model name, this method retrieves its
	 * associated model object.
	 * 
	 * @param modelName
	 *            string that identifies the Model.
	 * @return model object representing model in the encyclopedia
	 * 
	 */
	public JGenModel findModelByName(String modelName);

	/**
	 * Fetches encyclopedia models. This method retrieves all models in the
	 * encyclopedia. Returns single model for the local encyclopedia.
	 * 
	 * @see #countModels()
	 * 
	 * @return array of model objects.
	 * 
	 */
	public JGenModel[] findModels();

	/**
	 * Retrieves an object by it unique object ID.
	 * 
	 * @param objectID
	 *            id of the object to be retrieved
	 * 
	 * @return an object associated with this unique ID.
	 * 
	 */
	public JGenObject findObjectById(int objectID);

	/**
	 * This is the return code from the last encyclopedia API method that is
	 * executed from the object.
	 * 
	 * @return value of the return code
	 */
	public int getReturnCode();

	/**
	 * Returns message error set by the last executed method.
	 * 
	 * @return text describing error occurring execution of last method
	 */
	public String getReturnMessage();

	/**
	 * This method saves any changes (since the last commit or rollback) to the
	 * transaction file, so that they become a permanent part of the model.
	 * 
	 * SUCCESSFUL - indicates a successful execution COMMIT_UNSUCCESSFUL -
	 * indicates that the commit did not succeed
	 */
	public void commit();
}