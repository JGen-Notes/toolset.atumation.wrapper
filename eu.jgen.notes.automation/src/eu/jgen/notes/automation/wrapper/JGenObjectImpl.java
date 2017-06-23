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

public class JGenObjectImpl implements JGenObject {

	protected JGenEncyclopediaImpl ency;

	protected OleAutomation oleObject;

	protected OleAutomation autoOpenAPI;

	protected JGenModel model;

	public int id;

	public JGenObjectImpl(JGenEncyclopediaImpl ency, JGenModel model, OleAutomation oleObject) {
		this.ency = ency;
		this.model = model;
		this.oleObject = oleObject;
		this.autoOpenAPI = ency.autoOpenAPI;
	}

	public void addAssociation(JGenObject toObject, int ascTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "AddAssoc" });
		Variant[] rgvarg = new Variant[2];
		rgvarg[0] = new Variant(ascTypeCode);
		rgvarg[1] = new Variant(toObject.getId());
		oleObject.invokeNoReply(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public int countAssociationMany(int ascTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "CountCardManyAsc" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(ascTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getInt();
	}

	public void deleteAssociation(JGenObject toObject, int ascTypeCode, boolean triggerFlag) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "DelAssoc" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(ascTypeCode);
		rgvarg[1] = new Variant(toObject.getId());
		rgvarg[2] = new Variant(triggerFlag);
		oleObject.invokeNoReply(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public JGenObject[] findAssociationMany(int ascTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "FetchCardManyAsc" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(ascTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return new JGenObject[] {};
		}
		OleAutomation oleObjects = variant.getAutomation();
		int count = countAssociationMany(ascTypeCode);
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
		OleAutomation oleObject1 = variant.getAutomation();
		ency.checkLastReturnCode();
		JGenObjectImpl object = new JGenObjectImpl(ency, this.model, oleObject1);
		object.id = object.getId();
		return object;
	}

	public JGenObject findAssociationOne(int ascTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "FetchCardOneAsc" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(ascTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		if (variant.getType() == OLE.VT_EMPTY) {
			return null;
		}
		OleAutomation oleObject1 = variant.getAutomation();
		JGenObjectImpl object = new JGenObjectImpl(ency, this.model, oleObject1);
		object.id = object.getId();
		return object;
	}

	public char findCharacterProperty(int prpTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "FetchSingleCharPrp" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(prpTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getString().charAt(0);
	}

	public int findNumericProperty(int prpTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "FetchSingleNumericPrp" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(prpTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getInt();
	}

	public int findObjectTypeCode() {
		int ids[] = oleObject.getIDsOfNames(new String[] { "ObjectTypeCode" });
		Variant variant = oleObject.getProperty(ids[0]);
		return variant.getInt();
	}

	public String findTextProperty(int prpTypeCode) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "FetchCharArrayPrp" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(prpTypeCode);
		Variant variant = oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
		return variant.getString();
	}

	public JGenEncyclopedia getEncy() {
		return ency;
	}

	public int getId() {
		int ids[] = oleObject.getIDsOfNames(new String[] { "ID" });
		Variant variant = oleObject.getProperty(ids[0]);
		if (ency.checkLastReturnCode() != 0) {
			return -1;
		} else {
			return variant.getInt();
		}
	}

	public JGenModel getModel() {
		return model;
	}

	public int getObjTypeCode() {
		int ids[] = oleObject.getIDsOfNames(new String[] { "ObjectTypeCode" });
		Variant variant = oleObject.getProperty(ids[0]);
		return variant.getInt();
	}

	public boolean isAssociationOneExists(int ascTypeCode) {
		return findAssociationOne(ascTypeCode) != null;
	}

	public void reorderAssociationAfter(int ascTypeCode, JGenObject toObject1, JGenObject toObject2) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "ReordrAssocAftr" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(ascTypeCode);
		rgvarg[1] = new Variant(toObject1.getId());
		rgvarg[2] = new Variant(toObject2.getId());
		oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public void reorderAssociationBefore(int ascTypeCode, JGenObject toObject1, JGenObject toObject2) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "ReordrAssocBefr" });
		Variant[] rgvarg = new Variant[3];
		rgvarg[0] = new Variant(ascTypeCode);
		rgvarg[1] = new Variant(toObject1.getId());
		rgvarg[2] = new Variant(toObject2.getId());
		oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public void updateCharacterProperty(int prpTypeCode, char value) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "UpSingleChar" });
		Variant[] rgvarg = new Variant[2];
		rgvarg[0] = new Variant(prpTypeCode);
		rgvarg[1] = new Variant(Character.toString(value));
		oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public void updateNumericProperty(int prpTypeCode, int value) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "UpNumeric" });
		Variant[] rgvarg = new Variant[2];
		rgvarg[0] = new Variant(prpTypeCode);
		rgvarg[1] = new Variant(value);
		oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public void updateTextProperty(int prpTypeCode, String value) {
		int ids[] = oleObject.getIDsOfNames(new String[] { "UpCharArray" });
		Variant[] rgvarg = new Variant[2];
		rgvarg[0] = new Variant(prpTypeCode);
		rgvarg[1] = new Variant(value);
		oleObject.invoke(ids[0], rgvarg);
		ency.checkLastReturnCode();
	}

	public boolean equals(JGenObjectImpl obj) {
		return obj.getId() == this.id;
	}
}
