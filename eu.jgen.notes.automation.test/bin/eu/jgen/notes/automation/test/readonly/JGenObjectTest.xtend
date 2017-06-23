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
package eu.jgen.notes.automation.test.readonly

import com.ca.gen.jmmi.schema.ObjTypeCode
import com.ca.gen.jmmi.schema.ObjTypeHelper
import com.ca.gen.jmmi.schema.PrpTypeCode
import com.ca.gen.jmmi.schema.PrpTypeHelper
import eu.jgen.notes.automation.wrapper.JGenEncyclopedia

import eu.jgen.notes.automation.wrapper.JGenFactory
import eu.jgen.notes.automation.wrapper.JGenModel
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.*
import com.ca.gen.jmmi.schema.AscTypeCode
import com.ca.gen.jmmi.schema.AscTypeHelper
import eu.jgen.notes.automation.wrapper.JGenObject

class JGenObjectTest {

	static JGenEncyclopedia ency
	static JGenModel genModel
	static JGenObject bussys
	static JGenObject pcroot

	@BeforeClass def static void setUpBeforeClass() throws Exception {
		var JGenFactory factory = JGenFactory::eINSTANCE
		ency = factory.createEncyclopedia()
		ency.connect()
		assertTrue(ency.countModels() == 1)
		genModel = ency.findModels().get(0)
		bussys = genModel.findTypeObjects(getObjType(ObjTypeCode.BUSSYS)).findFirst[true]
		pcroot = genModel.findTypeObjects(getObjType(ObjTypeCode.PCROOT)).findFirst[true]
	}

	@AfterClass def static void tearDownAfterClass() throws Exception {
		genModel = null
		ency.disconnect() 
		ency = null
	}

	@Before def void setUp() throws Exception {
	}

	@After def void tearDown() throws Exception {
	}

	@Test def void testGetEncy() {
		var currentEncy = bussys.getEncy()
		assertTrue(currentEncy == ency)
	}

	@Test def void testGetModel() {
		var currentModel = bussys.getModel()
		assertTrue(currentModel == genModel)
	}

	@Test def void testGetId() {
		var id = genModel.getId()
		assertTrue(id == 0)
	}

	@Test def void testCountAssociationMany() {
		val count = pcroot.countAssociationMany(getAscType(AscTypeCode.HASBUSYS))
		assertTrue(count == 1)
	}

	@Test def void testFindAssociationMany() {
		val array = pcroot.findAssociationMany(getAscType(AscTypeCode.HASBUSYS))
		assertTrue(array.size == 1)
		assertTrue(array.findFirst[true].id == bussys.id)
		assertTrue(array.findFirst[true] == bussys)
	}

	@Test def void testFindAssociationOne() {
		val object = bussys.findAssociationOne(getAscType(AscTypeCode.FOUNDBY))
		assertTrue(object.id == pcroot.id)
		assertTrue(object == pcroot)
	}

	@Test def void testFindCharacterProperty() {
		val character = bussys.findCharacterProperty(getPrpType(PrpTypeCode.PLANING))
		assertTrue(character == 'N'.charAt(0))
	}

	@Test def void testFindNumericProperty() {
		val number = bussys.findNumericProperty(getPrpType(PrpTypeCode.OPCODE))
		assertTrue(number == 58)
	}

	@Test def void testFindTextProperty() {
		val name = bussys.findTextProperty(getPrpType(PrpTypeCode.NAME))
		assertTrue(name == "EMPTYTST")
	}

	@Test def void testGetObjTypeCode() {
		val code = bussys.getObjTypeCode()
		assertTrue(code == getObjType(ObjTypeCode.BUSSYS))
	}

	@Test def void testIsAssociationOneExists() {
		val exist = bussys.isAssociationOneExists(getAscType(AscTypeCode.FOUNDBY))
		assertTrue(exist)
	}

	def private static int getObjType(ObjTypeCode objTypeCode) {
		return ObjTypeHelper::getCode(objTypeCode)
	}

	def private static int getAscType(AscTypeCode ascTypeCode) {
		return AscTypeHelper::getCode(ascTypeCode)
	}

	def private static int getPrpType(PrpTypeCode prpTypeCode) {
		return PrpTypeHelper::getCode(prpTypeCode)
	}
}
