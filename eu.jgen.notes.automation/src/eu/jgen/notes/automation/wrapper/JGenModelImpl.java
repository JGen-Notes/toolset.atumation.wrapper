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

import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.Variant;

import eu.jgen.notes.automation.wrapper.JGenEncyclopedia;
import eu.jgen.notes.automation.wrapper.JGenModel;
import eu.jgen.notes.automation.wrapper.JGenObject;

public class JGenModelImpl implements JGenModel {

	protected JGenEncyclopediaImpl ency;

	protected OleAutomation autoOpenAPI;

	public JGenModelImpl(JGenEncyclopediaImpl ency) {
		this.ency = ency;
		this.autoOpenAPI = ency.autoOpenAPI;
	}
	
	public String getLocalName() {
		int ids[] = ency.oleModel.getIDsOfNames(new String[] { "Name" });
		Variant variant = ency.oleModel.getProperty(ids[0]);
		if (ency.checkLastReturnCode() != 0) {
			return "";
		} else {
			return variant.getString();
		}
	}

	public JGenObject addObject(int objTypeCode) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "AddObject" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(objTypeCode);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		OleAutomation oleObject = variant.getAutomation();
		ency.checkLastReturnCode();
		JGenObjectImpl object = new JGenObjectImpl(ency, this, oleObject);
		return object;
	}

	public int countNamedObjects(int objTypeCode, int prpTypeCode, String name) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "CountModelNameTypeObjs" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(objTypeCode);
		rgvarg[1] = new Variant(prpTypeCode);
		rgvarg[2] = new Variant(name);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getInt();
	}

	public int countObjects() {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "CountModelObjs" });
		Variant variant = autoOpenAPI.invoke(ids[0]);
		ency.checkLastReturnCode();
		return variant.getInt();
	}

	public int countTypeObjects(int objTypeCode) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "CountModelTypeObjs" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(objTypeCode);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getInt();
	}

	public void deleteObject(JGenObject object, boolean triggerFlag) {
		JGenObjectImpl auto = (JGenObjectImpl) object;
		int ids[] = auto.oleObject.getIDsOfNames(new String[] { "DelObject" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(triggerFlag);
		auto.oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public JGenObject[] findAllObjects() {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "FetchAllModelObjects" });
		Variant variant = autoOpenAPI.invoke(ids[0]);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return new JGenObject[] {};
		}
		OleAutomation oleObjects = variant.getAutomation();
		int count = countObjects();
		JGenObject[] objects = new JGenObject[count];
		for (int i = 0; i < count; i++) {
			objects[i] = getItem(oleObjects, i);
		}
		return objects;
	}

	private JGenObject getItem(OleAutomation oleObjects, int index) {
		int ids[] = oleObjects.getIDsOfNames(new String[] { "Item" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(index + 1);
		Variant variant = oleObjects.invoke(ids[0], rgvarg);
		OleAutomation oleObject = variant.getAutomation();
		ency.checkLastReturnCode();
		JGenObject object = new JGenObjectImpl(ency, this, oleObject);
		return object;
	}

	// TODO need some more work
	public String findInfo() {
		return getName();
	}

	public JGenObject findNamedObject(int objTypeCode, int prpTypeCode, String name) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "FetchModelNameTypeObjs" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(objTypeCode);
		rgvarg[1] = new Variant(prpTypeCode);
		rgvarg[2] = new Variant(name);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return null;
		}
		OleAutomation oleObjects = variant.getAutomation();
		int count = countTypeObjects(objTypeCode);
		JGenObject[] objects = new JGenObject[count];
		for (int i = 0; i < count; i++) {
			objects[i] = getItem(oleObjects, i);
		}
		return objects[0];
	}

	public JGenObject[] findNamedObjects(int objTypeCode, int prpTypeCode, String name) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "FetchModelNameTypeObjs" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(objTypeCode);
		rgvarg[1] = new Variant(prpTypeCode);
		rgvarg[2] = new Variant(name);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return new JGenObject[] {};
		}
		OleAutomation oleObjects = variant.getAutomation();
		int count = countNamedObjects(objTypeCode, prpTypeCode, name);
		JGenObject[] objects = new JGenObject[count];
		for (int i = 0; i < count; i++) {
			objects[i] = getItem(oleObjects, i);
		}
		return objects;

	}

	public JGenEncyclopedia getEncy() {
		return ency;
	}

	public int getId() {
		return 0;
	}

	public String getName() {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "FetchModelInfo" });
		Variant[] rgvarg = new Variant[0];
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getString();
	}

	public JGenObject[] findTypeObjects(int objTypeCode) {
		int ids[] = autoOpenAPI.getIDsOfNames(new String[] { "FetchAllModelTypeObjs" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(objTypeCode);
		Variant variant = autoOpenAPI.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return new JGenObject[] {};
		}
		OleAutomation oleObjects = variant.getAutomation();
		int count = countTypeObjects(objTypeCode);
		JGenObject[] objects = new JGenObject[count];
		for (int i = 0; i < count; i++) {
			objects[i] = getItem(oleObjects, i);
		}
		return objects;
	}

	public String getModelPath() {
		int ids[] = ency.oleModel.getIDsOfNames(new String[] { "FullName" });
		Variant[] rgvarg = new Variant[0];
		Variant variant = ency.oleModel.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getString();
	}

}
