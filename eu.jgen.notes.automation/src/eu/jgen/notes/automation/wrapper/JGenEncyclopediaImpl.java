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

import org.eclipse.swt.SWT;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Shell;

import eu.jgen.notes.automation.wrapper.JGenEncyclopedia;
import eu.jgen.notes.automation.wrapper.JGenModel;
import eu.jgen.notes.automation.wrapper.JGenObject;
import eu.jgen.notes.automation.wrapper.JGMSG;

public class JGenEncyclopediaImpl implements JGenEncyclopedia {

	protected Shell shell;

	protected OleFrame oleFrame;

	protected OleAutomation oleAutoToolset;

	protected OleControlSite oleControlSite;

	protected OleAutomation oleModels;

	protected OleAutomation oleModel;

	protected OleAutomation autoOpenAPI;

	protected int returnCode;

	protected boolean active = false;

	public JGenEncyclopediaImpl() {
		super();
		this.shell = new Shell();
		this.oleFrame = new OleFrame(shell, SWT.NONE);
		oleControlSite = new OleControlSite(oleFrame, SWT.NONE, "COOLgen.Toolset");
		this.oleAutoToolset = new OleAutomation(oleControlSite);
	}

	public void connect() {
		prolog();
		if (getReturnCode() != SUCCESSFUL || countModels() == 0) {
			returnCode = JGMSG.MODEL_NOT_FOUND_RC;
			return;
		}
		epilog();
		active = true;
	}

	public void disconnect() {
		active = false;
		autoOpenAPI.dispose();
		oleAutoToolset.dispose();
		oleModels.dispose();
		oleModel.dispose();
		oleControlSite.dispose();
		shell.dispose();
		oleFrame.dispose();
	}

	public void commit() {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "Commit" });
		oleModel.invoke(ids[0]);
	}

	private void extractAuto() {
		int ids[] = oleModel.getIDsOfNames(new String[] { "OpenAPI" });
		Variant variant = oleModel.getProperty(ids[0]);
		autoOpenAPI = variant.getAutomation();
		if (autoOpenAPI == null) {
			returnCode = OLE_AUTOMATION_SPECIFIC_ERROR;
		}
	}

	private void prolog() {
		int ids[] = oleAutoToolset.getIDsOfNames(new String[] { "Models" });
		Variant variant = oleAutoToolset.getProperty(ids[0]);
		oleModels = variant.getAutomation();
		if (oleModels == null) {
			returnCode = OLE_AUTOMATION_SPECIFIC_ERROR;
		}
	}

	private void epilog() {
		int ids[] = oleModels.getIDsOfNames(new String[] { "Item" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(1);
		Variant variant = oleModels.invoke(ids[0], rgvarg);
		oleModel = variant.getAutomation();
		if (oleModel == null) {
			returnCode = OLE_AUTOMATION_SPECIFIC_ERROR;
		}
		extractAuto();
	}

	protected int checkLastReturnCode() {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "LastReturnCode" });
		Variant variant = autoOpenAPI.getProperty(ids[0]);
		returnCode = variant.getInt();
		return returnCode;
	}

	public int countModels() {
		int ids[] = oleModels.getIDsOfNames(new String[] { "Count" });
		Variant variant = oleModels.getProperty(ids[0]);
		return variant.getInt();
	}

	public JGenModel findModelByName(String modelName) {
		JGenModel[] models = findModels();
		if (models.length == 1) {
			if (models[0].findInfo().equals(modelName)) {
				returnCode = SUCCESSFUL;
				return models[0];
			}
		}
		returnCode = JGMSG.MODEL_NOT_FOUND_RC;
		return null;
	}

	public JGenModel[] findModels() {
		JGenModel genModel = new JGenModelImpl(this);
		return new JGenModel[] { genModel };
	}

	public JGenObject findObjectById(int objectID) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "GetMetaModelObject" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(objectID);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		OleAutomation oleObject = variant.getAutomation();

		checkLastReturnCode();
		JGenModel[] models = findModels();
		if (models.length == 1) {
			JGenObjectImpl object = new JGenObjectImpl(this, models[0], oleObject);
			object.id = objectID;
			return object;
		}
		oleObject.dispose();
		return null;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public boolean isActive() {
		return active;
	}

	public String getReturnMessage() {
		return JGMSG.getReturnMessage(returnCode);
	}

}
