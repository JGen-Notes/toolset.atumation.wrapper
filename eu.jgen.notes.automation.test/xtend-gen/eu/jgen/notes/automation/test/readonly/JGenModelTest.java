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

import com.ca.gen.jmmi.schema.ObjTypeCode;
import com.ca.gen.jmmi.schema.ObjTypeHelper;
import com.ca.gen.jmmi.schema.PrpTypeCode;
import com.ca.gen.jmmi.schema.PrpTypeHelper;
import com.google.common.base.Objects;
import eu.jgen.notes.automation.wrapper.JGenEncyclopedia;
import eu.jgen.notes.automation.wrapper.JGenFactory;
import eu.jgen.notes.automation.wrapper.JGenModel;
import eu.jgen.notes.automation.wrapper.JGenObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("all")
public class JGenModelTest {
  private final String PATH = "C:\\jgen.notes.models\\emptytst.ief\\";
  
  private static JGenEncyclopedia ency;
  
  private static JGenModel genModel;
  
  @BeforeClass
  public static void setUpBeforeClass() {
    JGenFactory factory = JGenFactory.eINSTANCE;
    JGenEncyclopedia _createEncyclopedia = factory.createEncyclopedia();
    JGenModelTest.ency = _createEncyclopedia;
    JGenModelTest.ency.connect();
    int _countModels = JGenModelTest.ency.countModels();
    boolean _equals = (_countModels == 1);
    Assert.assertTrue(_equals);
    JGenModel[] _findModels = JGenModelTest.ency.findModels();
    JGenModel _get = _findModels[0];
    JGenModelTest.genModel = _get;
  }
  
  @AfterClass
  public static void tearDownAfterClass() {
    JGenModelTest.genModel = null;
    JGenModelTest.ency.disconnect();
    JGenModelTest.ency = null;
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void testCountObjects() {
    int counter = JGenModelTest.genModel.countObjects();
    Assert.assertTrue((counter == 1030));
  }
  
  @Test
  public void testCountNamedObjects() {
    int _objType = JGenModelTest.getObjType(ObjTypeCode.HLENTDS);
    int _prpType = JGenModelTest.getPrpType(PrpTypeCode.NAME);
    int counter = JGenModelTest.genModel.countNamedObjects(_objType, _prpType, 
      "IEF_SUPPLIED");
    Assert.assertTrue((counter == 1));
  }
  
  @Test
  public void testCountTypeObjects() {
    int _objType = JGenModelTest.getObjType(ObjTypeCode.HLENTDS);
    int counter = JGenModelTest.genModel.countTypeObjects(_objType);
    Assert.assertTrue((counter == 54));
  }
  
  @Test
  public void testFindInfo() {
    String info = JGenModelTest.genModel.findInfo();
    boolean _equals = Objects.equal(info, "EMPTYTST");
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testFindAllObjects() {
    JGenObject[] array = JGenModelTest.genModel.findAllObjects();
    int _length = array.length;
    boolean _equals = (_length == 1030);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testFindNamedObject() {
    int _objType = JGenModelTest.getObjType(ObjTypeCode.HLENTDS);
    int _prpType = JGenModelTest.getPrpType(PrpTypeCode.NAME);
    JGenObject object = JGenModelTest.genModel.findNamedObject(_objType, _prpType, 
      "IEF_SUPPLIED");
    int _objTypeCode = object.getObjTypeCode();
    int _objType_1 = JGenModelTest.getObjType(ObjTypeCode.HLENTDS);
    boolean _tripleEquals = (_objTypeCode == _objType_1);
    Assert.assertTrue(_tripleEquals);
    int _prpType_1 = JGenModelTest.getPrpType(PrpTypeCode.NAME);
    String _findTextProperty = object.findTextProperty(_prpType_1);
    boolean _equals = Objects.equal(_findTextProperty, "IEF_SUPPLIED");
    Assert.assertTrue(_equals);
    int _prpType_2 = JGenModelTest.getPrpType(PrpTypeCode.BASDER);
    char _findCharacterProperty = object.findCharacterProperty(_prpType_2);
    char _charAt = "B".charAt(0);
    boolean _equals_1 = (_findCharacterProperty == _charAt);
    Assert.assertTrue(_equals_1);
    int _prpType_3 = JGenModelTest.getPrpType(PrpTypeCode.OPCODE);
    int _findNumericProperty = object.findNumericProperty(_prpType_3);
    boolean _equals_2 = (_findNumericProperty == 114);
    Assert.assertTrue(_equals_2);
  }
  
  @Test
  public void testFindTypeObjects() {
    int _objType = JGenModelTest.getObjType(ObjTypeCode.HLENTDS);
    JGenObject[] array = JGenModelTest.genModel.findTypeObjects(_objType);
    int _length = array.length;
    boolean _equals = (_length == 54);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetId() {
    int id = JGenModelTest.genModel.getId();
    Assert.assertTrue((id == 0));
  }
  
  @Test
  public void testGetEncy() {
    JGenEncyclopedia currentEncy = JGenModelTest.genModel.getEncy();
    boolean _equals = Objects.equal(currentEncy, JGenModelTest.ency);
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetName() {
    String info = JGenModelTest.genModel.getName();
    boolean _equals = Objects.equal(info, "EMPTYTST");
    Assert.assertTrue(_equals);
  }
  
  @Test
  public void testGetModelPath() {
    String path = JGenModelTest.genModel.getModelPath();
    boolean _equals = Objects.equal(path, this.PATH);
    Assert.assertTrue(_equals);
  }
  
  private static int getObjType(final ObjTypeCode objTypeCode) {
    return ObjTypeHelper.getCode(objTypeCode);
  }
  
  private static int getPrpType(final PrpTypeCode prpTypeCode) {
    return PrpTypeHelper.getCode(prpTypeCode);
  }
}
