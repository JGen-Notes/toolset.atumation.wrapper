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

class JGenModelTest {

	val PATH = "C:\\jgen.notes.models\\emptytst.ief\\"

	static JGenEncyclopedia ency
	static JGenModel genModel

	@BeforeClass def static void setUpBeforeClass() {
		var JGenFactory factory = JGenFactory::eINSTANCE
		ency = factory.createEncyclopedia()
		ency.connect()
		assertTrue(ency.countModels() == 1)
		genModel = ency.findModels().get(0) 
	}

	@AfterClass def static void tearDownAfterClass() {
		genModel = null
		ency.disconnect()
		ency = null
	}

	@Before def void setUp() {
	}

	@After def void tearDown() {
	}

	@Test def void testCountObjects() {
		var int counter = genModel.countObjects()
		assertTrue(counter == 1030)
	}

	@Test def void testCountNamedObjects() {
		var int counter = genModel.countNamedObjects(getObjType(ObjTypeCode::HLENTDS), getPrpType(PrpTypeCode::NAME),
			"IEF_SUPPLIED")
		assertTrue(counter === 1)
	}

	@Test def void testCountTypeObjects() {
		var int counter = genModel.countTypeObjects(getObjType(ObjTypeCode::HLENTDS))
		assertTrue(counter == 54)
	}

	@Test def void testFindInfo() {
		var info = genModel.findInfo()
		assertTrue(info == "EMPTYTST")
	}

	@Test def void testFindAllObjects() {
		var array = genModel.findAllObjects()
		assertTrue(array.length == 1030)
	}

	@Test def void testFindNamedObject() {
		var object = genModel.findNamedObject(getObjType(ObjTypeCode::HLENTDS), getPrpType(PrpTypeCode::NAME),
			"IEF_SUPPLIED")
		assertTrue(object.objTypeCode === getObjType(ObjTypeCode::HLENTDS))
		assertTrue(object.findTextProperty(getPrpType(PrpTypeCode.NAME)) == "IEF_SUPPLIED")
		assertTrue(object.findCharacterProperty(getPrpType(PrpTypeCode.BASDER)) == 'B'.charAt(0))
		assertTrue(object.findNumericProperty(getPrpType(PrpTypeCode.OPCODE)) == 114)
	}

	@Test def void testFindTypeObjects() {
		var array = genModel.findTypeObjects(getObjType(ObjTypeCode::HLENTDS))
		assertTrue(array.length == 54)
	}

	@Test def void testGetId() {
		var id = genModel.getId()
		assertTrue(id == 0)
	}

	@Test def void testGetEncy() {
		var currentEncy = genModel.getEncy()
		assertTrue(currentEncy == ency)
	}

	@Test def void testGetName() {
		var info = genModel.getName()
		assertTrue(info == "EMPTYTST")
	}

	@Test def void testGetModelPath() {
		var path = genModel.getModelPath()
		assertTrue(path == PATH)
	}

	def private static int getObjType(ObjTypeCode objTypeCode) {
		return ObjTypeHelper::getCode(objTypeCode)
	}

	def private static int getPrpType(PrpTypeCode prpTypeCode) {
		return PrpTypeHelper::getCode(prpTypeCode)
	}
}
