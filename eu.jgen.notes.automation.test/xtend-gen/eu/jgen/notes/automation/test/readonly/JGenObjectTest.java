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
 */
package eu.jgen.notes.automation.test.readonly;

import com.ca.gen.jmmi.schema.AscTypeCode;
import com.ca.gen.jmmi.schema.AscTypeHelper;
import com.ca.gen.jmmi.schema.ObjTypeCode;
import com.ca.gen.jmmi.schema.ObjTypeHelper;
import com.ca.gen.jmmi.schema.PrpTypeCode;
import com.ca.gen.jmmi.schema.PrpTypeHelper;
import com.google.common.base.Objects;
import eu.jgen.notes.automation.wrapper.JGenEncyclopedia;
import eu.jgen.notes.automation.wrapper.JGenFactory;
import eu.jgen.notes.automation.wrapper.JGenModel;
import eu.jgen.notes.automation.wrapper.JGenObject;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("all")
public class JGenObjectTest {
  private static JGenEncyclopedia ency;
  
  private static JGenModel genModel;
  
  private static JGenObject bussys;
  
  private static JGenObject pcroot;
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    JGenFactory factory = JGenFactory.eINSTANCE;
    JGenObjectTest.ency = factory.createEncyclopedia();
    JGenObjectTest.ency.connect();
    int _countModels = JGenObjectTest.ency.countModels();
    boolean _equals = (_countModels == 1);
    Assert.assertTrue(_equals);
    JGenObjectTest.genModel = JGenObjectTest.ency.findModels()[0];
    final Function1<JGenObject, Boolean> _function = (JGenObject it) -> {
      return Boolean.valueOf(true);
    };
    JGenObjectTest.bussys = IterableExtensions.<JGenObject>findFirst(((Iterable<JGenObject>)Conversions.doWrapArray(JGenObjectTest.genModel.findTypeObjects(JGenObjectTest.getObjType(ObjTypeCode.BUSSYS)))), _function);
    final Function1<JGenObject, Boolean> _function_1 = (JGenObject it) -> {
      return Boolean.valueOf(true);
    };
    JGenObjectTest.pcroot = IterableExtensions.<JGenObject>findFirst(((Iterable<JGenObject>)Conversions.doWrapArray(JGenObjectTest.genModel.findTypeObjects(JGenObjectTest.getObjType(ObjTypeCode.PCROOT)))), _function_1);
  }
  
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    JGenObjectTest.genModel = null;
    JGenObjectTest.ency.disconnect();
    JGenObjectTest.ency = null;
  }
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
  }
  
  @Test
  public void testGetEncy() {
    JGenEncyclopedia currentEncy = JGenObjectTest.bussys.getEncy();
    boolean _equals = Objects.equal(currentEncy, JGenObjectTest.ency);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetModel() {
    JGenModel currentModel = JGenObjectTest.bussys.getModel();
    boolean _equals = Objects.equal(currentModel, JGenObjectTest.genModel);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetId() {
    int id = JGenObjectTest.genModel.getId();
    Assert.assertTrue((id == 0));
  }
  
  @Test
  public void testCountAssociationMany() {
    final int count = JGenObjectTest.pcroot.countAssociationMany(JGenObjectTest.getAscType(AscTypeCode.HASBUSYS));
    Assert.assertTrue((count == 1));
  }
  
  @Test
  public void testFindAssociationMany() {
    final JGenObject[] array = JGenObjectTest.pcroot.findAssociationMany(JGenObjectTest.getAscType(AscTypeCode.HASBUSYS));
    int _size = ((List<JGenObject>)Conversions.doWrapArray(array)).size();
    boolean _equals = (_size == 1);
    Assert.assertTrue(_equals);
    final Function1<JGenObject, Boolean> _function = (JGenObject it) -> {
      return Boolean.valueOf(true);
    };
    int _id = IterableExtensions.<JGenObject>findFirst(((Iterable<JGenObject>)Conversions.doWrapArray(array)), _function).getId();
    int _id_1 = JGenObjectTest.bussys.getId();
    boolean _equals_1 = (_id == _id_1);
    Assert.assertTrue(_equals_1);
  }
  
  @Test
  public void testFindAssociationOne() {
    final JGenObject object = JGenObjectTest.bussys.findAssociationOne(JGenObjectTest.getAscType(AscTypeCode.FOUNDBY));
    int _id = object.getId();
    int _id_1 = JGenObjectTest.pcroot.getId();
    boolean _equals = (_id == _id_1);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testFindCharacterProperty() {
    final char character = JGenObjectTest.bussys.findCharacterProperty(JGenObjectTest.getPrpType(PrpTypeCode.PLANING));
    char _charAt = "N".charAt(0);
    boolean _equals = (character == _charAt);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testFindNumericProperty() {
    final int number = JGenObjectTest.bussys.findNumericProperty(JGenObjectTest.getPrpType(PrpTypeCode.OPCODE));
    Assert.assertTrue((number == 58));
  }
  
  @Test
  public void testFindTextProperty() {
    final String name = JGenObjectTest.bussys.findTextProperty(JGenObjectTest.getPrpType(PrpTypeCode.NAME));
    boolean _equals = Objects.equal(name, "EMPTYTST");
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetObjTypeCode() {
    final int code = JGenObjectTest.bussys.getObjTypeCode();
    int _objType = JGenObjectTest.getObjType(ObjTypeCode.BUSSYS);
    boolean _equals = (code == _objType);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testIsAssociationOneExists() {
    final boolean exist = JGenObjectTest.bussys.isAssociationOneExists(JGenObjectTest.getAscType(AscTypeCode.FOUNDBY));
    Assert.assertTrue(exist);
  }
  
  private static int getObjType(final ObjTypeCode objTypeCode) {
    return ObjTypeHelper.getCode(objTypeCode);
  }
  
  private static int getAscType(final AscTypeCode ascTypeCode) {
    return AscTypeHelper.getCode(ascTypeCode);
  }
  
  private static int getPrpType(final PrpTypeCode prpTypeCode) {
    return PrpTypeHelper.getCode(prpTypeCode);
  }
}
